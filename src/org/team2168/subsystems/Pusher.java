package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain subsystem.
 */
public class Pusher extends Subsystem {


	private static Pusher instance = null;
	private static DoubleSolenoid pushSolenoid;


	private Pusher() {
		pushSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN_ID,
				RobotMap.PUSHER_SOLENOID_FORWARD, RobotMap.PUSHER_SOLENOID_REVERSE);
	}

	public static Pusher getInstance() {
		if(instance == null) {
			instance = new Pusher();
		}
		return instance;
	}

	/**
	 * Enables the pusher to push the totes out
	 */
	public void pushOut() {
		pushSolenoid.set(Value.kForward);
	}

	/**
	 * Retracts the pusher to intake totes again
	 */
	public void retractPusher() {
		pushSolenoid.set(Value.kReverse);
	}

	public void initDefaultCommand() {
	}

}
