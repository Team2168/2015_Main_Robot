package org.team2168.commands.intake;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class IntakeSingleToteStartOpen extends CommandGroup {

	private final static boolean GREATER_THAN = true;
	private final static boolean LESS_THAN = false;

	public IntakeSingleToteStartOpen() {
		addSequential(new DisengageIntake());
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_ENGAGE_VOLTAGE, GREATER_THAN));
		addSequential(new EngageIntake());
		addSequential(new DriveIntakeToVoltage(RobotMap.INTAKE_TOTE_STOP_VOLTAGE, GREATER_THAN));
		addSequential(new StopIntakeWheels());
	}
}
