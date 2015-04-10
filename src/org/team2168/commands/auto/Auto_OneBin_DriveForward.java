package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.binRetainer.DisengageBinRetainer;
import org.team2168.commands.binRetainer.EngageBinRetainer;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateAboutLeftWheel;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_OneBin_DriveForward extends CommandGroup {
    
    public  Auto_OneBin_DriveForward() {

    	//Drives fwd
    	addSequential(new EngageBinRetainer());
    	addSequential(new EngageGripper());
    	addSequential(new Sleep(), 0.1);
    	addParallel(new LiftPIDPosition(12, 0.6 ), 1.1);
    	addSequential(new RotateAboutLeftWheel(40, 0.7), 1.1);
    	addSequential(new DriveXDistance(-12, 0.325), 5);
    	
    }
}
