package org.team2168.commands;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    /**
     * This method runs the first time the command is run
     */
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * This command allows the potato to pass values to control the speed of both left and right motors.
     */
    protected void execute() {
    	Robot.drivetrain.tankDrive(OI.driverJoystick.getRawAxis(RobotMap.driverLeftstick),
    			OI.driverJoystick.getRawAxis(RobotMap.driverRightstick));
    }

    /**
     * This method ends the command when it returns true
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * This method ends the command when isFinished returns true
     */
    protected void end() {
    }

    /**
     * This method tells the robot what to do if another command interupts this one
     */
    protected void interrupted() {
    }
}
