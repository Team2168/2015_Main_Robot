package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.gripper.ReleaseGripper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Gripper subsystem
 */
public class Gripper extends Subsystem {

	private static Gripper instance = null;
	private DoubleSolenoid gripper;

	/**
	 * This method instantiates the double solenoid.
	 * Private to prevent creating more than one instance of this subsystem.
	 */
	private Gripper(){
		gripper = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.GRIPPER_DOUBLE_SOLENOID_FORWARD,
				RobotMap.GRIPPER_DOUBLE_SOLENOID_REVERSE);
	}

	/**
	 * @return an instance of the gripper
	 */
	public static Gripper getInstance() {
		if (instance==null) {
			instance = new Gripper();
		}
		return instance;
	}

	/**
	 * Engages the gripper
	 */
	public void engageGripper() {
		gripper.set(Value.kForward);
	}

	/**
	 * Disengages the gripper
	 */
	public void releaseGripper() {
		gripper.set(Value.kReverse);
	}

	/**
	 *
	 * @return true when the gripper is engaged
	 */
	public boolean isGripperEngaged() {
		return gripper.get() == Value.kForward;
	}

	/**
	 *
	 * @return true when the gripper is disengaged
	 */
	public boolean isGripperDisengaged() {
		return gripper.get() == Value.kReverse;
	}


	/**
	 * Set the default command for a subsystem here.
	 */
	public void initDefaultCommand() {
		//We don't want a command that finishes to be the default...
		//Call the command on startup if you want to establish component position.
		//setDefaultCommand(new ReleaseGripper());
	}
}
