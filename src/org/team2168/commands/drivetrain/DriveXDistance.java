package org.team2168.commands.drivetrain;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class DriveXDistance extends Command{
	private double distanceGoal;
	private double speed;
	private double endDistance;
	private boolean finished;
	private double angle;

	private double powerShift;

	double rightSpeed = 0;
	double leftSpeed = 0;

	static final double DIST_ERROR_TOLERANCE_INCH = 1;
	static final double TURN_ERROR_TOLERANCE_DEG =1;

	double lastRotateOutput;

	/**
	 * Move the drivetrain forward the specified distance.
	 * @param distance in feet
	 */
	public DriveXDistance(double distance) {
		requires(Robot.drivetrain);
		this.distanceGoal = distance;
		this.speed = RobotMap.autoNormalSpeed;
		this.powerShift = 1;
		this.lastRotateOutput = 0;
	}

	public DriveXDistance(double distance, double speed) {
		this(distance);
		this.speed = speed;
	}

	public DriveXDistance(double distance, double speed, double powerShift) {
		this(distance, speed);
		this.powerShift = powerShift;
	}

	protected void initialize() {
		finished = false;
		Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetPosition();

		//reset controller
		Robot.drivetrain.imu.reset();
		Robot.drivetrain.driveTrainPosController.reset();
		Robot.drivetrain.rotateController.reset();


		//drivetrain.resetGyro();
		endDistance = Robot.drivetrain.getAveragedDistance() + distanceGoal;
		angle = Robot.drivetrain.getHeading();

		Robot.drivetrain.driveTrainPosController.setSetPoint(endDistance);
		Robot.drivetrain.driveTrainPosController.setMaxPosOutput(speed);
		Robot.drivetrain.driveTrainPosController.setMinPosOutput(-speed);
		Robot.drivetrain.driveTrainPosController.setAcceptErrorDiff(0.3); //feet
		Robot.drivetrain.rotateController.setSetPoint(angle);
		
		
		
		// 		//This code helps offset uneven left/right gearbox power, like a feedworward term
		//		//modify speeds based on power shift, - means put more power to left side, + means put more power to right side
		//		//this power shift helps accommodate for unequal power in drivetrains
		//
		//		if (powerShift > 1) //reduce left speed so right power is increased
		//		{
		//			rightSpeed = speed;
		//			leftSpeed = speed - speed*Math.abs(powerShift%1);
		//		}
		//
		//		else if (powerShift < 1) //reduce right speed so left if increased
		//		{
		//			rightSpeed = speed - speed*Math.abs(powerShift%1);
		//			leftSpeed = speed;
		//		}
		//
		//		Robot.drivetrain.rotateController.setSetPoint(Robot.drivetrain.gyroSPI.getAngleDeg());

		Robot.drivetrain.driveTrainPosController.Enable();
		Robot.drivetrain.rotateController.Enable();
	}

	protected void execute() {

		lastRotateOutput = Robot.drivetrain.rotateController.getControlOutput();
		double headingCorrection = (Robot.drivetrain.rotateController.getControlOutput()) + lastRotateOutput;

		Robot.drivetrain.tankDrive(Robot.drivetrain.driveTrainPosController.getControlOutput()+headingCorrection, Robot.drivetrain.driveTrainPosController.getControlOutput()-headingCorrection);
		//finished = Robot.drivetrain.driveTrainPosController.isFinished();
	}



	protected void interrupted() {
		Robot.drivetrain.tankDrive(0, 0); //bypass rate limiter
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
		Robot.drivetrain.tankDrive(0, 0); //bypass rate limiter
	}

}
