package org.team2168.commands.buttonBox;

import org.team2168.commands.OpenAll;
import org.team2168.commands.intake.IntakeSingleBin;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BinIntakeVertical extends CommandGroup {
    
    public  BinIntakeVertical() {
    	
    	addParallel(new OpenAll());
    	
    	addSequential(new LiftPIDPosition(0, 1), 1.4);
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	
    	addSequential(new IntakeSingleBin());
    	
    	// TODO : Add stop intake wheels for intake single bin when button is released
    	
    	// DONE
    }
}
