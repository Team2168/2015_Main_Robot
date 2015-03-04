package org.team2168.commands.lift;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the lift with a joystick.
 */
public class LiftWithJoystick extends Command {

	F310 joystick;
	private double joyval;
	
	public LiftWithJoystick(F310 joystick) {
		this.joystick = joystick;
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
		joyval = joystick.getRightStickRaw_Y();
		if (Math.abs(joyval) < RobotMap.LIFT_MIN_SPEED)
			joyval = 0;
		
		if (joyval > 0 && !Robot.lift.isFullyRaised()) {
			Robot.lift.disableBrake();
			Robot.lift.drive(joyval);
		}
		else if (joyval < 0 && !Robot.lift.isFullyLowered()) {
			Robot.lift.disableBrake();
			Robot.lift.drive(joyval);
		}
		else
		{
			Robot.lift.enableBrake();
			Robot.lift.drive(0);
		}
		
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
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
	}
}
