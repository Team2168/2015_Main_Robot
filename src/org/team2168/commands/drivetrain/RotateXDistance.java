package org.team2168.commands.drivetrain;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateXDistance extends Command {

		private double  destinationAngle = 0, //The goal distance in inches
	            currentAngle     = 0,
	            fwdRotationSpeed = 0.0,
	            revRotationSpeed = 0.0;
	private boolean finished,
	            dimeTurning;
	
	private static double slowSpeed     = 0.8, //speed to turn the slower wheel at
	                      fastSpeed     = 0.9; //speed to turn the faster wheel at
	private static final double RATE_LIMIT = 0.05;
	
	/**
	* Turn the drivetrain.
	* This does not turn on a dime, it will turn in an arc (one side of drivetrain fixed).
	* 
	* @param angle The angle to turn, in degrees (positive is clockwise) 
	*/
	public RotateXDistance(double angle) {
		this(angle, false);
	}
	
	/**
	* Turn the drivetrain.
	* 
	* @param angle The angle to turn, in degrees (positive is clockwise)
	* @param dime Enable on a dime turning (rotate in place). If not, non turning side doesn't rotate.
	* NOTE: Turning on a dime with the 2013 drivetrain doesn't work very well. LOTS OF DRAG
	*/
	public RotateXDistance(double angle, boolean dime) {
	requires(Robot.drivetrain);
	destinationAngle = angle;
	dimeTurning = dime;
	}
	
	
	protected void initialize() {
		finished = false;
		
		Robot.drivetrain.tankDrive(0.0, 0.0);
		Robot.drivetrain.resetGyro();
		
		fwdRotationSpeed = RobotMap.minDriveSpeed;
		revRotationSpeed = RobotMap.minDriveSpeed;
	}
	
	
	/**
	* Rotate the drivetrain in the commanded direction until we are at or
	* above our commanded destination angle.
	*/
	protected void execute() {
	if(destinationAngle == 0.0) {
		finished = true;
	} else {
		System.out.println("Turn drivetrain");
		//Rate limit the drivetrain
		fwdRotationSpeed = Drivetrain.rateLimit(fastSpeed, fwdRotationSpeed, RATE_LIMIT);
		revRotationSpeed = Drivetrain.rateLimit(-slowSpeed, revRotationSpeed, RATE_LIMIT);
	//		if(dimeTurning) {
	//			revRotationSpeed = -fwdRotationSpeed;
	//		} else {
	//			revRotationSpeed = Drivetrain.rateLimit(SLOW_SPEED, revRotationSpeed, RATE_LIMIT);
	//		}
		
		currentAngle = Robot.drivetrain.getHeading();
		System.out.println("Gyro: " + currentAngle + "   fwdSpeed: " + fwdRotationSpeed
				+ "  revSpeed: " + revRotationSpeed);
		
		//Positive angle is rotation clockwise, negative is counter-clockwise
		if (Math.abs(currentAngle) >= Math.abs(destinationAngle)) {
			//We're there
			Robot.drivetrain.tankDrive(0, 0);
			finished = true;
		} else if (destinationAngle > currentAngle) {
			//rotate clockwise
			Robot.drivetrain.tankDrive(revRotationSpeed, fwdRotationSpeed);
		} else {
			//rotate counterclockwise
			Robot.drivetrain.tankDrive(fwdRotationSpeed, revRotationSpeed);
		}
	}
	}
	
	
	
	protected boolean isFinished() {
		return finished;
	}
	
	protected void end() {
	//make sure we are stopped for good measure
		Robot.drivetrain.tankDrive(0, 0);
	}
	
	protected void interrupted() {
	//Clear the current command to motor controllers if we're interrupted.
		Robot.drivetrain.tankDrive(0, 0);
	}
	
	public static void setFastSpeed(double input) {
		fastSpeed = input;
	}
	
	public static double getFastSpeed() {
		return fastSpeed;
	}
	
	public static void setSlowSpeed(double input) {
		slowSpeed = input;
	}
	
	public static double getSlowSpeed() {
		return slowSpeed;
	}	
}
