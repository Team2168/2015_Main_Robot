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
	private boolean drivingForward = false;
	private double powerShift;
	
	double rightSpeed = 0;
	double leftSpeed = 0;
	
	static final double DIST_ERROR_TOLERANCE_INCH = 1;
	static final double TURN_ERROR_TOLERANCE_DEG =1;

	double pTurn = 0.001;
	double iTurn = 0.00001;
	double pDrive = 0;
	
	
	//integral 
	double errsum = 0;
	double olderrsum = 0;
	
	double lastRotateOutput;
	
	/**
	 * Move the drivetrain forward the specified distance.
	 * @param distance in inches
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
		angle = Robot.drivetrain.gyroSPI.getAngleDeg();
		
		Robot.drivetrain.driveTrainPosController.setSetPoint(endDistance);
		Robot.drivetrain.driveTrainPosController.setMaxPosOutput(speed);
		Robot.drivetrain.driveTrainPosController.setMinPosOutput(-speed);
		Robot.drivetrain.rotateController.setSetPoint(angle);
		
//		
//		drivingForward = Robot.drivetrain.getAveragedDistance() < endDistance;
//		
//		//don't drive if the destination position is really close to our
//		//current position.
//		finished = Math.abs(distanceGoal) < 0.1;
//		
//		
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
		
		Robot.drivetrain.tankDrive(Robot.drivetrain.driveTrainPosController.getControlOutput(), Robot.drivetrain.driveTrainPosController.getControlOutput());
//		
//		//TODO set the margin of error
//		
//		//displacement error
//		double currentDistance = Robot.drivetrain.getAveragedDistance();
//		double distError = this.distanceGoal - currentDistance;

//		//angle error
//		double turnError = Robot.drivetrain.gyroSPI.getAngleDeg() - angle;
//		double steeringAdjust = pTurn  * turnError;
//		
//	
//		// calculate integral gain by summing past errors
//		errsum = turnError + olderrsum;
//		double integ = iTurn * errsum;
//
//		// save old ErrorSum for use in next loop
//		olderrsum += errsum;
//		
////		//Calculate driving speed
////		if (distError < DIST_ERROR_TOLERANCE_INCH)
////		{
////			
////			leftSpeed = leftSpeed*pDrive*distError;
////			rightSpeed = rightSpeed*pDrive*distError;
////
////		} 
//		
//		//make the left/right motors go less fast, to correct angle
//		//if the current angle is less than -tolerance, increase left and decrease right, Pgain is neg
//		//if the current angle is greater than +tolerance, increase right, and decrease left, Pgain is pos
////		if (turnError < -TURN_ERROR_TOLERANCE_DEG || turnError > TURN_ERROR_TOLERANCE_DEG) 
////		{
//				leftSpeed = leftSpeed + Robot.drivetrain.rotateController.getControlOutput();
//				rightSpeed = rightSpeed - Robot.drivetrain.rotateController.getControlOutput();;
////		} 
//
//		
//		//Determine if we are finished
//		//check if the robot is at, or past our destination position
//		if (finished || (drivingForward && currentDistance >= endDistance) || (!drivingForward && currentDistance <= endDistance)) 
//		{
//			//we're there, stop
//			Robot.drivetrain.tankDrive(0, 0);
//			finished = true; 
//		} 
//		else if(currentDistance < endDistance) 
//			//Drive forward 
//			Robot.drivetrain.tankDrive(leftSpeed,rightSpeed); //use ratelimiter
		
		
//		System.out.println("Right Speed: " + rightSpeed + 
//				" Left Speed: " + leftSpeed + 
//				" Angle = " + Robot.drivetrain.gyroSPI.getAngleDeg() + 
//				" Distance = " + Robot.drivetrain.imu.getPos() +
////				" Angle Error = " + turnError +
//				" Dist Error = " + Robot.drivetrain.driveTrainPosController.getError() 
////				" turnGain = "  + turnError +
////				" driveGain = " + pDrive*distError
//				);
		
		//System.out.println(drivetrain.getRightTicks() + " " + drivetrain.getAveragedEncoderDistance());
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
