package org.team2168.commands.intake;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class IntakeSingleTote extends CommandGroup {

	private final static boolean GREATER_THAN = true;
	private final static boolean LESS_THAN = false;

	public IntakeSingleTote() {
		//TODO: This sequence needs more testing!
		addSequential(new DisengageIntake());
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_ENGAGE_VOLTAGE, LESS_THAN));
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_ENGAGE_VOLTAGE, GREATER_THAN));
		addSequential(new EngageIntake());
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_STOP_VOLTAGE, GREATER_THAN));
		addSequential(new StopIntakeWheels());
	}
}
