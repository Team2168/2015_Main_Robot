package org.team2168;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {	
	// Solenoid Channels 
	public final static int INTAKE_DOUBLE_SOLENOID_FORWARD = 0;
	public final static int INTAKE_DOUBLE_SOLENOID_REVERSE = 1;
	
	// PWM Channels
	public final static int INTAKE_MOTORS = 0;
	
	// Digital Input
	public final static int LEFT_TOTE_SWITCH = 0;
	public final static int RIGHT_TOTE_SWITCH = 1;
	
}
