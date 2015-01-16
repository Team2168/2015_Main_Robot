package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

/**
 * Intake subystem controlls the intake motors and solenoids. 
 * @author Vittorio Papandrea
 */
public class Intake extends Subsystem {
	
	DoubleSolenoid rightLeftIntake;

	Talon rightLeftMotor;
	
	public Intake() {
		rightLeftIntake = new DoubleSolenoid(RobotMap.INTAKE_DOUBLE_SOLENOID_FORWARD, 
										 	 	RobotMap.INTAKE_DOUBLE_SOLENOID_REVERSE);
		rightLeftMotor 	= new Talon(RobotMap.INTAKE_MOTORS);
		
	}
	
	/**
	 * Releases the intake from intaking position
	 */
	public void releaseIntake() {
		rightLeftIntake.set(Value.kReverse);
	}
	
	/**
	 * Actuates the intake into intaking position
	 */
	public void actuateIntake() {
		rightLeftIntake.set(Value.kForward);
	}
	
	/**
	 * runs the intake motors in, making the tote move in toward the lift
	 */
	public void runIntakeIn() {
		setIntakeSpeed(1);
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
		setIntakeSpeed(-1);
	}
	
	/**
	 * Sets the intake Speed of the motors. 
	 * @param speed 1 to 0 Tote In. 0 - -1 Tote Out
	 */
	public void setIntakeSpeed(double speed) {	
		rightLeftMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

