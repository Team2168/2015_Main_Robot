
package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author shriji
 */
public class DriveLeftPIDPosition extends Command {

	private double setPoint;
	
    public DriveLeftPIDPosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	this.setPoint = Robot.drivetrain.leftPosController.getSetPoint();
    	
    }

    public DriveLeftPIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }


    // Called just before this Command runs the first time
    
	protected void initialize() {
		Robot.drivetrain.leftPosController.reset();
		Robot.drivetrain.leftPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		Robot.drivetrain.leftPosController.setSetPoint(setPoint);
		Robot.drivetrain.driveLeft(Robot.drivetrain.leftPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
    	return Robot.drivetrain.leftPosController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.drivetrain.leftPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
