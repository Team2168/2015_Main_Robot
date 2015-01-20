package org.team2168;

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


	//Solenoid Channels////////////////////////////////////////////////////////
	public final static int INTAKE_DOUBLE_SOLENOID_FORWARD = 0;
	public final static int INTAKE_DOUBLE_SOLENOID_REVERSE = 1;
	public final static int GRIPPER_DOUBLE_SOLENOID_FORWARD = 2;
	public final static int GRIPPER_DOUBLE_SOLENOID_REVERSE = 3;


	//Digital IO Channels//////////////////////////////////////////////////////
	public final static int LEFT_TOTE_SWITCH = 0;
	public final static int RIGHT_TOTE_SWITCH = 1;


	//Analog Input Channels////////////////////////////////////////////////////
	public final static int SYSTEM_PRESSURE = 0;


	//Joysticks////////////////////////////////////////////////////////////////
	public final static int DRIVER_JOYSTICK = 1;
	public final static int OPERATOR_JOYSTICK = 2;


	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/


	/*************************************************************************
	 *                            MISC PARAMETERS
	 *************************************************************************/
	public final static double PRESS_SENSOR_LOW_VOLTAGE = 0.5;
	public final static double PRESS_SENSOR_LOW_PRESSURE = 0.0;
	public final static double PRESS_SENSOR_HIGH_VOLTAGE = 4.5;
	public final static double PRESS_SENSOR_HIGH_PRESSURE = 150.0;
}
