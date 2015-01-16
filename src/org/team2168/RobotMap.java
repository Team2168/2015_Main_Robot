package org.team2168;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
	//Intake Solenoid Channels 
	public final static int RIGHT_INTAKE_DOUBLE_SOLENOID_FORWARD = 0;
	public final static int RIGHT_INTAKE_DOUBLE_SOLENOID_REVERSE = 1;
	public final static int LEFT_INTAKE_DOUBLE_SOLENOID_FORWARD = 2;
	public final static int LEFT_INTAKE_DOUBLE_SOLENOID_REVERSE = 3;
	
	//Intake Motor Channels
	public final static int RIGHT_INTAKE_MOTOR = 4;
	public final static int LEFT_INTAKE_MOTOR = 5;
	
	
}
