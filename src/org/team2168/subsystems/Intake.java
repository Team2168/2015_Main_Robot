package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The intake subsytem controls the intake motors and solenoids. 
 * @author Vittorio Papandrea
 */
public class Intake extends Subsystem {
	
	private static Intake instance = null;
	private DoubleSolenoid rightLeftIntake;
	private Talon rightLeftMotor;
	private static DigitalInput leftLimitSwitch;
	private static DigitalInput rightLimitSwitch;

	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Intake() {
		rightLeftIntake = new DoubleSolenoid(RobotMap.INTAKE_DOUBLE_SOLENOID_FORWARD, 
												RobotMap.INTAKE_DOUBLE_SOLENOID_REVERSE);
		rightLeftMotor 	= new Talon(RobotMap.INTAKE_MOTORS);

		leftLimitSwitch 	= new DigitalInput(RobotMap.LEFT_TOTE_SWITCH);
		rightLimitSwitch 	= new DigitalInput(RobotMap.RIGHT_TOTE_SWITCH);
		
	}
	
	/**
	 * @return the instance of the subsystem
	 */
	public static Intake getInstance() {
		if(instance == null) {
			instance = new Intake();
		}
		
		return instance;
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
	public void engageIntake() {
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

	/**
	 * Check if there's soemthing in the intake.
	 * @return true when an object is in the intake.
	 */
	public Boolean isTotePresent() {
		if (leftLimitSwitch.get() || rightLimitSwitch.get()) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Set the default command for the subsystem
	 */
    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

