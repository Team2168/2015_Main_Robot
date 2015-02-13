package org.team2168.commands.calibration;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibrationDrivetrainRight extends Command {

	private double oscillatingValue;
	private double valueSign;
	private double completeOscillations;
	private boolean isFinished;
	private boolean motor1;
	private boolean motor2;
	private boolean motor3;

    public MotorCalibrationDrivetrainRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	oscillatingValue = -1;
    	valueSign = 1;
    	completeOscillations = 0;
    	isFinished = false;
    	motor1 = true;
    	motor2 = false;
    	motor3 = false;
    }

    /**
     * Oscillates the motors back and forth 3 times for calibration
     */
    protected void execute() {
    	if (motor1 == true) {
    		Robot.drivetrain.driveRight1(oscillatingValue);
    		oscillatingValue = oscillatingValue + (.05 * valueSign);
    		if (completeOscillations > 2) {
    			motor2 = true;
    			Robot.drivetrain.driveRight(0);
    			motor1 = false;
    		}
    		if (oscillatingValue == 1) {
    			valueSign = -1;
    		}
    		if (oscillatingValue == -1) {
    			valueSign = 1;
    			completeOscillations = completeOscillations + 1;
    		}
    	}
    	
    	if (motor2 == true) {
    		completeOscillations = 0;
    		Robot.drivetrain.driveRight2(oscillatingValue);
    		oscillatingValue = oscillatingValue + (.05 * valueSign);
    		if (completeOscillations > 2) {
    			motor3 = true;
    			Robot.drivetrain.driveRight2(0);
    			motor2 = false;
    		}
    		if (oscillatingValue == 1) {
    			valueSign = -1;
    		}
    		if (oscillatingValue == -1) {
    			valueSign = 1;
    			completeOscillations = completeOscillations + 1;
    		}
    	}
    	
    	if (motor3 == true) {
    		completeOscillations = 0;
    		Robot.drivetrain.driveRight3(oscillatingValue);
    		oscillatingValue = oscillatingValue + (.05 * valueSign);
    		if (completeOscillations > 2) {
    			Robot.drivetrain.driveRight3(0);
    			isFinished = true;
    		}
    		if (oscillatingValue == 1) {
    			valueSign = -1;
    		}
    		if (oscillatingValue == -1) {
    			valueSign = 1;
    			completeOscillations = completeOscillations + 1;
    		}
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
