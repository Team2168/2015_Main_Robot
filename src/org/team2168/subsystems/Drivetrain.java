package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PIDController.sensors.AverageEncoder;
import org.team2168.commands.drivetrain.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain subsystem.
 */
public class Drivetrain extends Subsystem {

	private static Drivetrain instance = null;
	private Talon leftMotor1;
	private Talon rightMotor1;
	private Talon leftMotor2;
	private Talon rightMotor2;
	private Talon leftMotor3;
	private Talon rightMotor3;
	private AverageEncoder drivetrainLeftEncoder;
	private AverageEncoder drivetrainRightEncoder;
	private Gyro drivetrainGyro;

	/**
	 * This method instantiates the motors.
	 * Private to prevent creating more than one instance of this subsystem.
	 */
	private Drivetrain() {
		leftMotor1 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_1);
		rightMotor1 = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1);
		leftMotor2 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_2);
		rightMotor2 = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2);
		leftMotor3 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_3);
		rightMotor3 = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3);
		drivetrainRightEncoder = new AverageEncoder(
				RobotMap.DRIVETRAIN_RIGHT_ENCODER_A,
				RobotMap.DRIVETRAIN_RIGHT_ENCODER_B,
				RobotMap.driveEncoderPulsePerRot,
				RobotMap.driveEncoderDistPerTick,
				RobotMap.rightDriveTrainEncoderReverse,
				RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType,
				RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
		drivetrainLeftEncoder = new AverageEncoder(
				RobotMap.DRIVETRAIN_LEFT_ENCODER_A,
				RobotMap.DRIVETRAIN_LEFT_ENCODER_B,
				RobotMap.driveEncoderPulsePerRot,
				RobotMap.driveEncoderDistPerTick,
				RobotMap.leftDriveTrainEncoderReverse,
				RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType,
				RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
		drivetrainGyro = new Gyro(RobotMap.DRIVE_GYRO);
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
	 * Drive the first left motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft1(double speed) {
		leftMotor1.set(speed);
	}

	/**
	 * Drive the second left motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft2(double speed) {
		leftMotor2.set(speed);
	}

	/**
	 * Drive the third left motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft3(double speed) {
		leftMotor3.set(speed);
	}

	/**
	 * Drive the motors on the left side of the chassis.
	 * @param speed the speed to drive the motors (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft(double speed) {
		driveLeft1(speed);
		driveLeft2(speed);
		driveLeft3(speed);
	}

	/**
	 * Drive the first right motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight1(double speed) {
		rightMotor1.set(speed);
	}

	/**
	 * Drive the second right motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight2(double speed) {
		rightMotor2.set(speed);
	}

	/**
	 * Drive the third right motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight3(double speed) {
		rightMotor3.set(speed);
	}

	/**
	 * Drive the motors on the left right side of the chassis.
	 * @param speed the speed to drive the motors (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight(double speed) {
		driveRight1(speed);
		driveRight2(speed);
		driveRight3(speed);
	}

	/**
	 * Drive both motors in the chassis. (-1 to 1, positive is forward, negative is backwards)
	 * @param speed the speed to drive the left motors
	 * @param speed the speed to drive the right motors
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
	}

	/**
	 * Stop driving the wheels on both sides of the chassis.
	 */
	public void stop() {
		leftMotor1.set(0);
		rightMotor1.set(0);
		leftMotor2.set(0);
		rightMotor2.set(0);
		leftMotor3.set(0);
		rightMotor3.set(0);
	}

	/**
	 * Get the distance traveled by the left wheels.
	 * @return distance traveled in inches.
	 */
	public double getLeftPosition() {
		return drivetrainLeftEncoder.getDistance();
	}

	/**
	 * Get the distance traveled by the right wheels.
	 * @return distance traveled in inches
	 */
	public double getRightPosition() {
		return drivetrainRightEncoder.getDistance();
	}

	/**
	 * Get the distance traveled by the chassis.
	 * @return the average distance in inches traveled by the left and right wheels
	 */
	public double getPosition() {
		return (getLeftPosition() + getRightPosition()) / 2.0;
	}

	/**
	 * Zero the distance traveled by the left wheels of the chassis.
	 */
	public void resetLeftPosition() {
		drivetrainLeftEncoder.reset();
	}

	/**
	 * Zero the distance traveled by the right wheels of the chassis.
	 */
	public void resetRightPosition() {
		drivetrainRightEncoder.reset();
	}

	/**
	 * Zero the distance traveled by the chassis
	 */
	public void resetPosition() {
		resetLeftPosition();
		resetRightPosition();
	}


	/**
	 * Get the direction the chassis is heading.
	 * @return heading in degrees.
	 */
	public double getHeading() {
		return drivetrainGyro.getAngle();
	}

	/**
	 * Reset robot heading to zero.
	 */
	public void resetGyro() {
		drivetrainGyro.reset();
	}
}

