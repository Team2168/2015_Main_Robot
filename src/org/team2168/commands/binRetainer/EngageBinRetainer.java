package org.team2168.commands.binRetainer;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EngageBinRetainer extends Command {

	public EngageBinRetainer() {
		requires(Robot.binRetainer);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.binRetainer.retractBinRetainer();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.binRetainer.isRetracted();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}