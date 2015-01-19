package org.team2168.commands;
import org.team2168.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeIn extends Command {

	Intake intake = Intake.getInstance();
	
    public IntakeIn() {
    	requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.engageIntake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.runIntakeIn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (intake.isTotePresent()) {
        	return true;
        }else{
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.releaseIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
