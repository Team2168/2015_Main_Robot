package org.team2168.commands.intake;

import org.team2168.Robot;
import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveIntakeToVoltage extends Command {

	double limit = 0.0;
	boolean greaterThan = false;

	/**
	 *
	 * @param voltage voltage level to compare against
	 * @param greaterThan True to stop the command if the voltage see is greater than provided
	 *                    False to stop the command if the voltage seen is less than provided
	 */
	public DriveIntakeToVoltage(double voltage, boolean greaterThan) {
		requires(Robot.intake);
		limit = voltage;
		this.greaterThan = greaterThan;
	}

	/**
	 * Default constructor. Drives the intake until a voltage greater than the
	 *   one provided is seen by the intake sensor.
	 * @param Value the voltage the intake sensor needs to exceed
	 */
	public DriveIntakeToVoltage(double voltage) {
		this(voltage, true);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intake.setIntakeSpeed(RobotMap.INTAKE_WHEEL_SPEED);
	}

	/**
	 * Drive the intake wheels in until a voltage value greater than the one specified is seen.
	 */
	protected boolean isFinished() {
		if (greaterThan) {
			return Robot.intake.getAveragedRawToteDistance() > limit;
		}else{
			return Robot.intake.getAveragedRawToteDistance() < limit;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
