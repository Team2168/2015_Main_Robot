package org.team2168.commands.buttonBox;

import org.team2168.commands.OpenAll;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.lift.PIDCommands.LiftPIDPositionConditional;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Score extends CommandGroup {
    
    public  Score() {
    	
    	addSequential(new DisengageIntake());
    	addSequential(new LiftPIDPositionConditional(), 2);
    	addSequential(new OpenAll());
    	
    }
}
