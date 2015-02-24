package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.intake.StopIntakeWheels;
import org.team2168.utils.NPointAverager;

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
	private static final double CM_TO_INCH =  0.393701;
	private static NPointAverager toteDistance;

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
		toteDistance = new NPointAverager(10);
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
	 * @param speed 1 to 0 (Tote In) 0 to -1 (Tote Out)
	 */
	public void setLeftIntakeSpeed(double speed) {
		leftMotor.set(speed);
	}

	/**
	 * Sets the right intake motor speed.
	 * @param speed 1 to 0 (Tote In) 0 to -1 (Tote Out)
	 */
	public void setRightIntakeSpeed(double speed) {
		rightMotor.set(speed);
	}

	/**
	 * Sets both intake motors to the same speed
	 * @param speed 1 to 0 (Tote In) 0 to -1 (Tote Out)
	 */
	public void setIntakeSpeed(double speed) {
		setLeftIntakeSpeed(speed);
		setRightIntakeSpeed(-speed);
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
		return toteDistanceSensor.getVoltage();
	}

	/**
	 * Get the averaged voltage of the intake distance sensor
	 * @return average value in volts
	 */
	public double getAveragedRawToteDistance() {
		toteDistance.putData(getRawToteDistance());
		return toteDistance.getAverage();
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
	//		return ((0.512 * Math.pow(toteDistance, 2) - 0.8656 * toteDistance + 6.1888) * CM_TO_INCH);
	//	}

	/**
	 * Set the default command for the subsystem
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new SetIntakeWheelSpeed());
		setDefaultCommand(new StopIntakeWheels());
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
	 * @return true when the intake is disngaged.
	 */
	public boolean isIntakeDisengaged() {
		return rightLeftIntake.get() == Value.kReverse;
	}
}
