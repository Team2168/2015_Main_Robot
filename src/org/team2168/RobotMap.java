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
	public final static int LIFT_LEFT_MOTOR_PDP = 12;
	public final static int LIFT_RIGHT_MOTOR_PDP = 3;
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
	public final static int RCCBB_DOUBLE_SOLENOID_FORWARD = 6;
	public final static int RCCB_BRAKE_DOUBLE_SOLENOID_REVERSE = 7;

	//Relay Channels///////////////////////////////////////////////////////////
	public final static int BIN_RETAINER_RELAY = 0;
	public final static int ARCB_RELAY = 1;
	

	//Digital IO Channels//////////////////////////////////////////////////////
	//Channels 0-9 on RoboRio
	public final static int DRIVETRAIN_LEFT_ENCODER_A = 0;
	public final static int DRIVETRAIN_LEFT_ENCODER_B = 1;
	public final static int DRIVETRAIN_RIGHT_ENCODER_A = 2;
	public final static int DRIVETRAIN_RIGHT_ENCODER_B = 3;
	public final static int LIFT_ENCODER_A = 4;
	public final static int LIFT_ENCODER_B = 5;
	public final static int LIFT_LOWERED_SENSOR = 6;
	public final static int LIFT_RAISED_SENSOR = 7;

	public final static int BIN_RETAINER_RAISED_SENSOR = 9;
	//Channels 10-25 on MXP
	public final static int LEDS_GRIPPER_ENGAGED = 10;      //MXP pin 11
	public final static int LEDS_INTAKE_ENGAGED = 11;       //MXP pin 13
	public final static int LEDS_INTAKE_WHEELS_ACTIVE = 12; //MXP pin 15
	public final static int LEDS_ROBOT_DISABLED = 13;       //MXP pin 17
	public static final int PRACTICE_BOT_JUMPER = 24;       //MXP pin 32


	//Analog Input Channels////////////////////////////////////////////////////
	public final static int LIFT_LOWER_STALL_SENSOR = 0;
	public final static int RC_DISTANCE_SENSOR = 1;
	public final static int INTAKE_SENSOR = 2;
	public final static int RC_BIN_AUTO_SENSOR = 3; 
	
	//Channels 4-7 on MXP
	public final static int DRIVE_GYRO = 4;
	public final static int SYSTEM_PRESSURE = 5;


	//CAN Device IDs///////////////////////////////////////////////////////////
	public final static int PCM_CAN_ID = 0;
	public final static int PDP_CAN_ID = 1;


	//Joysticks////////////////////////////////////////////////////////////////
	public final static int DRIVER_JOYSTICK = 0;
	public final static int OPERATOR_JOYSTICK = 1;
	public final static int OPERATOR_BUTTONBOX = 2;
	public final static int PNUEMATICS_TEST_JOYSTICK = 3;
	public final static int COMMANDS_TEST_JOYSTICK = 4;
	public final static int AUTO_TEST_JOYSTICK = 5;



	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/
	//TODO: Verify gear ratio
	private static final int drivePulsePerRotation = 256; //encoder ticks per rotation
	private static final double driveGearRatio = 24.0/15.0; //ratio between wheel over encoder
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
	public static final AverageEncoder.SpeedReturnType liftSpeedReturnType = AverageEncoder.SpeedReturnType.IPS;
	public static final int liftEncoderMinRate = 10;
	public static final int liftEncoderMinPeriod = 10;
	public static final boolean liftEncoderReverse = false;
	public static final int liftAvgEncoderVal = 1;

	public static final double LIFT_OVER_CURRENT_RAISE = 60.0; //amps
	public static final double LIFT_OVER_CURRENT_LOWER = 42.0; //amps
	public static final double LIFT_STALL_PERIOD = 0.75; //seconds stall can be sustained for
	public static final double LIFT_STALL_RATE = -15; //if lift moves slower than this rate assume it is stalling (inch/sec)

	//TODO: Calibrate this voltage. Values below this value will be interpreted
	//  as an object being in the way of the lift.
	public static final double LIFT_LOWER_STALL_VOLTAGE = 2.5;

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
	public static final double LIFT_PWM_DEADBAND = 0.2;

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

	public final static double INTAKE_WHEEL_SPEED = 1.0;
	public final static double INTAKE_TOTE_ENGAGE_VOLTAGE = 0.85;
	public final static double INTAKE_TOTE_STOP_VOLTAGE = 2.45;
	public final static double INTAKE_RC_PRESENT_VOLTAGE = 1.0;

	public final static boolean PRINT_SD_DEBUG_DATA = true;
	public final static long SmartDashThreadPeriod = 100; //ms

	/*************************************************************************
	 *                            Electrical Parameters
	 *************************************************************************/
	public final static long PDPThreadPeriod = 20; //ms
	public final static double WARNING_CURRENT_LIMIT = 35;  //amps
	public final static double STALL_CURRENT_LIMIT = 50;  //amps
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

	public static final double rotatePositionP = 0.045;
	public static final double rotatePositionI = 0.001;
	public static final double rotatePositionD = 0.0064778888124088;

	double pTurn = 0.001;
	double iTurn = 0.00001;
	double pDrive = 0;

	public static final double liftPUp = 0.12;
	public static final double liftIUp = 0.0120;
	public static final double liftDUp = 0.0002573;

	public static final double liftPDw = 0.12;
	public static final double liftIDw = 0.05;
	public static final double liftDDw = 0.02573;


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
