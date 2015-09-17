package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RCFingers subsystem
 * Grabs a sideways bin in order to right it
 */
public class RCFingers extends Subsystem {

	private static RCFingers instance = null;
	private Relay rcfingers;

	/**
	 * This method instantiates the double solenoid.
	 * Private to prevent creating more than one instance of this subsystem.
	 */
	private RCFingers(){
		rcfingers = new Relay(RobotMap.RCFINGERS_RELAY);
	}
	
	/**
	 * @return an instance of the RCFingers
	 */
	public static RCFingers getInstance() {
		if (instance==null) {
			instance = new RCFingers();
		}
		return instance;
	}
	
	/**
	 * @return True when the left finger is retracted
	 */
	public boolean isLeftRetracted() {
		return rcfingers.get() == Relay.Value.kOff || rcfingers.get() == Relay.Value.kForward;
	}

	/**
	 * @return True when the left finger is extended
	 */
	public boolean isLeftExtended() {
		return !isLeftRetracted();
	}

	/**
	 * @return True when the right finger is retracted
	 */
	public boolean isRightRetracted() {
		return rcfingers.get() == Relay.Value.kOff || rcfingers.get() == Relay.Value.kReverse;
	}

	/**
	 * @return True when the right finger is extended
	 */
	public boolean isRightExtended() {
		return !isRightRetracted();
	}
	
	/**
	 * Retracts the left finger
	 */
	public void retractLeft() {
		if(isRightRetracted()) {
			rcfingers.set(Relay.Value.kOff);
		} else {
			rcfingers.set(Relay.Value.kForward);
		}
	}

	/**
	 * Extends the left finger
	 */
	public void extendLeft() {
		if(isRightRetracted()) {
			rcfingers.set(Relay.Value.kReverse);
		} else {
			rcfingers.set(Relay.Value.kOn);
		}
	}

	/**
	 * Retracts the right finger
	 */
	public void retractRight() {
		if(isLeftRetracted()) {
			rcfingers.set(Relay.Value.kOff);
		} else {
			rcfingers.set(Relay.Value.kReverse);
		}
	}

	/**
	 * Extends the right finger
	 */
	public void extendRight() {
		if(isLeftRetracted()) {
			rcfingers.set(Relay.Value.kForward);
		} else {
			rcfingers.set(Relay.Value.kOn);
		}
	}

	public void initDefaultCommand() {
	}
	
}
