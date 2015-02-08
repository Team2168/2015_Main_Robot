package org.team2168;

import org.team2168.replay.Replay;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.Gripper;
import org.team2168.subsystems.Intake;
import org.team2168.subsystems.Lift;
import org.team2168.subsystems.Pneumatics;
import org.team2168.subsystems.Winch;
import org.team2168.utils.ConsolePrinter;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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

    //Replay file data
    public static boolean endWrite;
    public static boolean writeComplete;
    
    // Subsystem objects
    public static Drivetrain drivetrain;
    public static Intake intake;
    public static Lift lift;
    public static Winch winch;
    public static Gripper gripper;
    public static Pneumatics pneumatics;

    //Replay object
    public static Replay replay;
    
    //SmartDash printer
	ConsolePrinter printer;
    
    public static BuiltInAccelerometer accel;

    // Auto command objects
    Command autonomousCommand;
    
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

		//Create instance of replay object
        replay = new Replay();
        
        //create thread to write dashboard variables
		printer = new ConsolePrinter(20);
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
        SmartDashboard.putNumber("Tote Distance(inches)", Robot.intake.getToteDistance());
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
        endWrite = true;
    }

    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	if (endWrite && !writeComplete) {
    		replay.end();
    		writeComplete = true;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        replay.flushDataToFile(drivetrain.getMotorData(), drivetrain.getEncodersData(),
        		drivetrain.getHeading());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

}
