package org.team2168.commands;

import org.team2168.commands.arcb.ARCBRetract;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateAboutRightWheel;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OpenAll extends CommandGroup {
    
    public  OpenAll() {
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
    	
    	addParallel(new ReleaseGripper());
    	addParallel(new ARCBRetract());
    	addParallel(new DisengageIntake());
    	//DONE
    	
    }
}
