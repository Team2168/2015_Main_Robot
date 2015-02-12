package org.team2168.commandgroups;

import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.SetLiftPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveLiftToSetPosition extends CommandGroup {

	public  DriveLiftToSetPosition(double position) {
		addSequential(new DisableBrake());
		addSequential(new SetLiftPosition(position));
		addSequential(new EnableBrake());
	}
}
