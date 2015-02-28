package org.team2168.subsystems;

import org.team2168.OI;
import org.team2168.RobotMap;
import org.team2168.commands.winch.WinchWithJoystick;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The winch subsystem.
 */
public class Winch extends Subsystem {

	private static Winch instance = null;
	private static Victor winchMotor;
	//	private static Encoder winchEncoder;

	private static final boolean MOTOR_INVERED = false;

	/**
	 * A private constructor to prevent multiple instances of the subsystem from
	 * being created.
	 */
	private Winch() {
		winchMotor = new Victor(RobotMap.WINCH_MOTOR);
		//		winchEncoder = new Encoder(RobotMap.WINCH_ENCODER_A,
		//				RobotMap.WINCH_ENCODER_B);
	}

	/**
	 * @return an instance of the subsystem
	 */
	public static Winch getInstance() {
		if (instance == null) {
			instance = new Winch();
		}

		return instance;
	}

	/**
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new WinchWithJoystick(OI.driverJoystick));
	}

	/**
	 * Drive the winch motors.
	 *
	 * @param speed
	 *            Value from -1.0 to 1.0, positive values winch inward.
	 */
	public void drive(double speed) {

		double temp = speed;
		if (MOTOR_INVERED)
			temp = -speed;

		winchMotor.set(temp);
	}

	/**
	 * Get the distance the winch motor has turned since the last reset
	 *
	 * @return distance
	 */
	public double getDistance() {
		//		return winchEncoder.getDistance();
		return 0.0;
	}

	/**
	 * Reset the encoder
	 */
	public void resetEncoder() {
		//		winchEncoder.reset();
		;
	}
}
