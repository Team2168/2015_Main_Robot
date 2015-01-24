package org.team2168;

import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.IntakeIn;
import org.team2168.commands.intake.IntakeOff;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    // // CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    // joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    // // TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    public static F310 driverJoystick;
    public static F310 operatorJoystick;
    public static F310 testJoystick;
    
    public OI() {
    	//Joysticks////////////////////////////////////////////////////////////////
        driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
        operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
        testJoystick = new F310(RobotMap.TEST_JOYSTICK);
        //TEST CONTROLLER BUTTON MAP///////////////////////////////////////////////
        testJoystick.ButtonA().whenPressed(new EngageGripper());
        testJoystick.ButtonA().whenReleased(new ReleaseGripper());
        testJoystick.ButtonB().whenPressed(new IntakeIn());
        testJoystick.ButtonB().whenReleased(new IntakeOff());
        
    }
    

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
