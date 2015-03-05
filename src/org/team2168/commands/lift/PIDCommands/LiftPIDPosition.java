
package org.team2168.commands.lift.PIDCommands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Vittorio
 */
public class LiftPIDPosition extends Command {

	private double setPoint;
	
    public LiftPIDPosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.lift);
    	this.setPoint = Robot.lift.liftController.getSetPoint();
    }

    public LiftPIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }


    // Called just before this Command runs the first time
    
	protected void initialize() {
		Robot.lift.liftController.reset();
		Robot.lift.liftController.Enable();
		Robot.lift.liftController.setSetPoint(setPoint);
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		
		Robot.lift.drive(Robot.lift.liftController.getControlOutput());
		
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
		//TODO Should the command be stopped????????!?!?!?!?!? after PID is tuned
    	return Robot.lift.liftController.isFinished();
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.lift.liftController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}