package org.team2168.commands.auto;

import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_OneTote_Rotate90Push extends CommandGroup {
    
    public  Auto_OneTote_Rotate90Push() {

    	//Zeros the lift
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//Intakes the tote
    	addSequential(new IntakeSingleTote(),2);
    	
    	addParallel(new SetIntakeSpeed(-0.5),3);
    	
    	//Rotates 90 
    	addSequential(new RotateXDistancePIDZZZ(90, 0.4),4);
    	
    	//Drive 5 feet
    	addSequential(new DriveXDistance(15, 0.4),5);

    	
    }
}
