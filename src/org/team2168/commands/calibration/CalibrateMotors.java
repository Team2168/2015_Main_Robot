package org.team2168.commands.calibration;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CalibrateMotors extends CommandGroup {
    
    public  CalibrateMotors() {
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
    	
    	// Drive train motors
    	addSequential(new MotorCalibration(0));
    	addSequential(new MotorCalibration(1));
    	addSequential(new MotorCalibration(2));
    	addSequential(new MotorCalibration(3));
    	addSequential(new MotorCalibration(4));
    	addSequential(new MotorCalibration(5));
    	
    	// Intake motors
    	addSequential(new MotorCalibration(6));
    	addSequential(new MotorCalibration(7));
    	
    	// Lift Motor
    	addSequential(new MotorCalibration(8));
    	
    }
}
