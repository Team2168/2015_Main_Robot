package org.team2168.commands.buttonBox;

import org.team2168.commands.intake.IntakeSingleToteHP;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeToteHP extends CommandGroup {
    
    public  IntakeToteHP() {
    	addSequential(new IntakeSingleToteHP()); 
    }
}
