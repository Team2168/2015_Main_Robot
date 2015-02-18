
package org.team2168;

import org.team2168.PIDController.pathplanner.FalconPathPlanner;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Gripper;
import org.team2168.subsystems.Intake;
import org.team2168.subsystems.Lift;
import org.team2168.subsystems.Pneumatics;
import org.team2168.subsystems.Winch;
import org.team2168.utils.ConsolePrinter;
import org.team2168.utils.PowerDistribution;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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

	//Power Monitor
	public static PowerDistribution pdp;
	
	//SmartDash printer
	ConsolePrinter printer;
	
	//path follower
	public static FalconPathPlanner path;
		
	
	public static BuiltInAccelerometer accel;

	// Auto command objects
	Command autonomousCommand;
	Command driveWithJoystick;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		drivetrain = Drivetrain.getInstance();
		intake = Intake.getInstance();
		lift = Lift.getInstance();
		winch = Winch.getInstance();
		gripper = Gripper.getInstance();
		pneumatics = Pneumatics.getInstance();

		accel = new BuiltInAccelerometer();
		
		pdp = new PowerDistribution(RobotMap.PDPThreadPeriod);
		pdp.startThread();

		long start = System.currentTimeMillis();
		//create waypoint path
		double[][] waypoints = new double[][]{
				{4, 6},
				{4, 16}
		}; 

		double totalTime = 10; //seconds
		double timeStep = 0.07; //period of control loop on Rio, seconds
		double robotTrackWidth = 2; //distance between left and right wheels, feet

		
		path = new FalconPathPlanner(waypoints);
		path.calculate(totalTime, timeStep, robotTrackWidth);

		System.out.println("Time in ms: " + (System.currentTimeMillis()-start));
	
		
        //create thread to write dashboard variables
		printer = new ConsolePrinter(RobotMap.SmartDashThreadPeriod);
		printer.startThread();

		oi = new OI();
		// instantiate the command used for the autonomous period
		// autonomousCommand = new ExampleCommand();
	}


	/**
	 * This method runs periodically when the robot is disabled
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This method initializes the autonomous commands
	 */
	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
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
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}


    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	Robot.drivetrain.gyroSPI.calibrate();
    	Robot.drivetrain.gyroAnalog.reInitGyro();
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
}
