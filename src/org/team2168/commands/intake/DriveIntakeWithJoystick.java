package org.team2168.commands.intake;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveIntakeWithJoystick extends Command {

	F310 joystick;

	public DriveIntakeWithJoystick(F310 joystick) {
		this.joystick = joystick;
		requires(Robot.intake);
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
		Robot.intake.setIntakeSpeed(joystick.getLeftStickRaw_Y());
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
