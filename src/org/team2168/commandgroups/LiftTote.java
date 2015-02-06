package org.team2168.commandgroups;

import org.team2168.RobotMap;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.SetLiftPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftTote extends CommandGroup {
    
    public  LiftTote() {
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
    	
    	addSequential(new ReleaseGripper());
    	addSequential(new DisableBrake());
    	addSequential(new SetLiftPosition(RobotMap. LIFT_ABOVE_TOTE_HEIGHT));
    	addSequential(new EngageGripper());
    	addSequential(new SetLiftPosition(RobotMap.LIFT_TOTE_ENGAGE_HEIGHT));
    	addSequential(new SetLiftPosition(RobotMap.LIFT_CARRYING_TOTE_HEIGHT));
    	addSequential(new EnableBrake());
    	
    }
}
