package org.team2168.commands.calibration;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.CalibrateDrivetrainMotor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CalibrateQuicrun1060 extends CommandGroup {
    
    public  CalibrateQuicrun1060() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new CalibrateDrivetrainMotor(1, 1), 0.01);
    	addSequential(new CalibrateDrivetrainMotor(-1, -1), 0.01);
    	addSequential(new CalibrateDrivetrainMotor(0 ,0), 0.01);
    	
    }
}
