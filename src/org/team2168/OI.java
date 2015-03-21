package org.team2168;

import org.team2168.commands.arcb.ARCBDeploy;
import org.team2168.commands.arcb.ARCBDeployLeft;
import org.team2168.commands.arcb.ARCBDeployRight;
import org.team2168.commands.arcb.ARCBRetract;
import org.team2168.commands.arcb.ARCBRetractLeft;
import org.team2168.commands.arcb.ARCBRetractRight;
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


		//OPERATOR JOYSTICK BUTTON MAP/////////////////////////////////////////////
		operatorJoystick.ButtonRightDPad().whenPressed(new EngageGripper());
		operatorJoystick.ButtonLeftDPad().whenPressed(new ReleaseGripper());

		operatorJoystick.ButtonRightBumper().whenPressed(new EngageIntake());
		operatorJoystick.ButtonLeftBumper().whenPressed(new DisengageIntake());
		operatorJoystick.ButtonStart().whenPressed(new ARCBDeploy());
		operatorJoystick.ButtonBack().whenPressed(new ARCBRetract());

		operatorJoystick.ButtonRightTrigger().whenPressed(new OperatorIntakeSingleTote());
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
		pnuematicTestJoystick.ButtonBack().whenPressed(new ARCBDeployLeft());
		pnuematicTestJoystick.ButtonBack().whenReleased(new ARCBRetractLeft());
		pnuematicTestJoystick.ButtonStart().whenPressed(new ARCBDeployRight());
		pnuematicTestJoystick.ButtonStart().whenReleased(new ARCBRetractRight());

		commandsTestJoystick.ButtonStart().whenPressed(new LiftOneTote());
		commandsTestJoystick.ButtonX().whenPressed(new LiftPIDPosition());
		commandsTestJoystick.ButtonB().whenPressed(new LiftPIDPause());
		commandsTestJoystick.ButtonBack().whenPressed(new OpenWhenSensed());
		commandsTestJoystick.ButtonRightBumper().whenPressed(new DriveXDistance(18,0.2,1));
		commandsTestJoystick.ButtonLeftBumper().whenPressed(new TestAllMotors());
		commandsTestJoystick.ButtonRightTrigger().whenPressed(new IntakeSingleTote());
		commandsTestJoystick.ButtonA().whenPressed(new ZeroLift());
		commandsTestJoystick.ButtonY().whenPressed(new RotateXDistancePIDZZZ(45, 0.4));
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
