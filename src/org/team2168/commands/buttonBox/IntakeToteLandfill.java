package org.team2168.commands.buttonBox;

import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.IntakeSingleToteLandfill;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeToteLandfill extends CommandGroup {
    
    public  IntakeToteLandfill() {
    	addSequential(new IntakeSingleToteLandfill());
    	
    	// Done
    }
}
