package org.team2168.commands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibrationDrivetrainLeft extends Command {
	
	private double oscillatingValue;
	private double valueSign;

    public MotorCalibrationDrivetrainLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	oscillatingValue = -1;
    	valueSign = 1;
    }

    /**
     * Oscillates the motors back and forth for calibration
     */
    //TODO Make this not infinite
    protected void execute() {
    	Robot.drivetrain.driveLeft(oscillatingValue);
    	oscillatingValue = oscillatingValue + (.05 * valueSign);
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
