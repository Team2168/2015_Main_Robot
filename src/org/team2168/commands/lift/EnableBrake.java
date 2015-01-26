package org.team2168.commands.lift;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Engages the brake on the lift gearbox
 */
public class EnableBrake extends Command {
	public EnableBrake() {
		requires(Robot.lift);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		Robot.lift.enableBrake();
	}

	/**
	 * Returns true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return Robot.lift.isBrakeEnabled();
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	protected void interrupted() {
	}
}