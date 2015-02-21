package org.team2168.commands.lift;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the lift with a joystick.
 */
public class LiftWithJoystick extends Command {

	Joystick joystick;
	int axis;
	
	public LiftWithJoystick(Joystick joystick, int axis) {
		this.joystick = joystick;
		this.axis = axis;
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
		
		if(Math.abs(joystick.getRawAxis(axis)) > RobotMap.LIFT_MIN_SPEED) 
		{
			Robot.lift.disableBrake();
			Robot.lift.drive(joystick.getRawAxis(axis)); 
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
