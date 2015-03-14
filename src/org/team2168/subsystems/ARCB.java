package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain subsystem.
 */
public class ARCB extends Subsystem {


	private static ARCB instance = null;
	private static DoubleSolenoid pushSolenoid;


	private ARCB() {
		pushSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN_ID,
				RobotMap.PUSHER_SOLENOID_FORWARD, RobotMap.PUSHER_SOLENOID_REVERSE);
	}

	public static ARCB getInstance() {
		if(instance == null) {
			instance = new ARCB();
		}
		return instance;
	}

	/**
	 * Enables the pusher to push the totes out
	 */
	public void retract() {
		pushSolenoid.set(Value.kReverse);
	}

	/**
	 * Retracts the pusher to intake totes again
	 */
	public void deploy() {
		pushSolenoid.set(Value.kForward);
	}

	/**
	 * Returnes if retracted
	 * @return True when retracted
	 */
	public boolean isRetract() {
		return pushSolenoid.get() == Value.kForward;
	}
	
	/**
	 * Returnes if deployed
	 * @return True when deployed
	 */
	public boolean isDeployed() {
		return pushSolenoid.get() == Value.kReverse;
	}
	
	
	public void initDefaultCommand() {
	}

}
