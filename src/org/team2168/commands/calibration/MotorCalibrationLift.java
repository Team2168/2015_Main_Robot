package org.team2168.commands.calibration;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibrationLift extends Command {
	
	private double oscillatingValue;
	private double valueSign;
	private double completeOscillations;
	private boolean isFinished;

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
    	completeOscillations = 0;
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Oscillates speed of lift to maximum and minimum back and forth 3 times
     * for calibration.
     */
    protected void execute() {
    	Robot.lift.drive(oscillatingValue);
    	oscillatingValue = oscillatingValue + (valueSign * .05);
    	if (completeOscillations > 2) {
    		isFinished = true;
    		Robot.lift.drive(0);
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
