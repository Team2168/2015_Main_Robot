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
	
	public final static int debug = 1;

	//PWM Channels/////////////////////////////////////////////////////////////
	public final static int DRIVETRAIN_RIGHT_MOTOR_1 = 0;
	public final static int DRIVETRAIN_RIGHT_MOTOR_2 = 1;
	public final static int DRIVETRAIN_RIGHT_MOTOR_3 = 2;
	public final static int DRIVETRAIN_LEFT_MOTOR_1 = 3;
	public final static int DRIVETRAIN_LEFT_MOTOR_2 = 4;
	public final static int DRIVETRAIN_LEFT_MOTOR_3 = 5;


	public final static int INTAKE_LEFT_MOTOR = 6;
	public final static int INTAKE_RIGHT_MOTOR = 7;
	public final static int LIFT_MOTOR = 8;
	public final static int WINCH_MOTOR = 9;


	//Solenoid Channels////////////////////////////////////////////////////////
	public final static int INTAKE_DOUBLE_SOLENOID_FORWARD = 0;
	public final static int INTAKE_DOUBLE_SOLENOID_REVERSE = 1;
	public final static int GRIPPER_DOUBLE_SOLENOID_FORWARD = 2;
	public final static int GRIPPER_DOUBLE_SOLENOID_REVERSE = 3;
	public final static int LIFT_BRAKE_DOUBLE_SOLENOID_FORWARD = 4;
	public final static int LIFT_BRAKE_DOUBLE_SOLENOID_REVERSE = 5;


	//Digital IO Channels//////////////////////////////////////////////////////
	//0-9 on RoboRio 10-25 on MXP
	public final static int DRIVETRAIN_LEFT_ENCODER_A = 0;
	public final static int DRIVETRAIN_LEFT_ENCODER_B = 1;
	public final static int DRIVETRAIN_RIGHT_ENCODER_A = 2;
	public final static int DRIVETRAIN_RIGHT_ENCODER_B = 3;
	public final static int LIFT_ENCODER_A = 4;
	public final static int LIFT_ENCODER_B = 5;
	public final static int WINCH_ENCODER_A = 6;
	public final static int WINCH_ENCODER_B = 7;
	public final static int LEFT_TOTE_SWITCH = 8;
	public final static int RIGHT_TOTE_SWITCH = 9;
	
	public static final int PracticeBotJumper = 24; //on MXP

	//Analog Input Channels////////////////////////////////////////////////////
	public final static int DRIVE_GYRO = 0;
	public final static int SYSTEM_PRESSURE = 1;
	public final static int INTAKE_SENSOR = 2;


	//CAN Device IDs///////////////////////////////////////////////////////////
	public final static int PDP_CAN_ID = 1;
	public final static int PCM_CAN_ID = 2;


	//Joysticks////////////////////////////////////////////////////////////////
	public final static int DRIVER_JOYSTICK = 0;
	public final static int OPERATOR_JOYSTICK = 1;
	public final static int TEST_JOYSTICK = 2;


	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/
	//TODO: Verify gear ratio
	private static final int drivePulsePerRotation = 256; //encoder ticks per rotation
	private static final double driveGearRatio = 22.0/15.0; //ratio between wheel over encoder
	private static final double driveWheelDiameter = 6;
	public static final int driveEncoderPulsePerRot = (int) (drivePulsePerRotation * driveGearRatio); //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick = (Math.PI * driveWheelDiameter/driveEncoderPulsePerRot);
	public static final CounterBase.EncodingType driveEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType drivePosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType driveSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int driveEncoderMinRate = 10;
	public static final int driveEncoderMinPeriod = 10;
	public static final boolean leftDriveTrainEncoderReverse = false;
	public static final boolean rightDriveTrainEncoderReverse = true;
	public static final int driveAvgEncoderVal = 5;
	public static final double minDriveSpeed =  0.2;


	/*************************************************************************
	 *                              LIFT PARAMETERS
	 *************************************************************************/
	//TODO: UPDTE THESE WITH CORRECT VALUES
	private static final int liftPulsePerRotation = 256; //encoder ticks per rotation
	private static final double liftGearRatio = 24.0/27.0; //ratio between wheel over encoder
	private static final double liftWheelDiameter = 6;
	public static final int liftEncoderPulsePerRot = (int) (drivePulsePerRotation * driveGearRatio); //pulse per rotation * gear ratio
	public static final double liftEncoderDistPerTick = (Math.PI * driveWheelDiameter/driveEncoderPulsePerRot);
	public static final CounterBase.EncodingType liftEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType liftPosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType liftSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int liftEncoderMinRate = 10;
	public static final int liftEncoderMinPeriod = 10;
	public static final boolean liftEncoderReverse = false;
	public static final int liftAvgEncoderVal = 5;

	public static final double LIFT_MOVING_SPEED = 0.5;
	
	//TODO find height of lift in order to be above a tote with another being carried
	public static final double LIFT_ABOVE_TOTE = 1;


	/*************************************************************************
	 *                            MISC PARAMETERS
	 *************************************************************************/
	public final static double PRESS_SENSOR_LOW_VOLTAGE = 0.5;
	public final static double PRESS_SENSOR_LOW_PRESSURE = 0.0;
	public final static double PRESS_SENSOR_HIGH_VOLTAGE = 4.5;
	public final static double PRESS_SENSOR_HIGH_PRESSURE = 150.0;

	public final static int MAX_LIFT_HEIGHT = 76;
	public final static int MIN_LIFT_HEIGHT = 0;

	

}
