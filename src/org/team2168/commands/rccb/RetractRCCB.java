package org.team2168.commands.rccb;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command releases the rccb
 */
public class RetractRCCB extends Command {

	public RetractRCCB() {
		requires(Robot.rccb);
	}

	/**
	 * Called first time the potato is run
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		Robot.rccb.DisengageRCCB();;
	}

	/**
	 * This method ends the command when it returns true
	 */
	protected boolean isFinished() {
		return Robot.rccb.isDisengaged();
	}

	/**
	 * This method runs once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 * This method tells the robot what to do if another command interrupts this one
	 */
	protected void interrupted() {
	}
}
