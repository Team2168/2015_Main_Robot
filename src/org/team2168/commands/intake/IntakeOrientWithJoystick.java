package org.team2168.commands.intake;

import org.team2168.Robot;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOrientWithJoystick extends Command {

	private F310 joystick;
	private double y, x;
	private static final double DEADBAND_X = 0.5;
	private static final double DEADBAND_Y = 0.1;

	public IntakeOrientWithJoystick(F310 joystick) {
		this.joystick = joystick;
		requires(Robot.intake);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run.
	 * Pressing left/right on the stick will cause the tote to rotate.
	 */
	protected void execute() {
		y = joystick.getLeftStickRaw_Y();
		x = joystick.getLeftStickRaw_X();

		//add deadband
		if(Math.abs(y) < DEADBAND_Y) {
			y = 0.0;
		}

		if(Math.abs(x) < DEADBAND_X) {
			x = 0.0;
		} else if (x > 0) {
			//positive x value greater than deadband, coerce range toward zero
			x = x - DEADBAND_X;
		} else {
			//negative x value greater than deadband, coerce range toward zero
			x = x + DEADBAND_X;
		}

		Robot.intake.setLeftIntakeSpeed(-y+x);
		Robot.intake.setRightIntakeSpeed(-(y+x));
	}

	/**
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
		Robot.intake.stopIntake();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	protected void interrupted() {
	}
}
