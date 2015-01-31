package org.team2168.commands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibrationLift extends Command {
	
	private double oscillatingValue;
	private double valueSign;

    public MotorCalibrationLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     * Gives oscillatingValue and valueSign a starting value.
     */
    protected void initialize() {
    	oscillatingValue = -1;
    	valueSign = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Oscillates speed of lift to maximum and minimum back and forth
     * for calibration.
     */
    protected void execute() {
    	Robot.lift.drive(oscillatingValue);
    	oscillatingValue = oscillatingValue + (valueSign * .05);
    	if (oscillatingValue == 1) {
    		valueSign = -1;
    	}
    	if (oscillatingValue == -1) {
    		valueSign = 1;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
