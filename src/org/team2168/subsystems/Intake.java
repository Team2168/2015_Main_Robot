package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The intake subsytem controls the intake motors and solenoids.
 * @author Vittorio Papandrea
 */
public class Intake extends Subsystem {

	private static Intake instance = null;
	private DoubleSolenoid rightLeftIntake;
	private Talon rightLeftMotor;
	private static DigitalInput leftLimitSwitch;
	private static DigitalInput rightLimitSwitch;
	private static AnalogInput toteDistanceSensor;
	private static final double CM_TO_INCH =  0.393701;

	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Intake() {
		rightLeftIntake = new DoubleSolenoid(RobotMap.INTAKE_DOUBLE_SOLENOID_FORWARD,
				RobotMap.INTAKE_DOUBLE_SOLENOID_REVERSE);
		rightLeftMotor 	= new Talon(RobotMap.INTAKE_MOTORS);

		leftLimitSwitch		= new DigitalInput(RobotMap.LEFT_TOTE_SWITCH);
		rightLimitSwitch 	= new DigitalInput(RobotMap.RIGHT_TOTE_SWITCH);
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
	 * Releases the intake from intaking position
	 */
	public void releaseIntake() {
		rightLeftIntake.set(Value.kReverse);
	}

	/**
	 * Actuates the intake into intaking position
	 */
	public void engageIntake() {
		rightLeftIntake.set(Value.kForward);
	}

	/**
	 * runs the intake motors in, making the tote move in toward the lift
	 */
	public void runIntakeIn() {
		setIntakeSpeed(1);
	}

	/**
	 * stops the intake motors
	 */
	public void stopIntake() {
		setIntakeSpeed(0);
	}

	/**
	 * runs the intake motors out, making the tote move out of the intake.
	 */
	public void runIntakeOut() {
		setIntakeSpeed(-1);
	}

	/**
	 * Sets the intake Speed of the motors.
	 * @param speed 1 to 0 Tote In. 0 - -1 Tote Out
	 */
	public void setIntakeSpeed(double speed) {
		rightLeftMotor.set(speed);
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
	 * Returns the voltage of the distance sensor
	 * @return voltage of distance sensor
	 */
	public double getRawToteDistance() {
		return toteDistanceSensor.getVoltage();
	}
	
	/**
	 * @return uses the voltage to return a value in inches
	 */
	//y = 0.512x^2 - 0.8656x + 6.1888
	//R� = 0.9985

	public double getToteDistance(){
		double toteDistance = getRawToteDistance();
		return ((0.512 * Math.pow(toteDistance, 2) - 0.8656 * toteDistance + 6.1888) * CM_TO_INCH);
	}

	/**
	 * Set the default command for the subsystem
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new MySpecialCommand());
	}

}
