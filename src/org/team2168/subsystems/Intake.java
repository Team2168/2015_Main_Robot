package org.team2168.subsystems;

import org.team2168.OI;
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
	private static DigitalInput leftLimitSwitch;
	private static DigitalInput rightLimitSwitch;
	private static AnalogInput toteDistanceSensor;
	private static double averagedToteDistance = 0.0;
	//TODO: calibrate this value
	private static final double TOTE_SENSOR_AVG_GAIN = 0.8;
	private static final double CM_TO_INCH =  0.393701;

	//Intake sensor won't return voltages smaller than:
	//TODO: calibrate this value
	private final static double INTAKE_TOTE_MIN_VOLTAGE = 0.5;

	private static final boolean LEFT_INVERTED = false;
	private static final boolean RIGHT_INVERTED = true;

	public boolean leftIntakeSelfTest = false;
	public boolean rightIntakeSelfTest = false;

	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Intake() {
		rightLeftIntake = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_DOUBLE_SOLENOID_FORWARD,
				RobotMap.INTAKE_DOUBLE_SOLENOID_REVERSE);
		rightMotor 	= new Victor(RobotMap.INTAKE_LEFT_MOTOR);
		leftMotor 	= new Victor(RobotMap.INTAKE_RIGHT_MOTOR);
		leftLimitSwitch = new DigitalInput(RobotMap.LEFT_TOTE_SWITCH);
		rightLimitSwitch = new DigitalInput(RobotMap.RIGHT_TOTE_SWITCH);
		toteDistanceSensor = new AnalogInput(RobotMap.INTAKE_SENSOR);
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
		if (leftLimitSwitch.get() || rightLimitSwitch.get()) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Returns the raw voltage from the intake distance sensor
	 * @return the sensed voltage from the distance sensor
	 */
	private double getRawToteDistance() {
		//Don't return values that are less than the TOTE_MIN_VOLTAGE.
		// This is to prevent garbage data being sent out when nothing is in front of the sensor.
		return Util.max(INTAKE_TOTE_MIN_VOLTAGE, toteDistanceSensor.getVoltage());
	}

	/**
	 * Get the averaged voltage of the intake distance sensor
	 * @return average value in volts
	 */
	public double getAveragedRawToteDistance() {
		averagedToteDistance = Util.runningAverage(getRawToteDistance(),
				averagedToteDistance, TOTE_SENSOR_AVG_GAIN);
		return averagedToteDistance;
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
}
