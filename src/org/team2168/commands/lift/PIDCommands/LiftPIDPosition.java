
package org.team2168.commands.lift.PIDCommands;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.Debouncer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 * @author Vittorio
 */
public class LiftPIDPosition extends Command {

	private double setPoint;
	private double speed;
	private static Debouncer stalled;
	
    public LiftPIDPosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.lift);
    	this.setPoint = Robot.lift.liftController.getSetPoint();
    	stalled = new Debouncer(0.25); //time to sustain stall before stopping
    }

    public LiftPIDPosition(double setPoint){
 	   this();
 	   this.speed = 1;
 	   this.setPoint = setPoint;
    }
    
    public LiftPIDPosition(double setPoint, double speed){
  	   this();
  	   this.speed = speed;
  	   this.setPoint = setPoint;
     }
     


    // Called just before this Command runs the first time
    
	protected void initialize() {
		Robot.lift.liftController.reset();
		Robot.lift.liftController.setSetPoint(setPoint);
		Robot.lift.liftController.setMaxPosOutput(speed);
		Robot.lift.liftController.setMaxNegOutput(-speed);
		Robot.lift.liftController.setAcceptErrorDiff(0.5); //inches
		
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
		//Check if the lift is drawing too much current. If it does, kill the SCHEDULER!
		stalled.update(Robot.isAutoMode() &&
				(leftMotorOverCurrent() || rightMotorOverCurrent()));

		if(stalled.getStatus()) {
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
		return Robot.pdp.getChannelCurrent(RobotMap.LIFT_LEFT_MOTOR_PDP)
				> RobotMap.LIFT_OVER_CURRENT;
	}
	
	/**
	 * 
	 * @return true when the right intake motor is over current
	 */
	public static boolean rightMotorOverCurrent() {
		return Robot.pdp.getChannelCurrent(RobotMap.LIFT_RIGHT_MOTOR_PDP)
				> RobotMap.LIFT_OVER_CURRENT;
	}
	
	public static boolean liftStalled() {
		return stalled.getStatus();
	}
}