package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Bin Slapper subsystem.
 */
public class BinSlapper extends Subsystem {

	private static BinSlapper instance = null;
	private static Victor slapperMotor;

	private static final boolean MOTOR_INVERED = false;

	/**
	 * A private constructor to prevent multiple instances of the subsystem from
	 * being created.
	 */
	private BinSlapper() {
		slapperMotor = new Victor(RobotMap.BIN_SLAPPER_MOTOR);
		slapperMotor.setExpiration(0.1);
		slapperMotor.setSafetyEnabled(true);
	}

	/**
	 * @return an instance of the subsystem
	 */
	public static BinSlapper getInstance() {
		if (instance == null) {
			instance = new BinSlapper();
		}

		return instance;
	}

	/**
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		//No default command
	}

	/**
	 * Drive the slapper motor.
	 *
	 * @param speed Value from -1.0 to 1.0, positive values spin clockwise.
	 */
	public void drive(double speed) {
		speed = Util.limit(speed);
		if (MOTOR_INVERED)
			speed = -speed;

		slapperMotor.set(speed);
	}
}
