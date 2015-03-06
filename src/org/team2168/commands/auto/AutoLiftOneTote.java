package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLiftOneTote extends CommandGroup {
    
    public  AutoLiftOneTote() {
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

    	//Zeros the lift
    	addSequential(new ZeroLift());
    	
    	//Intakes the tote
    	addSequential(new IntakeSingleTote());
    	
    	//Rotates 90 
    	addSequential(new RotateXDistance(90));
    	
    	//Drive 5 feet
    	addSequential(new DriveXDistance(5));
    	
    }
}
