package org.team2168.gamepads;

import org.team2168.RobotMap;
import org.team2168.utils.JoystickAnalogButton;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * 
 * Class to encapsulate all F310 functionality. No need to have multiple copies
 * in OI all the time
 * 
 * @author kevin
 *
 */
public class F310 extends Joystick
{
	// Gamepad axis ports
	private static final int AXIS_LEFT_X = 0;
	private static final int AXIS_LEFT_Y = 1;
	private static final int AXIS_Left_SHOULDER_TRIGGER = 2;
	private static final int AXIS_Right_SHOULDER_TRIGGER = 3;
	private static final int AXIS_RIGHT_X = 4;
	private static final int AXIS_RIGHT_Y = 5;
	private static final int AXIS_DPAD_X = 6;

	// Gamepad buttons
	private static final int BUTTON_A = 1;
	private static final int BUTTON_B = 2;
	private static final int BUTTON_X = 3;
	private static final int BUTTON_Y = 4;
	private static final int BUTTON_SHOULDER_LEFT_BUMPER = 5;
	private static final int BUTTON_SHOULDER_RIGHT_BUMPER = 6;
	private static final int BUTTON_BACK = 7;
	private static final int BUTTON_START = 8;
	private static final int BUTTON_LEFT_STICK = 9;
	private static final int BUTTON_RIGHT_STICK = 10;

	private static final int BUTTON_MODE = -1;
	private static final int BUTTON_LOGITECH = -1;

	/**
	 * Default constructor
	 * @param port the port the joystick is plugged into on the DS.
	 */
	public F310(int port) {
		super(port);	
	}

	/**
	 * Returns the X position of the left stick.
	 */
	public double getLeftStickRaw_X() {
		return getRawAxis(AXIS_LEFT_X);
	}

	/**
	 * Returns the X position of the right stick.
	 */
	public double getRightStickRaw_X() {
		return getRawAxis(AXIS_RIGHT_X);
	}

	/**
	 * Returns the Y position of the left stick.
	 */
	public double getLeftStickRaw_Y() {
		return getRawAxis(AXIS_LEFT_Y);
	}

	/**
	 * Returns the Y position of the right stick.
	 */
	public double getRightStickRaw_Y() {
		return getRawAxis(AXIS_RIGHT_Y);
	}

	/**
	 * Returns the position of the shoulder trigger.
	 */
	public double getLeftTriggerAxisRaw() {
		return getRawAxis(AXIS_Left_SHOULDER_TRIGGER);
	}
	
	/**
	 * Returns the position of the shoulder trigger.
	 */
	public double getRightTriggerAxisRaw() {
		return getRawAxis(AXIS_Right_SHOULDER_TRIGGER);
	}

	/**
	 * Returns the position of the shoulder trigger.
	 */
	public double getDAxisRaw_X() {
		return getRawAxis(AXIS_DPAD_X);
	}

	/**
	 * Checks whether Button A is being pressed and returns true if it is.
	 */
	public boolean isPressedButtonA() {
		return getRawButton(BUTTON_A);
	}

	/**
	 * Checks whether Button B is being pressed and returns true if it is.
	 */
	public boolean isPressedButtonB() {
		return getRawButton(BUTTON_B);
	}

	/**
	 * Checks whether Button X is being pressed and returns true if it is.
	 */
	public boolean isPressedButtonX() {
		return getRawButton(BUTTON_X);
	}

	/**
	 * Checks whether Button Y is being pressed and returns true if it is.
	 */
	public boolean isPressedButtonY() {
		return getRawButton(BUTTON_Y);
	}

	public boolean isPressedButtonLeftBumper() {
		return getRawButton(BUTTON_SHOULDER_LEFT_BUMPER);
	}	  

	public boolean isPressedButtonRightBumper() {
		return getRawButton(BUTTON_SHOULDER_RIGHT_BUMPER);
	}	  	  

	public boolean isPressedButtonBack() {
		return getRawButton(BUTTON_BACK);
	}	 	  

	public boolean isPressedButtonStart() {
		return getRawButton(BUTTON_START);
	}		  

	public boolean isPressedButtonLeftStick() {
		return getRawButton(BUTTON_LEFT_STICK);
	}	

	public boolean isPressedButtonRightStick() {
		return getRawButton(BUTTON_RIGHT_STICK);
	}		  

	/**
	 * DPad Left and Right only
	 * WPILIB cannot access the vertical axis of the Logitech Game Controller Dpad
	 */

	public boolean isPressedButtonDPadLeft() {
		double x = getDAxisRaw_X();
		return (x < -0.5);
	}

	public boolean isPressedButtonDPadRight() {
		double x = getDAxisRaw_X();
		return (x > 0.5);
	}

	public boolean isPressedButtonLeftTrigger() {
		double x = getLeftTriggerAxisRaw();
		return (x < -0.5);
	}

	public boolean isPressedButtonRightTrigger() {
		double x = getRightTriggerAxisRaw();
		return (x < -0.5);
	}

	/**
	 * Returns an object of Button A.
	 */
	public JoystickButton ButtonA() {
		return new JoystickButton(this, BUTTON_A);
	}

	/**
	 * Returns an object of Button B.
	 */
	public JoystickButton ButtonB() {
		return new JoystickButton(this, BUTTON_B);
	}

	/**
	 * Returns an object of Button X.
	 */
	public JoystickButton ButtonX() {
		return new JoystickButton(this, BUTTON_X);
	}

	/**
	 * Returns an object of Button Y.
	 */
	public JoystickButton ButtonY() {
		return new JoystickButton(this, BUTTON_Y);
	}

	/**
	 * Gets Start button object
	 * @return the Start button
	 */
	public JoystickButton ButtonStart(){
		return new JoystickButton(this, BUTTON_START);
	}

	/**
	 * Gets the Back button object
	 * @return the Back button
	 */
	public JoystickButton ButtonBack() {
		return new JoystickButton(this, BUTTON_BACK);
	}

	/**
	 * Gets the state of the left shoulder
	 * @return the state of the left shoulder
	 */
	public JoystickButton ButtonLeftBumper() {
		return new JoystickButton(this, BUTTON_SHOULDER_LEFT_BUMPER);
	}

	/**
	 * Gets the state of the right shoulder
	 * @return the state of the right shoulder
	 */
	public JoystickButton ButtonRightBumper() {
		return new JoystickButton(this, BUTTON_SHOULDER_RIGHT_BUMPER);
	}

	public JoystickButton ButtonLeftStick() {
		return new JoystickButton(this, BUTTON_LEFT_STICK);
	}

	public JoystickButton ButtonRightStick() {
		return new JoystickButton(this, BUTTON_RIGHT_STICK);
	}

	public JoystickAnalogButton ButtonLeftTrigger() {
		return new JoystickAnalogButton(this, AXIS_Left_SHOULDER_TRIGGER, -0.5);
	}
	
	public JoystickAnalogButton ButtonRightTrigger() {
		return new JoystickAnalogButton(this, AXIS_Right_SHOULDER_TRIGGER, -0.5);
	}
	
	public JoystickAnalogButton ButtonLeftDPad() {
		return new JoystickAnalogButton(this, AXIS_DPAD_X, -0.5);
	}
	
	public JoystickAnalogButton ButtonRightDPad() {
		return new JoystickAnalogButton(this, AXIS_DPAD_X, 0.5);
	}

	/**
	 * Electronic braking - aka "Falcon Claw"
	 * The more the "brake" is pulled, the slower output speed
	 * 
	 * @param inputSpeed The input value to scale back based on brake input. (1 to -1)
	 * @param brake The brake input value. (0 to -1)
	 * @return The adjusted value.
	 */
	private double falconClaw(double inputSpeed, double brake) {
		return ((1 - ((-RobotMap.minDriveSpeed + 1) * Math.abs(brake))) * inputSpeed);
	}

	/**
	 * A function to modify the joystick values using linear interpolation.
	 * The objective is augment the joystick value going to the motor controllers
	 *   to widen the region of "fine" control while still allowing full speed.
	 * 
	 * @param input The value to augment.
	 * @return The adjusted value.
	 */
	private double interpolate(double input) {
		//TODO: Modify this to take in the set of points as a parameter, then move out to utils package.
		double retVal = 0.0;
		boolean done = false;
		double m, b;

		//make sure input is between 1.0 and -1.0
		if (input > 1.0) {
			input = 1.0;
		} else if (input < -1.0) {
			input = -1.0;
		}

		//Find the two points in our array, between which the input falls. 
		//We will start at i = 1 since we can't have a point fall outside our array.
		for (int i = 1; !done && i < joystickScale.length; i++) {
			if (input >= joystickScale[i][0]) {
				//We found where the point falls in out array, between index i and i-1
				//Calculate the equation for the line. y=mx+b
				m = Util.slope(joystickScale[i-1][0],
                               joystickScale[i-1][1],
                               joystickScale[i][0],
                               joystickScale[i][1]);
				b = Util.intercept(m, joystickScale[i][0], joystickScale[i][1]);
				retVal = m * input + b;

				//we're finished, don't continue to loop
				done = true;
			}
		}

		return retVal;
	}

	// minSpeed needs to be tweaked based on the particular drivetrain.
	// It is the speed at which the drivetrain barely starts moving
	static double joystickScale[][] = {
		/* Joystick Input, Scaled Output */
		{ 1.00, 1.00 },
		{ 0.90, 0.68 },
		{ 0.06, RobotMap.minDriveSpeed },
		{ 0.06, 0.00 },
		{ 0.00, 0.00 },
		{ -0.06, 0.00 },
		{ -0.06, -RobotMap.minDriveSpeed},
		{ -0.90, -0.68 },
		{ -1.00, -1.00 } };	
}

