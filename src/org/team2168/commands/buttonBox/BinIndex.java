package org.team2168.commands.buttonBox;

import org.team2168.commands.binRetainer.EngageBinRetainer;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BinIndex extends CommandGroup {
    
    public  BinIndex() {
    	
    	addParallel(new EngageBinRetainer());
    	addSequential(new LiftPIDPosition(35, 1), 1.4);
    	
    	// DONE
    }
}
