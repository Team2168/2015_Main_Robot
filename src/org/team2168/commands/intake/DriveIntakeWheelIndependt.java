package org.team2168.commands.intake;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveIntakeWheelIndependt extends Command {

	private double left;
	private double right;
	
	/*
	 * 
	 * Right - (+,-)
	 * Left - (-,+)
	 * 
	 */
	
    public DriveIntakeWheelIndependt(double left, double right) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	this.left = left;
    	this.right = right;
    }

    // Called just before this Command runs the first times
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setLeftIntakeSpeed(left);
    	Robot.intake.setRightIntakeSpeed(right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
