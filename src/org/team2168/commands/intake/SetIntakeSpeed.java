package org.team2168.commands.intake;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeSpeed extends Command {

	double speed = 0.0;

	/**
	 *
	 * @param speed the speed to drive the intake at
	 */
	public SetIntakeSpeed(double speed) {
		requires(Robot.intake);
		this.speed = speed;
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 * because there are 2 axes using the same command we have
	 * to get to difference of the 2 at the same time in order
	 * to get the correct value
	 */
	protected void execute() {
		Robot.intake.setIntakeSpeed(speed);
	}

	/**
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return true;
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
