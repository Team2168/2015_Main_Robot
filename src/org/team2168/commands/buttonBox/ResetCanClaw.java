package org.team2168.commands.buttonBox;

import org.team2168.commands.Sleep;
import org.team2168.commands.binRetainer.DisengageBinRetainer;
import org.team2168.commands.binRetainer.EngageBinRetainer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ResetCanClaw extends CommandGroup {
    
    public  ResetCanClaw() {
    	
    	addSequential(new DisengageBinRetainer());
    	addSequential(new Sleep(), 0.5);
    	addSequential(new EngageBinRetainer());
    	
    	// Done
    	
    }
}
