package org.team2168.commands.buttonBox;

import org.team2168.commands.binRetainer.EngageBinRetainer;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IndexToteLandfill extends CommandGroup {
    
    public  IndexToteLandfill() {
    	
    	//addSequential(new LiftPIDPosition(8, 1), 1.4); // lower 1st tote onto 2nd tote
    	//addSequential(new EngageGripper());
    	
    	addSequential(new LiftPIDPosition(1, 1), 1.4); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	addSequential(new DisengageIntake());
    	addSequential(new LiftPIDPosition(22, 1), 1.6); // raise 2nd tote above garbage can
    	
    	// Done
    }
}
