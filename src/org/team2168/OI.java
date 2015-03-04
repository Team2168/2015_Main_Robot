package org.team2168;

import org.team2168.commandgroups.DriveLiftToSetPosition;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.intake.StopIntakeWheels;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.pusher.PushTotes;
import org.team2168.commands.pusher.RetractPusher;
import org.team2168.utils.F310;

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

	private static OI instance = null;

	/**
	 * A private constructor, to prevent multiple instances of this class from existing.
	 */
	private OI() {
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
		motorsTestJoystick = new F310(RobotMap.MOTORS_TEST_JOYSTICK);
		pnuematicTestJoystick = new F310(RobotMap.PNUEMATICS_TEST_JOYSTICK);
		commandsTestJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);
		autoTestJoystick = new F310(RobotMap.AUTO_TEST_JOYSTICK);


		//DRIVER JOYSTICK BUTTON MAP///////////////////////////////////////////////
		//driverJoystick.ButtonA().whenPressed(new PushTotes());
		//driverJoystick.ButtonB().whenPressed(new RetractPusher());

		//OPERATOR JOYSTICK BUTTON MAP/////////////////////////////////////////////
		operatorJoystick.ButtonA().whenPressed(new DriveLiftToSetPosition(RobotMap.MIN_LIFT_HEIGHT));
		operatorJoystick.ButtonY().whenPressed(new DriveLiftToSetPosition(RobotMap.LIFT_ABOVE_TOTE));
		operatorJoystick.ButtonRightDPad().whenPressed(new EngageGripper());
		operatorJoystick.ButtonLeftDPad().whenPressed(new ReleaseGripper());
		operatorJoystick.ButtonRightBumper().whenPressed(new EngageIntake());
		operatorJoystick.ButtonLeftBumper().whenPressed(new DisengageIntake());
		operatorJoystick.ButtonStart().whenPressed(new PushTotes());
		operatorJoystick.ButtonBack().whenPressed(new RetractPusher());
		operatorJoystick.ButtonRightTrigger().whenPressed(new IntakeSingleTote());
		operatorJoystick.ButtonRightTrigger().whenReleased(new StopIntakeWheels());
		operatorJoystick.ButtonLeftTrigger().whileHeld(new SetIntakeSpeed(-RobotMap.INTAKE_WHEEL_SPEED));
		operatorJoystick.ButtonLeftTrigger().whenReleased(new StopIntakeWheels());


		//TEST CONTROLLER BUTTON MAP///////////////////////////////////////////////
		pnuematicTestJoystick.ButtonA().whenPressed(new EngageGripper());
		pnuematicTestJoystick.ButtonB().whenPressed(new ReleaseGripper());
		pnuematicTestJoystick.ButtonX().whenPressed(new EnableBrake());
		pnuematicTestJoystick.ButtonY().whenPressed(new DisableBrake());
		pnuematicTestJoystick.ButtonLeftBumper().whenPressed(new EngageIntake());
		pnuematicTestJoystick.ButtonRightBumper().whenPressed(new DisengageIntake());
		//
		//        commandsTestJoystick.ButtonA().whenPressed(new LiftPIDPosition());
		//        commandsTestJoystick.ButtonB().whenPressed(new LiftPIDPause());
		//        commandsTestJoystick.ButtonRightBumper().whenPressed(new DriveXDistance(180,0.3,-1.20));
		//
		commandsTestJoystick.ButtonA().whenPressed(new ZeroLift());


	}

	/**
	 * @return an instance of the OI class.
	 */
	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}

		return instance;
	}
}
