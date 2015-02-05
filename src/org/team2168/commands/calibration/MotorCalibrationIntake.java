package org.team2168.commands.calibration;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibrationIntake extends Command {
	
	private double oscillatingValue;
	private double valueSign;
	private double completeOscillations;
	private boolean isFinished;

    public MotorCalibrationIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     * Gives oscillatingValue and valueSign beginning values for the calibration command.
     */
    protected void initialize() {
    	oscillatingValue = -1;
    	valueSign = 1;
    	completeOscillations = 0;
    	isFinished = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Oscillates the value of the speed of the lift to its maximum and minimum 3 times
     */
    protected void execute() {
    	Robot.intake.setIntakeSpeed(oscillatingValue);
    	oscillatingValue = oscillatingValue + (.05 * valueSign);
    	if (completeOscillations > 2) {
    		isFinished = true;
    		Robot.intake.setIntakeSpeed(0);
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
