package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The ARCB subsystem. Extends and retracts the bin arms.
 * 
 * This class requires the following wiring:
 *   - the right arm is on the M+ output
 *   - the left arm is on the M- output
 */
public class ARCB extends Subsystem {

	private static ARCB instance = null;
	private static Relay solenoids;

	private ARCB() {
		solenoids = new Realy(RobotMap.ARCB_RELAY);
	}

	public static ARCB getInstance() {
		if(instance == null) {
			instance = new ARCB();
		}
		return instance;
	}

	/**
	 * Retracts the left arm.
	 */
	public void retractLeft() {
		if(isRightRetracted()) {
			solenoids.set(Relay.Value.kOff);
		} else {
			solenoids.set(Relay.Value.kForward);
		}
	}

	/**
	 * Extends the left arm.
	 */
	public void extendLeft() {
		if(isRightRetracted()) {
			solenoids.set(Relay.Value.kReverse);
		} else {
			solenoids.set(Relay.Value.kOn);
		}
	}
	
	/**
	 * Retracts the right arm.
	 */
	public void retractRight() {
		if(isLeftRetracted()) {
			solenoids.set(Relay.Value.kOff);
		} else {
			solenoids.set(Relay.Value.kReverse);
		}
	}

	/**
	 * Extends the right arm.
	 */
	public void extendRight() {
		if(isLeftRetracted()) {
			solenoids.set(Relay.Value.kForward);
		} else {
			solenoids.set(Relay.Value.kOn);
		}
	}

	/**
	 * @return True when the left arm is retracted
	 */
	public boolean isLeftRetracted() {
		return solenoids.get() == Relay.Value.kOff || solenoids.get() == Relay.Value.kForward;
	}
	
	/**
	 * @return True when the left arm is extended
	 */
	public boolean isLeftExtended() {
		return !isLeftRetracted();
	}
	
	/**
	 * @return True when the right arm is retracted
	 */
	public boolean isRightRetracted() {
		return solenoids.get() == Relay.Value.kOff || solenoids.get() == Relay.Value.kReverse;
	}
	
	/**
	 * @return True when the right arm is extended
	 */
	public boolean isRightExtended() {
		return !isRightRetracted();
	}
	
	
	public void initDefaultCommand() {
	}

}
