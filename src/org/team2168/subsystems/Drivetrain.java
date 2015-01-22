package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The drivetrain subsystem.
 */
public class Drivetrain extends Subsystem {

	private static Drivetrain instance = null;
	private Talon leftMotor;
	private Talon rightMotor;
	private Encoder drivetrainLeftEncoder;
	private Encoder drivetrainRightEncoder;

	/**
	 * This method instantiates the motors.
	 * Private to prevent creating more than one instance of this subsystem.
	 */
	private Drivetrain() {
		leftMotor = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTORS);
		rightMotor = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTORS);
		drivetrainLeftEncoder = new Encoder(RobotMap.DRIVETRAIN_LEFT_ENCODER_A, 
											RobotMap.DRIVETRAIN_LEFT_ENCODER_B);
		drivetrainRightEncoder = new Encoder(RobotMap.DRIVETRAIN_RIGHT_ENCODER_A, 
											RobotMap.DRIVETRAIN_RIGHT_ENCODER_B);
	}

	/**
	 * @return an instance of the subsystem.
	 */
	public static Drivetrain getInstance() {
		if(instance == null) {
			instance = new Drivetrain();
		}

		return instance;
	}

	/**
	 * This method sets the default command so it always drives with the joysticks
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}

	/**
	 * Drive the left motors in the chassis.
	 * @param leftSpeed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft(double leftSpeed) {
		leftMotor.set(leftSpeed);
	}

	/**
	 * Drive the right motors in the chassis.
	 * @param rightSpeed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight(double rightSpeed) {
		rightMotor.set(rightSpeed);
	}

	/**
	 * Drive both motors in the chassis. (-1 to 1, positive is forward, negative is backwards)
	 * @param leftSpeed the speed to drive the left motor
	 * @param rightSpeed the speed to drive the right motor
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		//leftMotor.set(leftSpeed);
		//rightMotor.set(rightSpeed);
	}

	/**
	 * Stop driving the wheels.
	 */
	public void stop() {
		leftMotor.set(0);
		rightMotor.set(0);
	}

	/**
	 * Get the distance traveled by the left wheels.
	 * @return distance traveled in inches.
	 */
	public double getLeftPosition() {
		//TODO: return position of left wheels from encoders
		return 0.0;
	}

	/**
	 * Get the distance traveled by the right wheels.
	 * @return distance traveled in inches
	 */
	public double getRightPosition() {
		//TODO: return position of right wheels from encoders
		return 0.0;
	}

	/**
	 * Get the distance traveled by the chassis.
	 * @return the average distance in inches traveled by the left and right wheels
	 */
	public double getPosition() {
		return (getLeftPosition() + getRightPosition()) / 2.0;
	}

	/**
	 * Zero the distance traveled by the chassis
	 */
	public void resetPosition() {
		//TODO: reset distance traveled by the wheels to zero
	}

	/**
	 * Get the direction the chassis is heading.
	 * @return heading in degrees.
	 */
	public double getHeading() {
		//TODO: return current heading from gyro
		return 0.0;
	}

	/**
	 * Reset robot heading to zero.
	 */
	public void resetGyro() {
		//TODO: reset t he gyro heading to zero
	}
	
	public double rightEncoderDistance(){
		return drivetrainRightEncoder.getDistance();
	}
	
	public double leftEncoderDistance(){
		return drivetrainLeftEncoder.getDistance();
	}
}

