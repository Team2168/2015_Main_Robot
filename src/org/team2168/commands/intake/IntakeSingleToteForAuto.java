package org.team2168.commands.intake;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class IntakeSingleToteForAuto extends CommandGroup {

	private final static boolean GREATER_THAN = true;
	private final static boolean LESS_THAN = false;

	public IntakeSingleToteForAuto() {
		//TODO: This sequence needs more testing!
		addSequential(new DisengageIntake());
		addSequential(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right)
		addSequential(new Sleep(), 0.2);
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_ENGAGE_VOLTAGE, GREATER_THAN));
		addSequential(new EngageIntake());
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_STOP_VOLTAGE, GREATER_THAN));
		addSequential(new StopIntakeWheels());
		addSequential(new DisengageIntake());
	}
}
