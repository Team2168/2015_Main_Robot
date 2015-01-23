package org.team2168.commands.lift;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AcuateBreak extends Command {

	double position;
	
    public AcuateBreak(double position) {
        // Use requires() here to declare subsystem dependencies
    	this.position = position;
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.lift.isBreakNeeded(position)) {
    		Robot.lift.enableBreak();
    	}else {
    		Robot.lift.disableBreak();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
