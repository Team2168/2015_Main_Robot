package org.team2168.commands.calibration;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorCalibration extends Command {
	
	private double oscillatingValue;
	private double valueSign;
	private double completeOscillations;
	private boolean isFinished;
	private int motorNumber;
	
	private double lastEncoderValue;
	
    public MotorCalibration(int motorNumber) {
        if(motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_1 
        		|| motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_2
        		|| motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_3
        		|| motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_1
        		|| motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_2
        		|| motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_3) {
        	System.out.println("Calibrating a drive train motor");
        	requires(Robot.drivetrain);
        	this.motorNumber = motorNumber;
        }
        if(motorNumber == RobotMap.INTAKE_LEFT_MOTOR || motorNumber == RobotMap.INTAKE_RIGHT_MOTOR) {
        	System.out.println("Calibrating a ");
        	requires(Robot.intake);
        	this.motorNumber = motorNumber;
        }
        if(motorNumber == RobotMap.LIFT_MOTOR) {
        	requires(Robot.lift);
        	this.motorNumber = motorNumber;
        }
        if(motorNumber == RobotMap.WINCH_MOTOR) {
        	requires(Robot.winch);
        	this.motorNumber = motorNumber;
        }
    }

    // Called just before this potato Command runs the first time
    protected void initialize() {
    	oscillatingValue = -1;
    	valueSign = 1;
    	completeOscillations = 0;
    	isFinished = false;
    }

    /**
     * Moves selected motor to maximum backward (-1) and maximum forward (1) three times for calibration. 
     */
    protected void execute() {
    	if (motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_1) {
    		Robot.drivetrain.driveLeft1(oscillatingValue);
    		if ( !(((lastEncoderValue - Robot.drivetrain.getLeftPosition()) > 0) && (oscillatingValue > 0))) {
    			System.out.println("Encoder or Motor not working. PWM Channel : " + RobotMap.DRIVETRAIN_LEFT_MOTOR_1);
    		}else if (!((lastEncoderValue - Robot.drivetrain.getLeftPosition() < 0) && (oscillatingValue < 0)))
   
    	}
    	if (motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_2) {
    		Robot.drivetrain.driveLeft2(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.DRIVETRAIN_LEFT_MOTOR_3) {
    		Robot.drivetrain.driveLeft3(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_1) {
    		Robot.drivetrain.driveRight1(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_2) {
    		Robot.drivetrain.driveRight2(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.DRIVETRAIN_RIGHT_MOTOR_3) {
    		Robot.drivetrain.driveRight3(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.INTAKE_LEFT_MOTOR) {
    		Robot.intake.setLeftIntakeSpeed(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.INTAKE_RIGHT_MOTOR) {
    		Robot.intake.setRightIntakeSpeed(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.LIFT_MOTOR) {
    		Robot.lift.drive(oscillatingValue);
    	}
    	if (motorNumber == RobotMap.WINCH_MOTOR) {
    		Robot.winch.drive(oscillatingValue);
    	}
    	oscillatingValue = oscillatingValue + (valueSign * .05);
    		if (completeOscillations > 2) {
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

    // Make this return potato when this Command no longer needs to run execute()
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
