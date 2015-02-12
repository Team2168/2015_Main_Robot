package org.team2168;


import org.team2168.commandgroups.DriveLiftToSetPosition;
import org.team2168.commandgroups.IntakeTote;
import org.team2168.commandgroups.LiftTote;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.utils.F310;
import org.team2168.commands.drivetrain.DriveXDistance;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static F310 driverJoystick;
	public static F310 operatorJoystick;
	public static F310 testJoystickLeft;
	public static F310 testJoystickRight;
	public static F310 testJoystick;

	public OI() {
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
		testJoystickLeft = new F310(RobotMap.TEST_JOYSTICK);
		testJoystick = new F310(RobotMap.TEST_JOYSTICK);

		//DRIVER JOYSTICK BUTTON MAP///////////////////////////////////////////////


		//OPERATOR JOYSTICK BUTTON MAP/////////////////////////////////////////////
		operatorJoystick.ButtonA().whenPressed(new DriveLiftToSetPosition(RobotMap.MIN_LIFT_HEIGHT));
		operatorJoystick.ButtonY().whenPressed(new DriveLiftToSetPosition(RobotMap.LIFT_ABOVE_TOTE));
		operatorJoystick.ButtonRightDPad().whenPressed(new EngageGripper());
		operatorJoystick.ButtonLeftDPad().whenPressed(new ReleaseGripper());
		operatorJoystick.ButtonRightBumper().whenPressed(new EngageIntake());
		operatorJoystick.ButtonLeftBumper().whenPressed(new DisengageIntake());
		//TODO: The triggers return analog values. Use this feature to allow the operator to
		// drive the intake at variable speeds send a variable speed to the intake wheels.
		//operatorJoystick.ButtonRightTrigger().whileHeld(new ReverseIntakeWheels());
		//operatorJoystick.ButtonLeftTrigger().whileHeld(new ForwardIntakeWheels());


 
        testJoystickLeft.ButtonRightBumper().whenPressed(new DriveXDistance(180,0.3,-1.20));
    


		//TEST CONTROLLER BUTTON MAP///////////////////////////////////////////////
		testJoystick.ButtonA().whenPressed(new EngageGripper());
		testJoystick.ButtonB().whenPressed(new ReleaseGripper());
		testJoystick.ButtonX().whenPressed(new EnableBrake());
		testJoystick.ButtonY().whenPressed(new DisableBrake());
		testJoystick.ButtonLeftBumper().whenPressed(new EngageIntake());
		testJoystick.ButtonRightBumper().whenPressed(new DisengageIntake());
		testJoystick.ButtonLeftStick().whenPressed(new IntakeTote());
		testJoystick.ButtonRightStick().whenPressed(new LiftTote());
	}
}
