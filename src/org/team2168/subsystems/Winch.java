package org.team2168.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The winch subsystem.
 */
public class Winch extends Subsystem {

	private static Winch instance = null;

	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Winch() {

	}

	/**
	 * @return an instance of the subsystem
	 */
	public static Winch getInstance() {
		if(instance == null) {
			instance = new Winch();
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
	 * Drive the winch motors.
	 * @param speed Value from -1.0 to 1.0, positive values winch inward.
	 */
	public void drive(double speed) {
		//TODO: implement
	}
}


