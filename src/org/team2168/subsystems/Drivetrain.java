package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.controllers.PIDPosition;
import org.team2168.PID.controllers.PIDSpeed;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.PID.sensors.IMU;
import org.team2168.commands.drivetrain.DriveWithJoysticks;
import org.team2168.utils.TCPSocketSender;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain subsystem.
 */
public class Drivetrain extends Subsystem {

	private static Drivetrain instance = null;
	private SpeedController leftMotor1;
	private SpeedController rightMotor1;
	private SpeedController leftMotor2;
	private SpeedController rightMotor2;
	private SpeedController leftMotor3;
	private SpeedController rightMotor3;

	public AverageEncoder drivetrainLeftEncoder;
	public AverageEncoder drivetrainRightEncoder;
	public ADXRS453Gyro gyroSPI;
	public IMU imu;

	private static final boolean LEFT_INVERTED = false;
	private static final boolean RIGHT_INVERTED = true;

	//declare position/speed controllers
	public PIDPosition driveTrainPosController;
	public PIDPosition rotateController;

	//declare speed controllers
	public PIDSpeed rightSpeedController;
	public PIDSpeed leftSpeedController;

	//output voltage...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	private volatile double leftMotor1Voltage;
	private volatile double leftMotor2Voltage;
	private volatile double leftMotor3Voltage;
	private volatile double rightMotor1Voltage;
	private volatile double rightMotor2Voltage;
	private volatile double rightMotor3Voltage;

	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPSocketSender TCPdrivePosController;
	TCPSocketSender TCPrightSpeedController;
	TCPSocketSender TCPleftSpeedController;
	TCPSocketSender TCProtateController;

	/**
	 * This method instantiates the motors.
	 * Private to prevent creating more than one instance of this subsystem.
	 */
	private Drivetrain() {
		//leftMotor1 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_1);
		leftMotor1 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_1);
		rightMotor1 = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1);
		leftMotor2 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_2);
		rightMotor2 = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2);
		leftMotor3 = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTOR_3);
		rightMotor3 = new Talon(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3);

		gyroSPI = new ADXRS453Gyro();
		gyroSPI.startThread();


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

		imu = new IMU(drivetrainLeftEncoder,drivetrainRightEncoder,RobotMap.wheelbase);

		//DriveStraight Controller
		rotateController = new PIDPosition(
				"RotationController",
				RobotMap.rotatePositionP,
				RobotMap.rotatePositionI,
				RobotMap.rotatePositionD,
				gyroSPI,
				RobotMap.driveTrainPIDPeriod);

		driveTrainPosController = new PIDPosition(
				"driveTrainPosController",
				RobotMap.driveTrainRightPositionP,
				RobotMap.driveTrainRightPositionI,
				RobotMap.driveTrainRightPositionD,
				imu,
				RobotMap.driveTrainPIDPeriod);

		//Spawn new PID Controller
		rightSpeedController = new PIDSpeed(
				"RightSpeedController",
				RobotMap.driveTrainRightSpeedP,
				RobotMap.driveTrainRightSpeedI,
				RobotMap.driveTrainRightSpeedD,
				drivetrainRightEncoder,
				RobotMap.driveTrainPIDPeriod);

		leftSpeedController = new PIDSpeed(
				"LeftSpeedController",
				RobotMap.driveTrainLeftSpeedP,
				RobotMap.driveTrainLeftSpeedI,
				RobotMap.driveTrainLeftSpeedD,
				drivetrainLeftEncoder,
				RobotMap.driveTrainPIDPeriod);


		//add min and max output defaults and set array size
		rightSpeedController.setSIZE(RobotMap.drivetrainPIDArraySize);
		leftSpeedController.setSIZE(RobotMap.drivetrainPIDArraySize);
		driveTrainPosController.setSIZE(RobotMap.drivetrainPIDArraySize);
		rotateController.setSIZE(RobotMap.drivetrainPIDArraySize);

		//start controller threads
		rightSpeedController.startThread();
		leftSpeedController.startThread();
		driveTrainPosController.startThread();
		rotateController.startThread();

		//start TCP Servers for DEBUGING ONLY
		TCPdrivePosController = new TCPSocketSender(RobotMap.TCPServerDrivetrainPos, driveTrainPosController);
		TCPdrivePosController.start();

		TCPrightSpeedController = new TCPSocketSender(RobotMap.TCPServerRightDrivetrainSpeed, rightSpeedController);
		TCPrightSpeedController.start();

		TCPleftSpeedController = new TCPSocketSender(RobotMap.TCPServerLeftDrivetrainSpeed, leftSpeedController);
		TCPleftSpeedController.start();

		TCProtateController = new TCPSocketSender(RobotMap.TCPServerRotateController, rotateController);
		TCProtateController.start();

		leftMotor1Voltage = 0;
		leftMotor2Voltage = 0;
		leftMotor3Voltage = 0;
		rightMotor1Voltage = 0;
		rightMotor2Voltage = 0;
		rightMotor3Voltage = 0;
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
		setDefaultCommand(new DriveWithJoysticks(OI.driverJoystick));
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getLeft1MotorVoltage() {
		return leftMotor1Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getLeft2MotorVoltage() {
		return leftMotor2Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getLeft3MotorVoltage() {
		return leftMotor3Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getRight1MotorVoltage() {
		return rightMotor1Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getRight2MotorVoltage() {
		return rightMotor2Voltage;
	}

	/**
	 * Returns the last commanded voltage to the motor
	 * @return double in volts representing last commanded voltage to motor
	 */
	public double getRight3MotorVoltage() {
		return rightMotor3Voltage;
	}

	/**
	 * Drive the first left motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft1(double speed) {
		speed = Util.limit(speed);
		if (LEFT_INVERTED)
			speed = -speed;

		leftMotor1.set(speed);
		leftMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drive the second left motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft2(double speed) {
		speed = Util.limit(speed);
		if (LEFT_INVERTED)
			speed = -speed;

		leftMotor2.set(speed);
		leftMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drive the third left motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveLeft3(double speed) {
		speed = Util.limit(speed);
		if (LEFT_INVERTED)
			speed = -speed;

		leftMotor3.set(speed);
		leftMotor3Voltage =  Robot.pdp.getBatteryVoltage() * speed;
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
		speed = Util.limit(speed);
		if (RIGHT_INVERTED)
			speed = -speed;

		rightMotor1.set(speed);
		rightMotor1Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drive the second right motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight2(double speed) {
		speed = Util.limit(speed);
		if (RIGHT_INVERTED)
			speed = -speed;

		rightMotor2.set(speed);
		rightMotor2Voltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Drive the third right motor in the chassis.
	 * @param speed the speed to drive the motor (-1 to 1, positive is forward, negative is backwards)
	 */
	public void driveRight3(double speed) {
		speed = Util.limit(speed);
		if (RIGHT_INVERTED)
			speed = -speed;

		rightMotor3.set(speed);
		rightMotor3Voltage = Robot.pdp.getBatteryVoltage() * speed;
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
		return drivetrainLeftEncoder.getPos();
	}

	/**
	 * Get the distance traveled by the right wheels.
	 * @return distance traveled in inches
	 */
	public double getRightPosition() {
		return drivetrainRightEncoder.getPos();
	}

	/**
	 * Get the distance traveled by the chassis.
	 * @return the average distance in inches traveled by the left and right wheels
	 */
	public double getAveragedDistance() {
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
		return gyroSPI.getAngle();
	}

	/**
	 * Reset robot heading to zero.
	 */
	public void resetGyro() {
		gyroSPI.reset();
	}

	/**
	 * Calibrate gyro.
	 * This should only be called if the robot will be stationary for the calibration period.
	 */
	public void calibrateGyro() {
		gyroSPI.calibrate();
	}

	/**
	 * @return true if the gyro completed its previous calibration sequence.
	 */
	public boolean isGyroCalibrated() {
		return gyroSPI.hasCompletedCalibration();
	}

	/**
	 * A  simple rate limiter.
	 * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
	 *
	 * @param input the input value (speed from command/joystick)
	 * @param speed the speed currently being traveled at
	 * @param limit the rate limit
	 * @return the new output speed (rate limited)
	 */
	public static double rateLimit(double input, double speed, double limit) {
		return rateLimit(input, speed, limit, limit);
	}

	/**
	 * A simple rate limiter.
	 * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
	 *
	 * @param input the input value (speed from command/joystick)
	 * @param speed the speed currently being traveled at
	 * @param posRateLimit the rate limit for accelerating
	 * @param negRateLimit the rate limit for decelerating
	 * @return the new output speed (rate limited)
	 */
	public static double rateLimit(double input, double speed,
			double posRateLimit, double negRateLimit) {
		if (input > 0) {
			if (input > (speed + posRateLimit)) {
				//Accelerating positively
				speed = speed + posRateLimit;
			} else if (input < (speed - negRateLimit)) {
				//Decelerating positively
				speed = speed - negRateLimit;
			} else {
				speed = input;
			}
		} else {
			if (input < (speed - posRateLimit)) {
				//Accelerating negatively
				speed = speed - posRateLimit;
			} else if (input > (speed + negRateLimit)) {
				//Decelerating negatively
				speed = speed + negRateLimit;
			} else {
				speed = input;
			}
		}
		return speed;
	}

}
