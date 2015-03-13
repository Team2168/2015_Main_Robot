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
public class Auto_NoTote_DriveForward extends CommandGroup {
    
    public  Auto_NoTote_DriveForward() {

    	//Drives fwd
    	addSequential(new DriveXDistance(12, 0.4),10);
    	
    }
}
