package org.team2168.commands.lift;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveLiftToSetPosition extends CommandGroup {
    
    public  DriveLiftToSetPosition(double position) {
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
    	
    	requires(Robot.lift);
    	
    	addSequential(new SetLiftPosition(position));
    	addSequential(new AcuateBreak(position));
    	
    }
}
