package org.team2168.commands.auto.NEChamps;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.rccb.DeployRCCB;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *This command drives in a straight line and performs a three
 *tote stack atonomously. This autonomous assumes the robot is
 *placed holding 1 tote, and that the 2nd bin is removed.. This only removes the First Bin
 */
public class Auto_RCCB_Fast extends CommandGroup {

	public  Auto_RCCB_Fast() {

		addSequential(new DriveXDistance(-8, 1, 0.2), 0.38); //hight setpoint for full speed drive
		addParallel(new DriveXDistance(-1, 0.5), 2); // This is here to counter act the forward motion - gets canceled when driving forward.
		//addSequential(new Sleep(),0.1);
		addSequential(new DeployRCCB());
		addSequential(new Sleep(), 0.7);
		addSequential(new DriveXDistance(8, 1,0.5),2);

	}
}
