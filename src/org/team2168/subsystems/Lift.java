package org.team2168.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The lift subsystem.
 */
public class Lift extends Subsystem {

	private static Lift instance = null;

	/**
	 * A private constructor to prevent multiple instances of the subsystem
	 * from being created.
	 */
	private Lift() {

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
		//TODO: drive the lift motors
	}

	/**
	 * Get the lifts position along travel.
	 * @return position in inches
	 */
	public double getPosition() {
		//TODO: get the position from the winch encoder
		return 0.0;
	}

	/**
	 * Command the lift to a specific position along travel.
	 * @param position in inches.
	 */
	public void setPosition(double position) {
		//TODO: drive the lift to the setpoint using closed loop control
	}

	/**
	 * Drive the lift to a position relative to where it currently is.
	 * @param delta distance to travel in inches, positive is up
	 */
	public void setPositionDelta(double delta) {
		//TODO: drive the lift to a new position ()
		//setPosition(current position + delta);
	}

	/**
	 * Mark the current position of the lift as zero inches.
	 */
	public void zeroPosition() {
		//TODO: reset the lift position
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

