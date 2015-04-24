package org.team2168.commands.buttonBox;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleRCCBState extends Command {

	private boolean state;
	
    public ToggleRCCBState() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rccb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.rccb.isEngaged()) {
    		state = true;
    		Robot.rccb.DisengageRCCB();
    	} else {
    		state = false;
    		Robot.rccb.EngageRCCB();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (state)
    		return Robot.rccb.isDisengaged();
    	else
    		return Robot.rccb.isEngaged();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
