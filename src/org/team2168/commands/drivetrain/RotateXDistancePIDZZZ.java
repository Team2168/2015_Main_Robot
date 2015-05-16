
package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Vittorio
 */
public class RotateXDistancePIDZZZ extends Command {

	private double setPoint;
	private double maxSpeed;
	private double minSpeed;
	private double error = 0.5;  // Rotational degree error, default 0 never ends.
	private boolean absolute = false;
	
    public RotateXDistancePIDZZZ() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	this.setPoint = Robot.drivetrain.rotateController.getSetPoint();
    	this.maxSpeed = 1;
    	this.minSpeed = 0;
    }

    public RotateXDistancePIDZZZ(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }

    public RotateXDistancePIDZZZ(double setPoint, double maxSpeed){
  	   this(setPoint);
  	   this.maxSpeed = maxSpeed;
     }
    
    public RotateXDistancePIDZZZ(double setPoint, double maxSpeed, double minSpeed){
   	   this(setPoint, maxSpeed);
   	   this.minSpeed = minSpeed;
      }    

    public RotateXDistancePIDZZZ(double setPoint, double maxSpeed, double minSpeed, double error) {
    	this(setPoint, maxSpeed, minSpeed);
    	this.error = error;
    }
    
    public RotateXDistancePIDZZZ(double setPoint, double maxSpeed, double minSpeed, double error, boolean absolute) {
    	this(setPoint, maxSpeed, minSpeed, error);
    	this.absolute = absolute;
    }
    // Called just before this Command runs the first time
    
	protected void initialize() {
		if (!absolute)
			this.setPoint = this.setPoint + Robot.drivetrain.getHeading();
		Robot.drivetrain.rotateController.reset();
		Robot.drivetrain.rotateController.setSetPoint(setPoint);
		Robot.drivetrain.rotateController.setMaxPosOutput(maxSpeed);
		Robot.drivetrain.rotateController.setMaxNegOutput(-maxSpeed);
		Robot.drivetrain.rotateController.setMinPosOutput(minSpeed);
		Robot.drivetrain.rotateController.setMinNegOutput(-minSpeed);
		Robot.drivetrain.rotateController.setAcceptErrorDiff(error);
		//Robot.drivetrain.gyroSPI.reset();
		Robot.drivetrain.rotateController.Enable();
		
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		
		Robot.drivetrain.tankDrive(Robot.drivetrain.rotateController.getControlOutput(),-Robot.drivetrain.rotateController.getControlOutput());
	
		
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
		//TODO Should the command be stopped????????!?!?!?!?!? after PID is tuned
    	return Robot.drivetrain.rotateController.isFinished();
		//return false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.drivetrain.rotateController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}