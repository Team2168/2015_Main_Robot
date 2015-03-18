
package org.team2168.commands.lift.PIDCommands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Vittorio
 */
public class LiftPIDPosition extends Command {

	private double setPoint;
	private double speed;
	
    public LiftPIDPosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.lift);
    	this.setPoint = Robot.lift.liftController.getSetPoint();
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

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
		//TODO Should the command be stopped????????!?!?!?!?!? after PID is tuned
    	return Robot.lift.liftController.isFinished();
		//return false;
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
}