package org.team2168.commandgroups;

import org.team2168.RobotMap;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.lift.DisableBrake;
import org.team2168.commands.lift.EnableBrake;
import org.team2168.commands.lift.SetLiftPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftTote extends CommandGroup {

	public  LiftTote() {
		addSequential(new ReleaseGripper());
		addSequential(new DisableBrake());
		addSequential(new SetLiftPosition(RobotMap. LIFT_ABOVE_TOTE_HEIGHT));
		addSequential(new EngageGripper());
		addSequential(new SetLiftPosition(RobotMap.LIFT_TOTE_ENGAGE_HEIGHT));
		addSequential(new SetLiftPosition(RobotMap.LIFT_CARRYING_TOTE_HEIGHT));
		addSequential(new EnableBrake());
	}
}
