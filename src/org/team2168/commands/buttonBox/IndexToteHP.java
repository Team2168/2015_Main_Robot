package org.team2168.commands.buttonBox;

import org.team2168.commands.DisengageStack;
import org.team2168.commands.Sleep;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class IndexToteHP extends CommandGroup {
    
    public  IndexToteHP() {
    	
    	//addSequential(new DisengageStack());
    	
    	addSequential(new LiftPIDPosition(0, 0.75), 1.4); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    
    	addSequential(new EngageGripper());
    	addSequential(new DisengageIntake());
    	addSequential(new Sleep(), 0.2);
    	addSequential(new LiftPIDPosition(18, 1), 5); // raise 1st tote above next tote    	
    	
    	// DONE
    	
    }
}
