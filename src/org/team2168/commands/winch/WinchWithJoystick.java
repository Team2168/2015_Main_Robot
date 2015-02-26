package org.team2168.commands.winch;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.F310;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the winch with a joystick
 */
public class WinchWithJoystick extends Command {

	F310 joystick;
	
    public WinchWithJoystick(F310 joystick) {
    	this.joystick = joystick;
        requires(Robot.winch);
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	Robot.winch.drive((OI.operatorJoystick.getRightTriggerAxisRaw()
				-OI.operatorJoystick.getLeftTriggerAxisRaw()));
    }

    
   /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
