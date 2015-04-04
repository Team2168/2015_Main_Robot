package org.team2168.commands.lift;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.Debouncer;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ZeroLift extends Command {

	private final double LIFT_SPEED = 0.50;
	private static Debouncer stalled;

	public ZeroLift() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.lift);
		stalled = new Debouncer(RobotMap.LIFT_STALL_PERIOD); //time to sustain stall before stopping
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Drive down at fixed speed
		Robot.lift.drive(-LIFT_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//Check if the lift is moving down too slow If it does, kill the SCHEDULER!
		double rate = Robot.lift.getRate();
		stalled.update(rate < 0 && rate > RobotMap.LIFT_STALL_RATE); //rate is negative, and moving slow for period of time, rate is inches/sec

		if(Robot.isAutoMode() && Robot.lift.isLiftLowering() && stalled.getStatus()) {
			//Kill the scheduler :(
			Scheduler.getInstance().disable();
			Scheduler.getInstance().removeAll();
		}

		return Robot.lift.isFullyLowered();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.zeroPosition();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	/**
	 *
	 * @return true when the left intake motor is over current
	 */
	public static boolean leftMotorOverCurrent() {
		if(Robot.lift.isLiftLowering()) {
			//If the lift is lowering, use the smaller current limit
			return Robot.pdp.getChannelCurrent(RobotMap.LIFT_LEFT_MOTOR_PDP)
					> RobotMap.LIFT_OVER_CURRENT_LOWER;
		} else {
			//otherwise default to using the higher (raise) current limit
			return Robot.pdp.getChannelCurrent(RobotMap.LIFT_LEFT_MOTOR_PDP)
					> RobotMap.LIFT_OVER_CURRENT_RAISE;
		}
	}

	/**
	 *
	 * @return true when the right intake motor is over current
	 */
	public static boolean rightMotorOverCurrent() {
		if(Robot.lift.isLiftLowering()) {
			//If the lift is lowering, use the smaller current limit
			return Robot.pdp.getChannelCurrent(RobotMap.LIFT_RIGHT_MOTOR_PDP)
					> RobotMap.LIFT_OVER_CURRENT_LOWER;
		} else {
			//otherwise default to using the higher (raise) current limit
			return Robot.pdp.getChannelCurrent(RobotMap.LIFT_RIGHT_MOTOR_PDP)
					> RobotMap.LIFT_OVER_CURRENT_RAISE;
		}
	}

	public static boolean liftStalled() {
		return stalled.getStatus();
	}
}
