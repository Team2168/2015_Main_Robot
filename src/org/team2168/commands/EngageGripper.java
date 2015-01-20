package org.team2168.commands;

import org.team2168.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * this command engages the gripper
 */
public class EngageGripper extends Command {

    public EngageGripper() {
        
    	requires(Robot.gripper);
    	
    }

    /**
     * Called first time the command is run
     */
    protected void initialize() {
    }

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
    protected void execute() {
    	
    	Robot.gripper.engageGripper();
    	
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
