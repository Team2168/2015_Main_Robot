package org.team2168.commandgroups;

import org.team2168.RobotMap;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.SetLiftPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeTote extends CommandGroup {

	public IntakeTote() {
		addSequential(new DisableBrake());
		addSequential(new SetLiftPosition(RobotMap.LIFT_ABOVE_TOTE_HEIGHT));
		addSequential(new EnableBrake());
		addSequential(new IntakeSingleTote());
		//addSequential(new SetIntakeWheelSpeed(0.0));
	}
}
