package org.team2168;

import org.team2168.PIDController.sensors.AverageEncoder;

import edu.wpi.first.wpilibj.CounterBase;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/*************************************************************************
	 *                              WIRING MAP
	 *************************************************************************/
    
	//PWM Channels/////////////////////////////////////////////////////////////
	public final static int INTAKE_MOTORS = 0;
	public final static int DRIVETRAIN_LEFT_MOTORS = 1;
	public final static int DRIVETRAIN_RIGHT_MOTORS = 2;
	public final static int LIFT_MOTOR = 3;
	public final static int WINCH_MOTOR = 4;


	//Solenoid Channels////////////////////////////////////////////////////////
	public final static int INTAKE_DOUBLE_SOLENOID_FORWARD = 0;
	public final static int INTAKE_DOUBLE_SOLENOID_REVERSE = 1;
	public final static int GRIPPER_DOUBLE_SOLENOID_FORWARD = 2;
	public final static int GRIPPER_DOUBLE_SOLENOID_REVERSE = 3;
	public final static int LIFT_DOUBLE_SOLENOID_FORWARD = 4;
	public final static int LIFT_DOUBLE_SOLENOID_REVERSE = 5;


	//Digital IO Channels//////////////////////////////////////////////////////
	public final static int LEFT_TOTE_SWITCH = 0;
	public final static int RIGHT_TOTE_SWITCH = 1;
	public final static int LIFT_ENCODER_A = 2;
	public final static int LIFT_ENCODER_B = 3;
	public final static int WINCH_ENCODER_A = 4;
	public final static int WINCH_ENCODER_B = 5;
	public final static int DRIVETRAIN_LEFT_ENCODER_A = 6;
	public final static int DRIVETRAIN_LEFT_ENCODER_B = 7;
	public final static int DRIVETRAIN_RIGHT_ENCODER_A = 8;
	public final static int DRIVETRAIN_RIGHT_ENCODER_B = 9;


	//Analog Input Channels////////////////////////////////////////////////////
	public final static int SYSTEM_PRESSURE = 0;
	public final static int DRIVE_GYRO = 1;
	public final static int INTAKE_SENSOR = 2;


	//CAN Device IDs///////////////////////////////////////////////////////////
	public final static int PDP_CAN_ID = 1;
	public final static int PCM_CAN_ID = 2;


	//Joysticks////////////////////////////////////////////////////////////////
	public final static int DRIVER_JOYSTICK = 1;
	public final static int OPERATOR_JOYSTICK = 2;
	public final static int TEST_JOYSTICK = 2;
	


	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/

	//TODO Verify gear ratio
	private static final int drivePulsePerRotation = 256; //encoder ticks per rotation
	private static final double driveGearRatio = 24.0/27.0; //ratio between wheel over encoder
	private static final double driveWheelDiameter = 6;
	public static final int driveEncoderPulsePerRot = (int) (drivePulsePerRotation*driveGearRatio); //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick = (Math.PI * driveWheelDiameter/driveEncoderPulsePerRot);
	public static final CounterBase.EncodingType driveEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType drivePosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType driveSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int driveEncoderMinRate = 10;
	public static final int driveEncoderMinPeriod = 10;
	public static final boolean leftDriveTrainEncoderReverse = false;
	public static final boolean rightDriveTrainEncoderReverse = true;
	public static final int driveAvgEncoderVal = 5;
	
	/*************************************************************************
	 *                            MISC PARAMETERS
	 *************************************************************************/
	public final static double PRESS_SENSOR_LOW_VOLTAGE = 0.5;
	public final static double PRESS_SENSOR_LOW_PRESSURE = 0.0;
	public final static double PRESS_SENSOR_HIGH_VOLTAGE = 4.5;
	public final static double PRESS_SENSOR_HIGH_PRESSURE = 150.0;
}
