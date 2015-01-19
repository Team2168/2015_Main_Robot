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


	//Digital IO Channels//////////////////////////////////////////////////////



	//Analog Input Channels////////////////////////////////////////////////////



	//Joysticks////////////////////////////////////////////////////////////////
	public final static int DRIVER_JOYSTICK = 1;
	public final static int OPERATOR_JOYSTICK = 2;


	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/
}
