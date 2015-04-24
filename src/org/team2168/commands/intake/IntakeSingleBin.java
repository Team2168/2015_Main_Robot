package org.team2168.commands.intake;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeSingleBin extends CommandGroup {
    
	private final static boolean GREATER_THAN = true;
	private final static boolean LESS_THAN = false;
	
    public  IntakeSingleBin() {
    	
		addSequential(new DisengageIntake());
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_STOP_VOLTAGE, GREATER_THAN));	
    }
}
