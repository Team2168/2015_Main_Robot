
package org.team2168.commands.drivetrain;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Vittorio
 */
public class RotateXDistancePIDZZZ extends Command {

	private double setPoint;
	private double speed;
	
	
    public RotateXDistancePIDZZZ() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	this.setPoint = Robot.drivetrain.rotateController.getSetPoint();
    }

    public RotateXDistancePIDZZZ(double setPoint){
 	   this();
 	   this.speed = 1;
 	   this.setPoint = setPoint;
    }

    public RotateXDistancePIDZZZ(double setPoint, double speed){
  	   this();
  	   this.speed = speed;
  	   this.setPoint = setPoint;
     }

    // Called just before this Command runs the first time
    
	protected void initialize() {
		Robot.drivetrain.rotateController.reset();
		Robot.drivetrain.rotateController.setSetPoint(setPoint);
		Robot.drivetrain.rotateController.setMaxPosOutput(speed);
		Robot.drivetrain.rotateController.setMaxNegOutput(-speed);
		Robot.drivetrain.gyroSPI.reset();
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