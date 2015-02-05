package org.team2168.commands.drivetrain;

import org.team2168.OI;
import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows the joysticks to pass values to control the speed of both left and right motors.
 */
public class DriveWithJoysticks extends Command {

	Double leftStick;
	Double rightStick;
	
	boolean driveWithJoystick;
	
	/**
	 * Call the command with this constructor to run it with Joysticks
	 */
	public DriveWithJoysticks() {
		requires(Robot.drivetrain);
		this.driveWithJoystick = true;
	}
	
	/**
	 * Call the command with this constructor to run it without joysticks
	 * @param leftStick
	 * @param rightStick
	 */
	public DriveWithJoysticks(Double leftStick, Double rightStick) {
		requires(Robot.drivetrain);
		this.leftStick = leftStick;
		this.rightStick = rightStick;
		
		this.driveWithJoystick = false;
	}

	/**
	 * This method runs the first time the command is run
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if (!(driveWithJoystick)) {
			Robot.drivetrain.tankDrive(this.leftStick, this.rightStick);
		}else {
			Robot.drivetrain.tankDrive(OI.driverJoystick.getLeftStickRaw_Y(), OI.driverJoystick.getRightStickRaw_Y());
		}
		//System.out.println("(" + leftStick + "," + rightStick + ")");
		//System.out.println("(" + OI.driverJoystick.getLeftStickRaw_Y() + "," + OI.driverJoystick.getRightStickRaw_Y());
		//Robot.drivetrain.tankDrive(-1.0,
		//		1.9);
	}

	/**
	 * This method ends the command when it returns true
	 */
	protected boolean isFinished() {
		return false;
	}

	/**
	 * This method runs once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 * This method tells the robot what to do if another command interrupts this one
	 */
	protected void interrupted() {
	}
}
