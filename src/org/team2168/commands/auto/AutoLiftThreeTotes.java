package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLiftThreeTotes extends CommandGroup {
    
	private static final double OUTTAKE_TIME_ELAPSED = 3;
	private static final double AMOUNT_TO_DRIVE = 7;
	
    public  AutoLiftThreeTotes() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new ZeroLift());
    	addSequential(new AutoOneTote());
    	addSequential(new AutoOneTote());
    	
    	
    	
    }
}
