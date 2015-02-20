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
	private boolean rightMotor;
	private boolean leftMotor;
	

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
    	rightMotor = true;
    	leftMotor = false;
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Oscillates the value of the speed of the intake to its maximum and minimum value 3 times 
     * for the left intake motor the right = intake motor, individually.
     */
    protected void execute() {
    	if (rightMotor == true) {
    		Robot.intake.setRightIntakeSpeed(oscillatingValue);
    		oscillatingValue = oscillatingValue + (valueSign * .05);
    		if (completeOscillations > 2) {
    			leftMotor = true;
    			Robot.intake.setRightIntakeSpeed(0);
    			rightMotor = false;
    		}
    		if (oscillatingValue == 1) {
    			valueSign = -1;
    		}
    		if (oscillatingValue == -1) {
    			valueSign = 1;
    			completeOscillations = completeOscillations + 1;
    		}
    	}
    	if (leftMotor == true) {
    		completeOscillations = 0;
    		Robot.intake.setLeftIntakeSpeed(oscillatingValue);
    		oscillatingValue = (valueSign * .05);
    		if (completeOscillations > 2) {
    			Robot.intake.setLeftIntakeSpeed(0);
    			leftMotor = false;
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
