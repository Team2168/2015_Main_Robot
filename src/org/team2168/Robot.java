package org.team2168;

import org.team2168.PID.pathplanner.FalconPathPlanner;
import org.team2168.PID.trajectory.LoadPathFile;
import org.team2168.PID.trajectory.Path;
import org.team2168.commands.auto.Auto_NoTote_DoNothing;
import org.team2168.commands.auto.Auto_NoTote_DriveForward;
import org.team2168.commands.auto.Auto_OneTote_Rotate90Push;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Gripper;
import org.team2168.subsystems.Intake;
import org.team2168.subsystems.Lift;
import org.team2168.subsystems.Pneumatics;
import org.team2168.subsystems.Pusher;
import org.team2168.subsystems.Winch;
import org.team2168.utils.ConsolePrinter;
import org.team2168.utils.Debouncer;
import org.team2168.utils.PowerDistribution;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	// Subsystem objects
	public static Drivetrain drivetrain;
	public static Intake intake;
	public static Lift lift;
	public static Winch winch;
	public static Gripper gripper;
	public static Pneumatics pneumatics;
	public static Pusher pusher;

	public static PowerDistribution pdp; // Power Monitor
	ConsolePrinter printer; // SmartDash printer

	public static BuiltInAccelerometer accel;

	// Auto command objects
	static Command autonomousCommand;
	Command driveWithJoystick;
	SendableChooser autoChooser;

	private static DigitalInput practiceBot;
	public static FalconPathPlanner path;

	public static Path drivePath;

	private static boolean matchStarted = false;
	public static int gyroReinits;
	private double lastAngle;
	private Debouncer gyroDriftDetector = new Debouncer(1.0);
	public static boolean gyroCalibrating = false;
	private boolean lastGyroCalibrating = false;
	private double curAngle = 0.0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// Instantiate sensors
		practiceBot = new DigitalInput(RobotMap.PRACTICE_BOT_JUMPER);
		accel = new BuiltInAccelerometer();
		pdp = new PowerDistribution(RobotMap.PDPThreadPeriod);
		pdp.startThread();

		// Instantiate subsystems
		pneumatics = Pneumatics.getInstance();
		drivetrain = Drivetrain.getInstance();
		intake = Intake.getInstance();
		lift = Lift.getInstance();
		winch = Winch.getInstance();
		gripper = Gripper.getInstance();
		pusher = Pusher.getInstance();

		// instantiate the command used for the autonomous period
		// autonomousCommand = new ExampleCommand();

		pathPlanner();

		drivePath = LoadPathFile.readFile("/home/lvuser/2168StraightPath.txt");

		// create thread to write dashboard variables
		printer = new ConsolePrinter(RobotMap.SmartDashThreadPeriod);
		printer.startThread();

		oi = OI.getInstance();

		System.out.println(drivePath.getLeftWheelTrajectory().getNumSegments());
		System.out
		.println(drivePath.getRightWheelTrajectory().getNumSegments());

		System.out.println("Left Wheel Trajectory");
		for (int s = 0; s <= drivePath.getLeftWheelTrajectory()
				.getNumSegments(); s++) {
			System.out
			.println(drivePath.getLeftWheelTrajectory().getSegment(s));
		}

		System.out.println("Right Wheel Trajectory");
		for (int s = 0; s <= drivePath.getRightWheelTrajectory()
				.getNumSegments(); s++) {
			System.out.println(drivePath.getRightWheelTrajectory()
					.getSegment(s));
		}

		autoSelectInit();

		drivetrain.calibrateGyro();

		System.out.println("Bot Finished Loading.");
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
	}

	/**
	 * This method runs periodically when the robot is disabled
	 */
	public void disabledPeriodic() {
		autonomousCommand = (Command) autoChooser.getSelected();
		// Kill all active commands
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().disable();

		// Check to see if the gyro is drifting, if it is re-initialize it.
		gyroReinit();
	}

	/**
	 * This method initializes the autonomous commands
	 */
	public void autonomousInit() {
		matchStarted = true;
		drivetrain.stopGyroCalibrating();

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}

		Scheduler.getInstance().enable();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		autonomousCommand = (Command) autoChooser.getSelected();
		Scheduler.getInstance().run();
	}

	/**
	 * This method initializes the teleop commands
	 */
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		matchStarted = true;
		drivetrain.stopGyroCalibrating();

		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		Scheduler.getInstance().enable();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * Returns the status of DIO pin 24 on the MXP. Place a jumper between pin
	 * pin 32 and 30 on the MXP to indicate this RoboRio is installed on the
	 * practice bot.
	 *
	 * @return true if this is the practice robot
	 */
	public static boolean isPracticeRobot() {
		return !practiceBot.get();
	}

	/**
	 * Method which checks to see if gyro drifts and resets the gyro. Call this
	 * in a loop.
	 */
	private void gyroReinit() {
		//Check to see if the gyro is drifting, if it is re-initialize it.
		//Thanks FRC254 for orig. idea.
		curAngle = drivetrain.getHeading();
		gyroCalibrating = drivetrain.isGyroCalibrating();

		if (lastGyroCalibrating && !gyroCalibrating) {
			//if we've just finished calibrating the gyro, reset
			gyroDriftDetector.reset();
			curAngle = drivetrain.getHeading();
			System.out.println("Finished auto-reinit gyro");
		} else if (gyroDriftDetector.update(Math.abs(curAngle - lastAngle) > (0.75 / 50.0))
				&& !matchStarted && !gyroCalibrating) {
			//&& gyroReinits < 3) {
			gyroReinits++;
			System.out.println("!!! Sensed drift, about to auto-reinit gyro ("+ gyroReinits + ")");
			drivetrain.calibrateGyro();
		}

		lastAngle = curAngle;
		lastGyroCalibrating = gyroCalibrating;
	}

	/**
	 * Creates Autonomous mode chooser.
	 */
	private void autoSelectInit() {
		// NOTE: ONLY ADD AutoCommandGroup objects to this chooser!
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No Tote _ Drive Forward", new Auto_NoTote_DriveForward());
		autoChooser.addObject("No Tote _ Do Nothing", new Auto_NoTote_DoNothing());
		autoChooser.addObject("One Tote _ Rotate Push Fwd", new Auto_OneTote_Rotate90Push());


		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);

	}

	public static String getAutoName() {
		if (autonomousCommand != null) {
			return autonomousCommand.getName();
		} else {
			return "None";
		}
	}

	public void pathPlanner() {
		long start = System.currentTimeMillis();
		// create waypoint path
		double[][] waypoints = new double[][] { { 4, 6 }, { 4, 16 } };

		double totalTime = 5; // seconds
		double timeStep = 0.07; // period of control loop on Rio, seconds
		double robotTrackWidth = 2; // distance between left and right wheels,
		// feet

		path = new FalconPathPlanner(waypoints);
		path.calculate(totalTime, timeStep, robotTrackWidth);

		System.out.println("Time in ms: "
				+ (System.currentTimeMillis() - start));
	}
}
