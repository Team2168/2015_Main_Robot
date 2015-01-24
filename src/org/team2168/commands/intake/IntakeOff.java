package org.team2168.commands.intake;
import org.team2168.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOff extends Command {
	
	Intake intake = Intake.getInstance();

    public IntakeOff() {
    	requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.releaseIntake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.stopIntake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
