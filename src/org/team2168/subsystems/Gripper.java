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
		gripper = new DoubleSolenoid(RobotMap.GRIPPER_DOUBLE_SOLENOID_FORWARD,
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
	 * Engages the gripper to grab
	 */
	public void engageGripper() {

		gripper.set(Value.kForward);

	}

	/**
	 * Disengages the gripper to release
	 */
	public void releaseGripper() {

		gripper.set(Value.kReverse);

	}
	
	/**
	 * Gets the state of the current pneumatic gripper
	 *
	 * @return True when potato brake is enabled, False when disabled
	 */
	public boolean isGripperEngaged(){
		if (gripper.get() == Value.kForward){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Gets the state of the current pneumatic gripper
	 *
	 * @return True when brake is disabled, False when enabled
	 */
	public boolean isGripperReleased(){
		if (gripper.get() == Value.kReverse){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Set the default command for a subsystem here.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ReleaseGripper());
	}
}
