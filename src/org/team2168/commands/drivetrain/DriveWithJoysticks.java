package org.team2168.commands.drivetrain;

import org.team2168.Robot;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows the joysticks to pass values to control the speed of both left and right motors.
 */
public class DriveWithJoysticks extends Command {

	F310 joystick;

	public DriveWithJoysticks(F310 joystick) {
		this.joystick = joystick;
		requires(Robot.drivetrain);
	}

	/**
	 * This method runs the first time the command is run
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		Robot.drivetrain.tankDrive(0.65 * joystick.getLeftStickRaw_Y(),
				0.65 * joystick.getRightStickRaw_Y());
	}

	/**
	 * This method ends the command when it returns true
	 */
	protected boolean isFinished() {
		return false;
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
