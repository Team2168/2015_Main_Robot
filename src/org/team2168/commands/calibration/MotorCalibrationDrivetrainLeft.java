package org.team2168.commands.calibration;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibrationDrivetrainLeft extends Command {
	
	private double oscillatingValue;
	private double valueSign;
	private double completeOscillations;
	private boolean isFinished;

    public MotorCalibrationDrivetrainLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	oscillatingValue = -1;
    	valueSign = 1;
    	completeOscillations = 0;
    	isFinished = false;
    }

    /**
     * Oscillates the motors back and forth 3 times for calibration
     */
    protected void execute() {
    	Robot.drivetrain.driveLeft(oscillatingValue);
    	oscillatingValue = oscillatingValue + (.05 * valueSign);
    	if (completeOscillations > 2) {
    		isFinished = true;
    		Robot.drivetrain.driveLeft(0);
    	}
    	if (oscillatingValue == 1) {
    		valueSign = -1;
    	}
    	if (oscillatingValue == -1) {
    		valueSign = 1;
    		completeOscillations = completeOscillations + 1;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
