package org.team2168;

import org.team2168.commands.OpenAll;
import org.team2168.commands.arcb.ARCBDeploy;
import org.team2168.commands.arcb.ARCBDeployLeft;
import org.team2168.commands.arcb.ARCBDeployRight;
import org.team2168.commands.arcb.ARCBRetract;
import org.team2168.commands.arcb.ARCBRetractLeft;
import org.team2168.commands.arcb.ARCBRetractRight;
import org.team2168.commands.binRetainer.DisengageBinRetainer;
import org.team2168.commands.binRetainer.EngageBinRetainer;
import org.team2168.commands.calibration.TestAllMotors;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.OpenWhenSensed;
import org.team2168.commands.intake.OperatorIntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.intake.StopIntakeWheels;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.LiftWithJoystickUNSAFE;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPause;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;
import org.team2168.utils.F310;
import org.team2168.utils.TILaunchPad;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static F310 driverJoystick;
	public static F310 operatorJoystick;
	public static F310 pnuematicTestJoystick;
	public static F310 commandsTestJoystick;
	public static F310 autoTestJoystick;
	
	public static TILaunchPad operatorButtonBox;

	private static OI instance = null;

	/**
	 * A private constructor, to prevent multiple instances of this class from existing.

	 */
	private OI() {
		driverJoystick = new F310(RobotMap.DRIVER_JOYSTICK);
		operatorJoystick = new F310(RobotMap.OPERATOR_JOYSTICK);
		pnuematicTestJoystick = new F310(RobotMap.PNUEMATICS_TEST_JOYSTICK);
		commandsTestJoystick = new F310(RobotMap.COMMANDS_TEST_JOYSTICK);
		autoTestJoystick = new F310(RobotMap.AUTO_TEST_JOYSTICK);
		
		operatorButtonBox = new TILaunchPad(RobotMap.OPERATOR_BUTTONBOX);


		//DRIVER JOYSTICK BUTTON MAP///////////////////////////////////////////////


		//OPERATOR JOYSTICK BUTTON MAP/////////////////////////////////////////////
		operatorJoystick.ButtonRightDPad().whenPressed(new EngageGripper());
		operatorJoystick.ButtonLeftDPad().whenPressed(new ReleaseGripper());

		operatorJoystick.ButtonRightBumper().whenPressed(new EngageIntake());
		operatorJoystick.ButtonLeftBumper().whenPressed(new DisengageIntake());
		operatorJoystick.ButtonStart().whenPressed(new DisengageBinRetainer());
		operatorJoystick.ButtonBack().whenPressed(new EngageBinRetainer());

		operatorJoystick.ButtonRightTrigger().whenPressed(new OperatorIntakeSingleTote());
		operatorJoystick.ButtonRightTrigger().whenReleased(new StopIntakeWheels());
		operatorJoystick.ButtonLeftTrigger().whileHeld(new SetIntakeSpeed(-RobotMap.INTAKE_WHEEL_SPEED));
		operatorJoystick.ButtonLeftTrigger().whenReleased(new StopIntakeWheels());
		
		
		
		//ButtonBox////////////////////////////////////////////////////////////////////////
		operatorButtonBox.Button1().whenPressed(new EngageGripper());
		operatorButtonBox.Button2().whenPressed(new ReleaseGripper());
		
		operatorButtonBox.Button3().whenPressed(new ARCBDeploy());
		operatorButtonBox.Button4().whenPressed(new ARCBRetract());

		operatorButtonBox.Button5().whenPressed(new EngageIntake());
		operatorButtonBox.Button7().whenPressed(new DisengageIntake());
		
		operatorButtonBox.Button6().whenPressed(new OperatorIntakeSingleTote());
		operatorButtonBox.Button6().whenReleased(new StopIntakeWheels());
		operatorButtonBox.Button8().whileHeld(new SetIntakeSpeed(-RobotMap.INTAKE_WHEEL_SPEED));
		operatorButtonBox.Button8().whenReleased(new StopIntakeWheels());
		
		operatorButtonBox.Button9().whenReleased(new LiftOneTote());
		operatorButtonBox.Button10().whenReleased(new OpenAll());

		//TEST CONTROLLER BUTTON MAP///////////////////////////////////////////////
		pnuematicTestJoystick.ButtonA().whenPressed(new EngageGripper());
		pnuematicTestJoystick.ButtonB().whenPressed(new ReleaseGripper());
		pnuematicTestJoystick.ButtonX().whenPressed(new EnableBrake());
		pnuematicTestJoystick.ButtonY().whenPressed(new DisableBrake());
		pnuematicTestJoystick.ButtonLeftBumper().whenPressed(new EngageIntake());
		pnuematicTestJoystick.ButtonRightBumper().whenPressed(new DisengageIntake());
		pnuematicTestJoystick.ButtonBack().whenPressed(new ARCBDeployLeft());
		pnuematicTestJoystick.ButtonBack().whenReleased(new ARCBRetractLeft());
		pnuematicTestJoystick.ButtonStart().whenPressed(new ARCBDeployRight());
		pnuematicTestJoystick.ButtonStart().whenReleased(new ARCBRetractRight());

		commandsTestJoystick.ButtonStart().whenPressed(new LiftPIDPosition(0, 1));
		commandsTestJoystick.ButtonX().whenPressed(new LiftPIDPosition());
		commandsTestJoystick.ButtonB().whenPressed(new LiftPIDPause());
		commandsTestJoystick.ButtonBack().whenPressed(new OpenWhenSensed());
		commandsTestJoystick.ButtonRightBumper().whenPressed(new DriveXDistance(4.5, 0.5, 0.5));
		commandsTestJoystick.ButtonLeftBumper().whenPressed(new TestAllMotors());
		commandsTestJoystick.ButtonRightTrigger().whenPressed(new IntakeSingleTote());
		commandsTestJoystick.ButtonA().whenPressed(new ZeroLift());
		commandsTestJoystick.ButtonY().whenPressed(new RotateXDistancePIDZZZ(40, 0.325, 0.1, 4));
		commandsTestJoystick.ButtonLeftDPad().whenPressed(new LiftWithJoystickUNSAFE(commandsTestJoystick));
		

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
