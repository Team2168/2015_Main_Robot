
package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author shriji
 */
public class DriveRightPIDPosition extends Command {

	private double setPoint;
	
    public DriveRightPIDPosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	this.setPoint = Robot.drivetrain.rightPosController.getSetPoint();
    	
    }

    public DriveRightPIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }


    // Called just before this Command runs the first time
    
	protected void initialize() {
		Robot.drivetrain.rightPosController.reset();
		Robot.drivetrain.rightPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		Robot.drivetrain.rightPosController.setSetPoint(setPoint);
		Robot.drivetrain.driveRight(Robot.drivetrain.rightPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
    	return Robot.drivetrain.rightPosController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.drivetrain.rightPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
