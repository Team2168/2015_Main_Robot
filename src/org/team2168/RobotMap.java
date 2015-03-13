package org.team2168;

import org.team2168.PID.sensors.AverageEncoder;

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

	//PDP Channels/////////////////////////////////////////////////////////////
	public final static int DRIVETRAIN_RIGHT_MOTOR_1_PDP = 2;
	public final static int DRIVETRAIN_RIGHT_MOTOR_2_PDP = 1;
	public final static int DRIVETRAIN_RIGHT_MOTOR_3_PDP = 0;
	public final static int DRIVETRAIN_LEFT_MOTOR_1_PDP = 13;
	public final static int DRIVETRAIN_LEFT_MOTOR_2_PDP = 14;
	public final static int DRIVETRAIN_LEFT_MOTOR_3_PDP = 15;
	public final static int INTAKE_LEFT_MOTOR_PDP = 11;
	public final static int INTAKE_RIGHT_MOTOR_PDP = 4;
	public final static int LIFT_MOTOR_PDP = 12;
	public final static int WINCH_MOTOR_PDP = 10;
	public final static int DIO_POWER = 6;
	public final static int PCM_POWER = 9;


	//Solenoid Channels////////////////////////////////////////////////////////
	public final static int INTAKE_DOUBLE_SOLENOID_FORWARD = 0;
	public final static int INTAKE_DOUBLE_SOLENOID_REVERSE = 1;
	public final static int GRIPPER_DOUBLE_SOLENOID_FORWARD = 2;
	public final static int GRIPPER_DOUBLE_SOLENOID_REVERSE = 3;
	public final static int LIFT_BRAKE_DOUBLE_SOLENOID_FORWARD = 4;
	public final static int LIFT_BRAKE_DOUBLE_SOLENOID_REVERSE = 5;
	public final static int PUSHER_SOLENOID_FORWARD = 6;
	public final static int PUSHER_SOLENOID_REVERSE = 7;

	//Digital IO Channels//////////////////////////////////////////////////////
	//0-9 on RoboRio 10-25 on MXP
	public final static int DRIVETRAIN_LEFT_ENCODER_A = 0;
	public final static int DRIVETRAIN_LEFT_ENCODER_B = 1;
	public final static int DRIVETRAIN_RIGHT_ENCODER_A = 2;
	public final static int DRIVETRAIN_RIGHT_ENCODER_B = 3;
	public final static int LIFT_ENCODER_A = 4;
	public final static int LIFT_ENCODER_B = 5;
	public final static int LIFT_LOWERED_SENSOR = 6;
	public final static int LIFT_RAISED_SENSOR = 7;
	public final static int LEFT_TOTE_SWITCH = 8;
	public final static int RIGHT_TOTE_SWITCH = 9;
	public static final int PRACTICE_BOT_JUMPER = 24; //on MXP
	//public final static int WINCH_ENCODER_A = 6;
	//public final static int WINCH_ENCODER_B = 7;


	//Analog Input Channels////////////////////////////////////////////////////
	public final static int DRIVE_GYRO = 0;
	public final static int SYSTEM_PRESSURE = 1;
	public final static int INTAKE_SENSOR = 2;


	//CAN Device IDs///////////////////////////////////////////////////////////
	public final static int PCM_CAN_ID = 0;
	public final static int PDP_CAN_ID = 1;


	//Joysticks////////////////////////////////////////////////////////////////
	public final static int DRIVER_JOYSTICK = 0;
	public final static int OPERATOR_JOYSTICK = 1;
	public final static int MOTORS_TEST_JOYSTICK = 2;
	public final static int PNUEMATICS_TEST_JOYSTICK = 3;
	public final static int COMMANDS_TEST_JOYSTICK = 4;
	public final static int AUTO_TEST_JOYSTICK = 5;


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
	public static final AverageEncoder.PositionReturnType drivePosReturnType = AverageEncoder.PositionReturnType.FEET;
	public static final AverageEncoder.SpeedReturnType driveSpeedReturnType = AverageEncoder.SpeedReturnType.FPS;
	public static final int driveEncoderMinRate = 0;
	public static final int driveEncoderMinPeriod = 1;
	public static final boolean leftDriveTrainEncoderReverse = false;
	public static final boolean rightDriveTrainEncoderReverse = true;
	public static final int driveAvgEncoderVal = 5;
	public static final double minDriveSpeed =  0.2;
	public static final double autoNormalSpeed = 0.5;
	public static final double wheelbase = 2; //units must match PositionReturnType (feet)

	/*************************************************************************
	 *                              LIFT PARAMETERS
	 *************************************************************************/

	//1.4 inch pully shaft
	//TODO: UPDATE THESE WITH CORRECT VALUES
	private static final int liftPulsePerRotation = 256; //encoder ticks per rotation
	private static final double liftGearRatio = 1/1; //ratio between wheel over encoder
	private static final double liftWheelDiameter = 1.4;
	public static final int liftEncoderPulsePerRot = (int) (liftPulsePerRotation * liftGearRatio); //pulse per rotation * gear ratio
	public static final double liftEncoderDistPerTick = (Math.PI * liftWheelDiameter/liftPulsePerRotation);
	public static final CounterBase.EncodingType liftEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType liftPosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType liftSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int liftEncoderMinRate = 10;
	public static final int liftEncoderMinPeriod = 10;
	public static final boolean liftEncoderReverse = false;
	public static final int liftAvgEncoderVal = 5;


	//TODO Find correct height for the lift at the following positions:
	//Height of lift where it is above the tote ready to lower onto it
	public static final double LIFT_ABOVE_TOTE_HEIGHT = 5;
	//Height of lift where it can engage to grab tote
	public static final double LIFT_TOTE_ENGAGE_HEIGHT = 5;
	//Height of lift where it can carry a tote and drive up to another one for pickup
	public static final double LIFT_CARRYING_TOTE_HEIGHT = 5;

	public static final double LIFT_MOVING_SPEED = 0.5;
	public static final int MAX_LIFT_HEIGHT = 76;
	public static final int MIN_LIFT_HEIGHT = 0;

	//TODO: find height of lift in order to be above a tote with another being carried
	public static final double LIFT_ABOVE_TOTE = 20.0; //inches
	public static final double LIFT_MIN_SPEED = 0.14; //pwm signal


	/*************************************************************************
	 *                            MISC PARAMETERS
	 *************************************************************************/
	public final static double PRESS_SENSOR_LOW_VOLTAGE = 0.5;
	public final static double PRESS_SENSOR_LOW_PRESSURE = 0.0;
	public final static double PRESS_SENSOR_HIGH_VOLTAGE = 4.5;
	public final static double PRESS_SENSOR_HIGH_PRESSURE = 150.0;

	public final static double INTAKE_WHEEL_SPEED = 0.5;
	public final static double INTAKE_TOTE_ENGAGE_VOLTAGE = 1.25;
	public final static double INTAKE_TOTE_STOP_VOLTAGE = 2.5;
	public final static boolean PRINT_SD_DEBUG_DATA = true;
	public final static long SmartDashThreadPeriod = 100; //ms

	/*************************************************************************
	 *                            Electrical Parameters
	 *************************************************************************/
	public final static long PDPThreadPeriod = 50; //ms
	public final static double WARNING_CURRENT_LIMIT = 35;  //amps
	public final static double STALL_CURRENT_LIMIT = 80;  //amps
	public final static double MAIN_BREAKER_TRIP_TEMP = 150;  //farenheit


	/*************************************************************************
	 *                            PID Parameters
	 *************************************************************************/
	//period to run PID loops on drive train
	public static final long driveTrainPIDPeriod = 20;//70ms loop
	public static final int drivetrainPIDArraySize = 50;

	//PID Gains for Left & Right Speed and Position
	//Bandwidth =
	//Phase Margin = 
	public static final double driveTrainLeftSpeedP =  0.4779;
	public static final double driveTrainLeftSpeedI =  1.0526; 
	public static final double driveTrainLeftSpeedD =  0.0543;

	public static final double driveTrainRightSpeedP = 0.4779;
	public static final double driveTrainRightSpeedI = 1.0526;  
	public static final double driveTrainRightSpeedD = 0.0543;

	public static final double driveTrainLeftPositionP = 0.2;
	public static final double driveTrainLeftPositionI = 0.0001412646174233;
	public static final double driveTrainLeftPositionD = 0.0074778888124088;

	public static final double driveTrainRightPositionP = 0.2;
	public static final double driveTrainRightPositionI = 0.0001412646174233;
	public static final double driveTrainRightPositionD = 0.0074778888124088;

	public static final double rotatePositionP = 0.02;
	public static final double rotatePositionI = 0.002;
	public static final double rotatePositionD = 0.0;

	double pTurn = 0.001;
	double iTurn = 0.00001;
	double pDrive = 0;
	
	//public static final double liftPUp = 0.0098;
	//public static final double liftIUp = 0.0124;
	//public static final double liftDUp = 0.0005418310445973070;
	
	public static final double liftPUp = 0.25;
	public static final double liftIUp = 0.0080; 
	public static final double liftDUp = 0.0002573;
	
	public static final double liftPDw = 0.35;
	public static final double liftIDw = 0.010; 
	public static final double liftDDw = 0.0002073;
	
	
//	public static final double liftPUp = 0.0590;
//	public static final double liftIUp = 0.0110;
//	public static final double liftDUp = 0.0017;
//	public static final double liftNUp = 10.7300;
	
	public static final long liftPIDPeriod = 20; //100ms
	
	/****************************************************************
	 *                TCP Servers  (ONLY FOR DEBUGGING)             *
	 ****************************************************************/
	public static final int TCPServerDrivetrainPos = 1180;
	public static final int TCPServerRotateController = 1181;
	public static final int TCPServerRightDrivetrainSpeed = 1182;
	public static final int TCPServerLeftDrivetrainSpeed = 1183;
	public static final int TCPServerLiftPos = 1184;

}
