package org.team2168.commands.calibration;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs a self test on all the motors on the robot.
 * This command group will automatically sequence through all motors and cause motion
 * of each subsystem. Verify it's ok for components to move before running this!
 */
public class TestAllMotors extends CommandGroup {
	private final static boolean FORWARD = true;
	private final static boolean REVERSE = false;

	public TestAllMotors() {
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1, FORWARD));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1, REVERSE));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2, FORWARD));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2, REVERSE));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3, FORWARD));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3, REVERSE));

		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_LEFT_MOTOR_1, FORWARD));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_LEFT_MOTOR_1, REVERSE));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_LEFT_MOTOR_2, FORWARD));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_LEFT_MOTOR_2, REVERSE));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_LEFT_MOTOR_3, FORWARD));
		addSequential(new MotorCalibration(RobotMap.DRIVETRAIN_LEFT_MOTOR_3, REVERSE));

//		addSequential(new MotorCalibration(RobotMap.LIFT_MOTOR, FORWARD));
//		addSequential(new MotorCalibration(RobotMap.LIFT_MOTOR, REVERSE));
//
//		addSequential(new MotorCalibration(RobotMap.INTAKE_RIGHT_MOTOR, FORWARD));
//		addSequential(new MotorCalibration(RobotMap.INTAKE_RIGHT_MOTOR, REVERSE));
//
//		addSequential(new MotorCalibration(RobotMap.INTAKE_LEFT_MOTOR, FORWARD));
//		addSequential(new MotorCalibration(RobotMap.INTAKE_LEFT_MOTOR, REVERSE));
	}
}
