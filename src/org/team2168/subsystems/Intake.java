package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

/**
 * @author Vittorio Papandrea
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
	
	/**
	 * Releases the intake from intaking position
	 */
	public void releaseIntake() {
		rightIntake.set(Value.kReverse);
		leftIntake.set(Value.kReverse);
	}
	
	/**
	 * Actuates the intake into intaking position
	 */
	public void actuateIntake() {
		rightIntake.set(Value.kForward);
		leftIntake.set(Value.kForward);
	}
	
	/**
	 * runs the intake motors in, making the tote move in toward the lift
	 */
	public void runIntakeIn() {
		setIntakeSpeed(-1);
	}
	
	/**
	 * stops the intake motors
	 */
	public void stopIntake() {
		setIntakeSpeed(0);
	}
	
	/**
	 * runs the intake motors out, making the tote move out of the intake.
	 */
	public void runIntakeOut() {
		setIntakeSpeed(1);
	}
	
	/**
	 * Sets the intake Speed of the motors. 
	 * @param speed -1 to 0 Reverse Motors. 0 - 1 Forward Motors
	 */
	public void setIntakeSpeed(double speed) {	
		rightMotor.set(speed);
		leftMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

