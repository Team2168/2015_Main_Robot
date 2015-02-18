package org.team2168.commands.gripper;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command releases the gripper
 */
public class ReleaseGripper extends Command {

	public ReleaseGripper() {
		requires(Robot.gripper);
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
		Robot.gripper.releaseGripper();
	}

	/**
	 * This method ends the command when it returns true
	 */
	protected boolean isFinished() {
		return Robot.gripper.isGripperDisengaged();
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
