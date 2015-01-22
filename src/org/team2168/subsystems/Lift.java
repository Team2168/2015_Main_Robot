package org.team2168.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;;

/**
 * The lift subsystem.
 */
public class Lift extends Subsystem {

	private static Lift instance = null;
	private Talon IntakeMotor;
	private Encoder WinchEncoder;
	private DoubleSolenoid LiftBreak;
	
	
	double currentPosition;
	
	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Lift() {
		IntakeMotor = new Talon(RobotMap.LIFT_MOTOR);
		WinchEncoder = new Encoder(RobotMap.WINCH_ENCODER_A, 
								   RobotMap.WINCH_ENCODER_B);
		LiftBreak = new DoubleSolenoid(RobotMap.LIFT_DOUBLE_SOLENOID_FORWARD,
									   RobotMap.LIFT_DOUBLE_SOLENOID_REVERSE);
	}

	/**
	 * @return an instance of the subsystem
	 */
	public static Lift getInstance() {
		if(instance == null) {
			instance = new Lift();
		}

		return instance;
	}

	/**
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Drive the lift in open loop mode.
	 * @param speed value from -1.0 to 1.0, positive drive the lift up.
	 */
	public void drive(double speed) {		
		IntakeMotor.set(speed);
	}

	/**
	 * Get the lifts position along travel.
	 * @return position in inches
	 */
	public double getPosition() {
		return WinchEncoder.getDistance();
	}

	/**
	 * Command the lift to a specific position along travel.
	 * @param position in inches.
	 */
	public void setPosition(double position) {
		double distanceToDrive = position - getPosition();
		double ABSvalue = Math.abs(distanceToDrive);
		
		if (distanceToDrive > 0) {
			setPositionDelta(ABSvalue, true);
		}else{
			setPositionDelta(ABSvalue, false);
		}
	}
	
	/**
	 * Gets the sate of the current pneumatic break
	 * @return True when break is enabled, False when break is disabled
	 */
	public boolean isBreakEnabled() {
		if (LiftBreak.get() == Value.kForward) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Enables the pneumatic break
	 */
	public void enableBreak() {
		LiftBreak.set(Value.kForward);
	}
	
	/**
	 * Disables the pneumatic break 
	 */
	public void disableBreak() {
		LiftBreak.set(Value.kReverse);
	}
	
	/**
	 * Drive the lift to a position relative to where it currently is.
	 * @param delta distance to travel in inches, positive is up
	 * @param direction True for up, False for down
	 */
	public void setPositionDelta(double delta, boolean direction) {
		if (delta > 1) {	
			disableBreak();
			if (direction) {
				IntakeMotor.set(1);
			}else {
				IntakeMotor.set(-1);
			}
		}else{
			enableBreak();
		}
		
	}

	/**
	 * Mark the current position of the lift as zero inches.
	 */
	public void zeroPosition() {
		//TODO: reset the lift position
		setPosition(0);
	}

	/**
	 * Identifies when the lift is at its highest position along travel.
	 * @return true when at upper hard stop
	 */
	public boolean upperHardStop() {
		//TODO: use sensor value
		return false;
	}

	/**
	 * Identifies when the lift is at its lowest position along travel.
	 * @return true when at the lower hard stop
	 */
	public boolean lowerHardStop() {
		//TODO: use sensor value
		return false;
	}
}

