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
public class IntakeWheels extends Subsystem {

	private static IntakeWheels instance = null;
	private Victor leftMotor;
	private Victor rightMotor;
	
	public boolean leftIntakeSelfTest = false;
	public boolean rightIntakeSelfTest = false;

	public boolean intakeDirection = false;

	private static final boolean LEFT_INVERTED = false;
	private static final boolean RIGHT_INVERTED = true;

	
	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private IntakeWheels() {
		rightMotor 	= new Victor(RobotMap.INTAKE_LEFT_MOTOR);
		leftMotor 	= new Victor(RobotMap.INTAKE_RIGHT_MOTOR);
	}

	/**
	 * @return the instance of the subsystem
	 */
	public static IntakeWheels getInstance() {
		if(instance == null) {
			instance = new IntakeWheels();
		}

		return instance;
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
	 * Set the default command for the subsystem
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new SetIntakeWheelSpeed());
		setDefaultCommand(new IntakeOrientWithJoystick(OI.operatorJoystick));
	}

	public boolean isIntakeWheelsIn() {
		return this.intakeDirection;
	}

	public boolean isIntakeWheelsOut() {
		return this.intakeDirection;
	}
}
