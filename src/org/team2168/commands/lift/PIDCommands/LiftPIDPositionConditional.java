
package org.team2168.commands.lift.PIDCommands;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.Debouncer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


/**
 *
 * @author Vittorio
 */
public class LiftPIDPositionConditional extends Command {

	private double setPoint;
	private double speed;
	private static Debouncer stalled;
	private static double error = 1.5;
	
	public LiftPIDPositionConditional() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
		this.setPoint = Robot.lift.liftController.getSetPoint();
		stalled = new Debouncer(RobotMap.LIFT_STALL_PERIOD); //time to sustain stall before stopping
	}

	public LiftPIDPositionConditional(double setPoint){
		this();
		this.speed = 1;
		this.setPoint = setPoint;
	}

	public LiftPIDPositionConditional(double setPoint, double speed){
		this();
		this.speed = speed;
		this.setPoint = setPoint;
	}
	
	public LiftPIDPositionConditional(double setPoint, double speed, double error) {
		this(setPoint, speed);
		this.error = error;
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		Robot.lift.liftController.reset();
		if (Robot.intake.isTotePresent())
			Robot.lift.liftController.setSetPoint(8);
		else
			Robot.lift.liftController.setSetPoint(0);
		Robot.lift.liftController.setMaxPosOutput(speed);
		Robot.lift.liftController.setMaxNegOutput(-speed);
		Robot.lift.liftController.setAcceptErrorDiff(error); //inches

		Robot.lift.liftController.Enable();
	}

	// Called repeatedly when this Command is scheduled to run

	protected void execute() {
		Robot.lift.drive(Robot.lift.liftController.getControlOutput());
	}

	/*
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		//Check if the lift is moving down too slow If it does, kill the SCHEDULER!
		double rate = Robot.lift.getRate();
		stalled.update(rate < 0 && rate > RobotMap.LIFT_STALL_RATE); //rate is negative, and moving slow for period of time, rate is inches/sec

		if(Robot.isAutoMode() && Robot.lift.isLiftLowering() && stalled.getStatus()) {
			//Kill the scheduler :(
			Scheduler.getInstance().disable();
			Scheduler.getInstance().removeAll();
		}

		//TODO Should the command be stopped????????!?!?!?!?!? after PID is tuned
		return Robot.lift.liftController.isFinished();
	}

	// Called once after isFinished returns true

	protected void end() {
		Robot.lift.liftController.Pause();
		Robot.lift.drive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run

	protected void interrupted() {
		end();
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
