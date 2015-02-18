package org.team2168.subsystems;



import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PIDController.sensors.ADXRS453Gyro;
import org.team2168.PIDController.sensors.AverageEncoder;
import org.team2168.PIDController.sensors.FalconGyro;
import org.team2168.PIDControllers.PIDPosition;
import org.team2168.PIDControllers.PIDSpeed;
import org.team2168.commands.drivetrain.DriveWithJoysticks;
import org.team2168.utils.TCPSocketSender;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
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
