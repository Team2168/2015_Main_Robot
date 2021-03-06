package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.intake.IntakeOrientWithJoystick;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The intake subsytem controls the intake motors and solenoids.
 * @author Vittorio Papandrea
 */
public class Intake extends Subsystem {

	private static Intake instance = null;
	private DoubleSolenoid rightLeftIntake;
	private Victor leftMotor;
	private Victor rightMotor;
	private static AnalogInput toteDistanceSensor;
	private static AnalogInput rcDistanceSensor;
	private static AnalogInput rcBinAutoDectector;
	
	//TODO: calibrate this value
	private static final double IR_SENSOR_AVG_GAIN = 0.8;
	private static final double CM_TO_INCH =  0.393701;
	private static double averagedToteDistance = 0.0;
	private static double averagedRCDistance = 0.0;
	private static double averagedRCBinAutoDistance = 0.0;

	private static final double MOTOR_SPIN_DEADBAND = 0.15;

	//Intake sensor won't return voltages smaller  than:
	//TODO: calibrate this value
	private final static double IR_SENSOR_MIN_VOLTAGE = 0.5;

	private static final boolean LEFT_INVERTED = false;
	private static final boolean RIGHT_INVERTED = true;

	public boolean leftIntakeSelfTest = false;
	public boolean rightIntakeSelfTest = false;

	public boolean intakeDirection = false;

	

	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Intake() {
		rightLeftIntake = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_DOUBLE_SOLENOID_FORWARD,
				RobotMap.INTAKE_DOUBLE_SOLENOID_REVERSE);
		rightMotor 	= new Victor(RobotMap.INTAKE_LEFT_MOTOR);
		leftMotor 	= new Victor(RobotMap.INTAKE_RIGHT_MOTOR);
		toteDistanceSensor = new AnalogInput(RobotMap.INTAKE_SENSOR);
		rcDistanceSensor = new AnalogInput(RobotMap.RC_DISTANCE_SENSOR);
		rcBinAutoDectector = new AnalogInput(RobotMap.RC_BIN_AUTO_SENSOR);
	}

	/**
	 * @return the instance of the subsystem
	 */
	public static Intake getInstance() {
		if(instance == null) {
			instance = new Intake();
		}

		return instance;
	}

	/**
	 * Actuates the intake into intaking position
	 */
	public void engageIntake() {
		rightLeftIntake.set(Value.kForward);
	}

	/**
	 * Releases the intake from intaking position
	 */
	public void releaseIntake() {
		rightLeftIntake.set(Value.kReverse);
	}

	/**
	 * Sets the left intake motor speed.
	 * @param speed 1 to 0 (Tote Out) 0 to -1 (Tote In)
	 */
	public void setLeftIntakeSpeed(double speed) {
		speed = Util.limit(speed);
		if (LEFT_INVERTED)
			speed = -speed;

		leftMotor.set(speed);
	}

	/**
	 * Sets the right intake motor speed.
	 * @param speed 1 to 0 (Tote Out) 0 to -1 (Tote In)
	 */
	public void setRightIntakeSpeed(double speed) {
		speed = Util.limit(speed);
		if (RIGHT_INVERTED)
			speed  = -speed;

		rightMotor.set(speed);
	}

	/**
	 * Sets both intake motors to the same speed
	 * @param speed 1 to 0 (Tote Out) 0 to -1 (Tote In)
	 */
	public void setIntakeSpeed(double speed) {

		if (speed > 0) {
			this.intakeDirection = true;
		}else{
			this.intakeDirection = false;
		}

		setLeftIntakeSpeed(speed);
		setRightIntakeSpeed(speed);
	}

	/**
	 * Stops the intake motors
	 */
	public void stopIntake() {
		setIntakeSpeed(0);
	}

	/**
	 * Check if there's something in the intake.
	 * @return true when an object is in the intake.
	 */
	public boolean isTotePresent() {
		return Robot.intake.getAveragedRawToteDistance() > RobotMap.INTAKE_TOTE_STOP_VOLTAGE;
	}

	/**
	 * Returns the raw voltage from the intake distance sensor
	 * @return the sensed voltage from the distance sensor
	 */
	private double getRawToteDistance() {
		//Don't return values that are less than the TOTE_MIN_VOLTAGE.
		// This is to prevent garbage data being sent out when nothing is in front of the sensor.
		return Util.max(IR_SENSOR_MIN_VOLTAGE, toteDistanceSensor.getVoltage());
	}

	/**
	 * Get the averaged voltage of the intake distance sensor
	 * Note, this method should be called from a loop to prevent data from getting stale.
	 * @return average value in volts
	 */
	public double getAveragedRawToteDistance() {
		averagedToteDistance = Util.runningAverage(getRawToteDistance(),
				averagedToteDistance, IR_SENSOR_AVG_GAIN);
		return averagedToteDistance;
	}
	
	public double getAveragedRawRCBinAutoDistance() {
		averagedRCBinAutoDistance = Util.runningAverage(getRawRCBinChassisDistance(),
				averagedRCBinAutoDistance, IR_SENSOR_AVG_GAIN);
		return averagedRCBinAutoDistance;
	}

	//	/**
	//	 * Gets the distance to the nearest object from the back of the intake.
	//	 * @return the distance in inches
	//	 */
	//	public double getToteDistance() {
	//		double toteDistance = getRawToteDistance();
	//
	//		//y = 0.512x^2 - 0.8656x + 6.1888
	//		//R^2 = 0.9985
	//		//TODO: figure out why this isn't working
	//		return ((0.512 * Math.pow(toteDistance, 2) - 0.8656 * toteDistance + 6.1888) * CM_TO_INCH);
	//	}

	/**
	 * @return the voltage value seen from the RC distance sensor
	 */
	public double getRawRCDistance(){
		return Util.max(IR_SENSOR_MIN_VOLTAGE, rcDistanceSensor.getVoltage());
	}
	
	/**
	 * @return the voltage value seen from the RC Bin sensor
	 */
	public double getRawRCBinChassisDistance(){
		return Util.max(IR_SENSOR_MIN_VOLTAGE, rcBinAutoDectector.getVoltage());
	}

	/**
	 * Note, this method should be called from a loop to prevent data from getting stale.
	 * @return the averaged voltage value seen from the RC distance sensor.
	 */
	public double getAveragedRawRCDistance() {
		averagedRCDistance = Util.runningAverage(getRawRCDistance(),
				averagedRCDistance, IR_SENSOR_AVG_GAIN);
		return averagedRCDistance;
	}

	/**
	 * @return true if a recycling container is present in the intake
	 */
	public boolean isRCInIntake() {
		return getAveragedRawRCDistance() > RobotMap.INTAKE_RC_PRESENT_VOLTAGE;
	}

	/**
	 * Set the default command for the subsystem
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new SetIntakeWheelSpeed());
		setDefaultCommand(new IntakeOrientWithJoystick(OI.operatorJoystick));
	}

	/**
	 *
	 * @return true when the intake is engaged.
	 */
	public boolean isIntakeEngaged() {
		return rightLeftIntake.get() == Value.kForward;
	}

	/**
	 *
	 * @return true when the intake is disengaged.
	 */
	public boolean isIntakeDisengaged() {
		return rightLeftIntake.get() == Value.kReverse;
	}

	public boolean isIntakeWheelsIn() {
		return this.intakeDirection;
	}

	public boolean isIntakeWheelsOut() {
		return this.intakeDirection;
	}

	/**
	 * @return true if the right motor is spinning
	 */
	public boolean isRightSpinning() {
		return Math.abs(rightMotor.get()) >= MOTOR_SPIN_DEADBAND;
	}

	/**
	 * @return true if the left motor is spinning
	 */
	public boolean isLeftSpinning() {
		return Math.abs(leftMotor.get()) >= MOTOR_SPIN_DEADBAND;
	}
}
