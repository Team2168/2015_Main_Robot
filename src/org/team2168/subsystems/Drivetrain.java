package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon leftMotor;
	Talon rightMotor;
	
	/**
	 * This method instantiates the motors
	 */
	public Drivetrain(){
		
		leftMotor = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTORS);
		rightMotor = new Talon(RobotMap.DRIVETRAIN_LEFT_MOTORS);
		
	}

	/**
	 * This method sets the default command so it always drives with the joysticks
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    /**
     * This method drives the left motors in the chassis.
     * @param leftSpeed the speed to drive the motor (speeds -1 to 1, positive is forward, negative is backwards)
     */
    public void driveLeft(double leftSpeed){
    	leftMotor.set(leftSpeed);
    }
    
    /**
     * This method drives the right motors in the chassis.
     * @param rightSpeed the speed to drive the motor (speeds -1 to 1, positive is forward, negative is backwards)
     */
    public void driveRight(double rightSpeed){
    	rightMotor.set(rightSpeed);
    }
    
    /**
     * This method drives both motors in the chassis.
     * @param leftSpeed the speed to drive the left motor (speeds -1 to 1, positive is forward, negative is backwards)
     * @param rightSpeed the speed to drive the right motor (speeds -1 to 1, positive is forward, negative is backwards)
     */
    public void tankDrive(double leftSpeed, double rightSpeed){
    	leftMotor.set(leftSpeed);
    	rightMotor.set(rightSpeed);
    }
    
    public void driveStop(){
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
}

