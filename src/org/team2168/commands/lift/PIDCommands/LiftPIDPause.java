
package org.team2168.commands.lift.PIDCommands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author shriji
 */
public class LiftPIDPause extends Command {

    public LiftPIDPause() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    
	protected void initialize() {
		Robot.lift.liftController.Pause();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	
    }

    //delete me
    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return Robot.lift.liftController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    }
}
