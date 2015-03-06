package org.team2168.commands.calibration;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibration extends Command {
	private static final double RUN_TIME = 2.0; //sec
	private static final double DRIVE_SPEED = 0.5;

	//fractions of the nominal travel rates
	private static final double DRIVETRAIN_MIN_RATE = 10 * (DRIVE_SPEED * 0.8); //10ft/s
	private static final double LIFT_MIN_RATE = 25 * (DRIVE_SPEED * 0.8); //25in/s

	private int motorNumber;

	private double direction = 1.0;

	private double startPosition;
	private double startTime;

	/**
	 * @param motorNumber the PWM channel the motor is connected to (use RobotMap)
	 * @param forward true for positive numbers sent to motor controller
	 */
	public MotorCalibration(int motorNumber, boolean forward) {
		if(motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_1
				|| motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_2
				|| motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_3
				|| motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_1
				|| motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_2
				|| motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_3) {
			requires(Robot.drivetrain);
			this.motorNumber = motorNumber;
		}
		if(motorNumber == RobotMap.INTAKE_LEFT_MOTOR
				|| motorNumber == RobotMap.INTAKE_RIGHT_MOTOR) {
			requires(Robot.intake);
			this.motorNumber = motorNumber;
		}
		if(motorNumber == RobotMap.LIFT_MOTOR) {
			requires(Robot.lift);
			this.motorNumber = motorNumber;
		}
		if(motorNumber == RobotMap.WINCH_MOTOR) {
			requires(Robot.winch);
			this.motorNumber = motorNumber;
		}

		//drive forward or reverse
		if(!forward) {
			direction = -direction;
		}
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		startPosition = getPosition();
		startTime = Timer.getFPGATimestamp();
	}

	/**
	 * Moves selected motor to the speed specified
	 */
	protected void execute() {
		setSpeed(DRIVE_SPEED * direction);
	}

	/**
	 * Make this return when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return (Timer.getFPGATimestamp() - startTime) > RUN_TIME;
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
		double rate;

		//Stop the motor
		setSpeed(0.0);

		//calculate our rate of travel
		rate = Math.abs((getPosition() - startPosition) / (Timer.getFPGATimestamp() - startTime));

		//Check if we passed
		switch(motorNumber) {
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_1:
			Robot.drivetrain.rightSelfTest1 = (rate >= DRIVETRAIN_MIN_RATE);
			break;
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_2:
			Robot.drivetrain.rightSelfTest2 = (rate >= DRIVETRAIN_MIN_RATE);
			break;
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_3:
			Robot.drivetrain.rightSelfTest3 = (rate >= DRIVETRAIN_MIN_RATE);
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_1:
			Robot.drivetrain.leftSelfTest1 = (rate >= DRIVETRAIN_MIN_RATE);
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_2:
			Robot.drivetrain.leftSelfTest2 = (rate >= DRIVETRAIN_MIN_RATE);
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_3:
			Robot.drivetrain.leftSelfTest3 = (rate >= DRIVETRAIN_MIN_RATE);
			break;
		case RobotMap.LIFT_MOTOR:
			Robot.lift.liftSelfTest = (rate >= LIFT_MIN_RATE);
			break;
		case RobotMap.WINCH_MOTOR:
			Robot.winch.winchSelfTest = true;
			break;
		case RobotMap.INTAKE_LEFT_MOTOR:
			Robot.intake.leftIntakeSelfTest = true;
			break;
		case RobotMap.INTAKE_RIGHT_MOTOR:
			Robot.intake.rightIntakeSelfTest = true;
			break;
		default:
			break;
		}
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		setSpeed(0.0);
	}

	/**
	 * Get the position of the selected drive. Some drives don't have position sensors,
	 *   in this case, zero is returned for the drive.
	 * @return the position from the encoder associated with the selected motor.
	 */
	private double getPosition() {
		double retVal = 0.0;

		switch(motorNumber) {
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_1:
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_2:
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_3:
			retVal = Robot.drivetrain.getRightPosition();
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_1:
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_2:
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_3:
			retVal = Robot.drivetrain.getLeftPosition();
			break;
		case RobotMap.INTAKE_LEFT_MOTOR:
		case RobotMap.INTAKE_RIGHT_MOTOR:
			//No position sensor
			retVal = 0.0;
			break;
		case RobotMap.LIFT_MOTOR:
			retVal = Robot.lift.getPosition();
			break;
		case RobotMap.WINCH_MOTOR:
			//No position sensor
			retVal = 0.0;
			break;
		default:
			retVal = 0.0;
			break;
		}
		return retVal;
	}

	/**
	 * @param speed (value from 1.0 to -1.0)
	 */
	private void setSpeed(double speed) {
		switch(motorNumber) {
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_1:
			Robot.drivetrain.driveRight1(speed);
			break;
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_2:
			Robot.drivetrain.driveRight2(speed);
			break;
		case RobotMap.DRIVETRAIN_RIGHT_MOTOR_3:
			Robot.drivetrain.driveRight3(speed);
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_1:
			Robot.drivetrain.driveLeft1(speed);
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_2:
			Robot.drivetrain.driveLeft2(speed);
			break;
		case RobotMap.DRIVETRAIN_LEFT_MOTOR_3:
			Robot.drivetrain.driveLeft3(speed);
			break;
		case RobotMap.INTAKE_LEFT_MOTOR:
			Robot.intake.setLeftIntakeSpeed(speed);
			break;
		case RobotMap.INTAKE_RIGHT_MOTOR:
			Robot.intake.setRightIntakeSpeed(speed);
			break;
		case RobotMap.LIFT_MOTOR:
			Robot.lift.drive(speed);;
			break;
		case RobotMap.WINCH_MOTOR:
			Robot.winch.drive(speed);
			break;
		default:
			//Oh nos, how did you get here!@#!@
			break;
		}
	}
}