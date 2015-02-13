package org.team2168;


import org.team2168.commandgroups.DriveLiftToSetPosition;
import org.team2168.commandgroups.IntakeTote;
import org.team2168.commandgroups.LiftTote;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.SetIntakeWheelSpeed;
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
	public static F310 motorsTestJoystick;
	public static F310 pnuematicTestJoystick;
	public static F310 commandsTestJoystick;
	public static F310 autoTestJoystick;


	public OI() {
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
		motorsTestJoystick = new F310(RobotMap.MOTORS_TEST_JOYSTICK);
		pnuematicTestJoystick = new F310(RobotMap.PNUEMATICS_TEST_JOYSTICK);
		commandsTestJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);
		autoTestJoystick = new F310(RobotMap.AUTO_TEST_JOYSTICK);


		//DRIVER JOYSTICK BUTTON MAP///////////////////////////////////////////////


		//OPERATOR JOYSTICK BUTTON MAP/////////////////////////////////////////////
		operatorJoystick.ButtonA().whenPressed(new DriveLiftToSetPosition(RobotMap.MIN_LIFT_HEIGHT));
		operatorJoystick.ButtonY().whenPressed(new DriveLiftToSetPosition(RobotMap.LIFT_ABOVE_TOTE));
		operatorJoystick.ButtonRightDPad().whenPressed(new EngageGripper());
		operatorJoystick.ButtonLeftDPad().whenPressed(new ReleaseGripper());
		operatorJoystick.ButtonRightBumper().whenPressed(new EngageIntake());
		operatorJoystick.ButtonLeftBumper().whenPressed(new DisengageIntake());
		operatorJoystick.ButtonRightTrigger().whileHeld(new SetIntakeWheelSpeed(-1*(OI.operatorJoystick.getRightTriggerAxisRaw())));
		operatorJoystick.ButtonLeftTrigger().whileHeld(new SetIntakeWheelSpeed(OI.operatorJoystick.getLeftTriggerAxisRaw()));


    


		//TEST CONTROLLER BUTTON MAP///////////////////////////////////////////////
        pnuematicTestJoystick.ButtonA().whenPressed(new EngageGripper());
        pnuematicTestJoystick.ButtonB().whenPressed(new ReleaseGripper());
        pnuematicTestJoystick.ButtonX().whenPressed(new EnableBrake());
        pnuematicTestJoystick.ButtonY().whenPressed(new DisableBrake());
        pnuematicTestJoystick.ButtonLeftBumper().whenPressed(new EngageIntake());
        pnuematicTestJoystick.ButtonRightBumper().whenPressed(new DisengageIntake());
        
        commandsTestJoystick.ButtonA().whenPressed(new IntakeTote());
        commandsTestJoystick.ButtonB().whenPressed(new LiftTote());
        commandsTestJoystick.ButtonRightBumper().whenPressed(new DriveXDistance(180,0.3,-1.20));
        
        
	}
}
