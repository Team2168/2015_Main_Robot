package org.team2168.commands;

import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DisengageStack extends CommandGroup {
    
    public  DisengageStack() {
    	
    	addSequential(new LiftPIDPosition(8, 1), 1.3); // lower 2tote stack onto 3rd tote
	
    	//release set
    	addSequential(new ReleaseGripper(), 2);
    }
}
