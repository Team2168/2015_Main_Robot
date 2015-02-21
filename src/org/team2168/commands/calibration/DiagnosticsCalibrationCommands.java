package org.team2168.commands.calibration;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.commands.calibration.MotorCalibrationDrivetrainLeft;
import org.team2168.commands.calibration.MotorCalibrationDrivetrainRight;
import org.team2168.commands.calibration.MotorCalibrationIntake;
import org.team2168.commands.calibration.MotorCalibrationLift;
import org.team2168.commands.calibration.MotorCalibrationWinch;

/**
 *Runs all calibration commands, starting with drivetrain left, then right, then intake, then lift, then lastly winch.
 */
public class DiagnosticsCalibrationCommands extends CommandGroup {
    
    public  DiagnosticsCalibrationCommands() {
    	addSequential(new MotorCalibrationDrivetrainLeft());
    	addSequential(new MotorCalibrationDrivetrainRight());
    	addSequential(new MotorCalibrationIntake());
    	addSequential(new MotorCalibrationLift());
    	addSequential(new MotorCalibrationWinch());
    }
}
