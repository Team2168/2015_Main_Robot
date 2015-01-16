package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Intake extends Subsystem {
	
	DoubleSolenoid leftIntake;
	DoubleSolenoid rightIntake;
	
	Talon leftMotor;
	Talon rightMotor;
	
	public Intake() {
		rightIntake 	= new DoubleSolenoid(RobotMap.RIGHT_INTAKE_DOUBLE_SOLENOID_FORWARD, 
										 	 RobotMap.RIGHT_INTAKE_DOUBLE_SOLENOID_REVERSE);
		leftIntake 		= new DoubleSolenoid(RobotMap.LEFT_INTAKE_DOUBLE_SOLENOID_FORWARD,
											 RobotMap.LEFT_INTAKE_DOUBLE_SOLENOID_REVERSE);
		
		rightMotor = new Talon(RobotMap.RIGHT_INTAKE_MOTOR);
		leftMotor = new Talon(RobotMap.LEFT_INTAKE_MOTOR);
		
	}
	
	public void releaseIntake() {
		rightIntake.set(Value.kReverse);
		leftIntake.set(Value.kReverse);
	}
	
	public void actuateIntake() {
		rightIntake.set(Value.kForward);
		leftIntake.set(Value.kForward);
	}
	
	public void runIntake() {
		rightMotor.set(1);
		leftMotor.set(1);
	}
	
	public void stopIntake() {
		rightMotor.set(0);
		leftMotor.set(0);
	}
	
	public void setIntakeSpeed(double speed) {
		rightMotor.set(speed);
		leftMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

