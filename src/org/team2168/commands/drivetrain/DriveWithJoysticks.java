package org.team2168.commands.drivetrain;

import org.team2168.OI;
import org.team2168.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows the joysticks to pass values to control the speed of both left and right motors.
 */
public class DriveWithJoysticks extends Command {

	Joystick joystick;
	int leftAxis;
	int rightAxis;
	
	public DriveWithJoysticks(Joystick joystick, int leftAxis, int rightAxis) {
		this.joystick = joystick;
		this.leftAxis = leftAxis;
		this.rightAxis = rightAxis;
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
		Robot.drivetrain.tankDrive(joystick.getRawAxis(leftAxis),
				joystick.getRawAxis(rightAxis));
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
