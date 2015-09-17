package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RC Fingers subsystem. Extends and retracts the RC Finger
 */
public class RCFingers extends Subsystem {

	private static RCFingers instance = null;
	private static Relay solenoids;
	

	private RCFingers() {
		solenoids = new Relay(RobotMap.RCFINGERS_RELAY);
		
	}

	public static RCFingers getInstance() {
		if(instance == null) {
			instance = new RCFingers();
		}
		return instance;
	}

	/**
	 * Retracts both fingers
	 */
	public void retractRCFingers() {
			solenoids.set(Relay.Value.kOff);
	}

	/**
	 * Extends both fingers
	 */
	public void engageRCFingers() {
			solenoids.set(Relay.Value.kForward);
		
	}
	
	/**
	 * @return True when the fingers are retracted
	 */
	public boolean isRetracted() {
		return solenoids.get() == Relay.Value.kOff;
	}

	/**
	 * @return True when the fingers are extended
	 */
	public boolean isExtended() {
		return solenoids.get() == Relay.Value.kForward;
	}




	public void initDefaultCommand() {
	}

}
