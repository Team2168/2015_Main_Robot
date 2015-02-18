package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PIDController.sensors.AverageEncoder;
import org.team2168.PIDControllers.PIDPosition;
import org.team2168.PIDControllers.PIDPosition2;
import org.team2168.commands.lift.LiftWithJoystick;
import org.team2168.utils.TCPSocketSender;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The lift subsystem.
 */
public class Lift extends Subsystem {

	private static Lift instance = null;
	private Talon intakeMotor;
	private DoubleSolenoid liftBrake;
	private double currentPosition;
	private static final double DESTINATION_TOL = 1.0; //inches

	private volatile double motorVoltage;
	
	public AverageEncoder liftEncoder;
	public PIDPosition2 liftController;
	
	TCPSocketSender TCPliftPosController;
	
	/**
	 * A private constructor to prevent multiple instances of the subsystem from
	 * being created.
	 */
	private Lift() {
		intakeMotor = new Talon(RobotMap.LIFT_MOTOR);
		liftEncoder = new AverageEncoder(RobotMap.LIFT_ENCODER_A,
				RobotMap.LIFT_ENCODER_B, RobotMap.liftEncoderPulsePerRot,
				RobotMap.liftEncoderDistPerTick,
				RobotMap.liftEncoderReverse,
				RobotMap.liftEncodingType, RobotMap.liftSpeedReturnType,
				RobotMap.liftPosReturnType, RobotMap.liftAvgEncoderVal);
		
		liftBrake = new DoubleSolenoid(RobotMap.LIFT_BRAKE_DOUBLE_SOLENOID_FORWARD,
				RobotMap.LIFT_BRAKE_DOUBLE_SOLENOID_REVERSE);
		
		liftController = new PIDPosition2("LiftPID", RobotMap.liftPUp, 
				RobotMap.liftIUp, RobotMap.liftDUp, liftEncoder,
    			RobotMap.liftPIDPeriod);
		liftController.startThread();
		
		//start TCP Servers for DEBUGING ONLY
    	TCPliftPosController = new TCPSocketSender(RobotMap.TCPServerLiftPos, liftController);
    	TCPliftPosController.start();
		
	}

	/**
	 * @return an instance of the subsystem
	 */
	public static Lift getInstance() {
		if (instance == null) {
			instance = new Lift();
		}

		return instance;
	}

	/**
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new LiftWithJoystick());
	}

	/**
	 * 
	 * @return
	 */
	public double getMotorVoltage()
	{
		return motorVoltage;
	}
	
	/**
	 * Drive the lift in open loop mode.
	 *
	 * @param speed
	 *            value from -1.0 to 1.0, positive drives the lift up.
	 */
	public void drive(double speed) {
		intakeMotor.set(speed);
		motorVoltage = Robot.pdp.getBatteryVoltage() * speed;
	}

	/**
	 * Get the lifts position along travel.
	 *
	 * @return position in inches
	 */
	public double getPosition() {
		return liftEncoder.getPos();
	}

	/**
	 * Command the lift to a specific position along travel.
	 *
	 * @param position
	 *            in inches.
	 */
	public void setPosition(double position) {
		// TODO: establish minimum and maximum distances the lift can travel.
		// Put them in RobotMap.
		// TODO: If someone inputs values which are outside the min/max, coerce
		// them to be within range.

		if (position > RobotMap.MIN_LIFT_HEIGHT && position < RobotMap.MAX_LIFT_HEIGHT) {
			double distanceToDrive = position - getPosition();
			double ABSvalue = Math.abs(distanceToDrive);

			if (distanceToDrive > 0) {
				setPositionDelta(ABSvalue, true);
			} else {
				setPositionDelta(ABSvalue, false);
			}
		} else {

			if (position > RobotMap.MAX_LIFT_HEIGHT) {
				double distanceToDrive = 76 - getPosition();
				double ABSvalue = Math.abs(distanceToDrive);

				if (distanceToDrive > 0) {
					setPositionDelta(ABSvalue, true);
				} else {
					setPositionDelta(ABSvalue, false);
				}
			}

			if (position < RobotMap.MIN_LIFT_HEIGHT) {
				double distanceToDrive = 0 - getPosition();
				double ABSvalue = Math.abs(distanceToDrive);

				if (distanceToDrive > 0) {
					setPositionDelta(ABSvalue, true);
				} else {
					setPositionDelta(ABSvalue, false);
				}
			}
		}

	}

	/**
	 * Drive the lift to a position relative to where it currently is.
	 *
	 * @param delta distance to travel in inches, positive is up
	 * @param direction True for up, False for down
	 */
	private void setPositionDelta(double delta, boolean direction) {
		if (delta > 1) {
			if (direction) {
				intakeMotor.set(RobotMap.LIFT_MOVING_SPEED);
			}else {
				intakeMotor.set(-RobotMap.LIFT_MOVING_SPEED);
			}
		}
	}

	/**
	 * @return true when the lift is within the destination tolerance of the
	 *         last commanded destination position
	 */
	public boolean isWithinDestiantionTolerance(double position) {
		if (Math.abs(position - getPosition()) < DESTINATION_TOL) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Mark the current position of the lift as zero inches.
	 */
	public void zeroPosition() {
		liftEncoder.reset();
	}

	/**
	 * Identifies when the lift is at its highest position along travel.
	 *
	 * @return true when at upper hard stop
	 */
	public boolean upperHardStop() {
		// TODO: use sensor value
		return false;
	}

	/**
	 * Identifies when the lift is at its lowest position along travel.
	 *
	 * @return true when at the lower hard stop
	 */
	public boolean lowerHardStop() {
		// TODO: use sensor value
		return false;
	}

	/**
	 * Enables the pneumatic brake
	 */
	public void enableBrake() {
		liftBrake.set(Value.kForward);
	}

	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is enabled
	 */
	public boolean isBrakeEnabled() {
		return liftBrake.get() == Value.kForward;
	}

	/**
	 * Disables the pneumatic brake
	 */
	public void disableBrake() {
		liftBrake.set(Value.kReverse);
	}

	/**
	 * Gets the current state of the pneumatic brake
	 *
	 * @return True when brake is disabled
	 */
	public boolean isBrakeDisabled() {
		return liftBrake.get() == Value.kReverse;
	}
}
