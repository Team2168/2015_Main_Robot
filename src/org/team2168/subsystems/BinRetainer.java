package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The ARCB subsystem. Extends and retracts the bin arms.
 *
 * This class requires the following wiring:
 *   - the right arm is on the M+ output
 *   - the left arm is on the M- output
 */
public class BinRetainer extends Subsystem {

	private static BinRetainer instance = null;
	private static Relay solenoids;
	

	private BinRetainer() {
		solenoids = new Relay(RobotMap.BIN_RETAINER_RELAY);
		
	}

	public static BinRetainer getInstance() {
		if(instance == null) {
			instance = new BinRetainer();
		}
		return instance;
	}

	/**
	 * Retracts the left arm.
	 */
	public void retractBinRetainer() {
			solenoids.set(Relay.Value.kOff);
	}

	/**
	 * Extends the left arm.
	 */
	public void engageBinRetainer() {
			solenoids.set(Relay.Value.kForward);
		
	}
	
	/**
	 * @return True when the left arm is retracted
	 */
	public boolean isRetracted() {
		return solenoids.get() == Relay.Value.kOff;
	}

	/**
	 * @return True when the left arm is extended
	 */
	public boolean isExtended() {
		return solenoids.get() == Relay.Value.kForward;
	}




	public void initDefaultCommand() {
	}

}
