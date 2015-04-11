package org.team2168.commands.slapper;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the motor that spins the bin slapper to move RCs out of
 *   the way in auto.
 * Note: this command doesn't end, it should be called from a command
 *   group with a timeout period defined to stop the motor from
 *   stalling out.
 */
public class SetSlapperSpeed extends Command {

	private double speed = 0.0;

	/**
	 *
	 * @param speed Value from -1.0 to 1.0, positive values spin clockwise.
	 */
	public SetSlapperSpeed(double speed) {
		requires(Robot.binSlapper);
		this.speed = speed;
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.binSlapper.drive(speed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.binSlapper.drive(0.0);
	}

	protected void interrupted() {

	}
}
