package org.team2168.commands.auto.NEChamps;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.rccb.DeployRCCB;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_RCCB_On_Bump extends CommandGroup {
    
    public  Auto_RCCB_On_Bump() {
    	addSequential(new DriveXDistance(-8, 1, 0.2), 0.38); //hight setpoint for full speed drive
    	addSequential(new Sleep(),0.1);
    	addSequential(new DeployRCCB());
    	addSequential(new Sleep(), 0.3);
    	addSequential(new DriveXDistance(8, 1,0.5),2);
    	
   	
    }
}
