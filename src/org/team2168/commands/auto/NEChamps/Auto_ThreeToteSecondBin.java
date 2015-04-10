package org.team2168.commands.auto.NEChamps;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.DriveXDistanceUntilObject;
import org.team2168.commands.drivetrain.RotateAboutRightWheel;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.DriveIntakeWheelIndependt;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.intake.StopIntakeWheels;
import org.team2168.commands.intake.WaitForIntakeToClearObject;
import org.team2168.commands.intake.WaitForObjectInIntake;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *This command drives in a straight line and performs a three
 *tote stack atonomously. This autonomous assumes the robot is
 *placed holding 1 tote, and that the 2nd bin is removed.. This only removes the First Bin
 */
public class Auto_ThreeToteSecondBin extends CommandGroup {
    
    public  Auto_ThreeToteSecondBin() { 
    	//Start with first tote in robot
    	//Intake 1st tote
    	addParallel(new IntakeSingleTote(), 5);    	
    	
    	//Zeros the lift
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);
    	
    	
    	//Wait for partner to move both bins out of the way
    	addSequential(new Sleep(), 1.25);
    	
    	
    	//lift 1st tote above tote height
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(15, 1), 1); // raise 1st tote above next tote    	
    	addSequential(new WaitForIntakeToClearObject()); //wait for object to clear sensor
    	
    	//Drive to second tote while intaking
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(7.5, 1, 0.5), 2);
    	addSequential(new DriveXDistanceUntilObject(4.5, 0.3),2.5);
    	
    	//intake second tote
    	addSequential(new EngageIntake(), 2);
    	addSequential(new WaitForObjectInIntake()); // delay to allow the intake
    	addSequential(new StopIntakeWheels());

    	addSequential(new LiftPIDPosition(0, 1), 1.3); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	
    	addSequential(new DisengageIntake(),2);
    	addParallel(new RotateXDistancePIDZZZ(-20, 0.7, 0.1, 4), 1.3);  //rotate to get bin off center
    	addSequential(new LiftPIDPosition(34, 1), 1.3);
    	
    	
    	//Roll 2nd bin out of the way towards right using intake wheels
    	addSequential(new DisengageIntake(), 2);
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	
    	//Drive around bin
    	addSequential(new DriveXDistance(3.5, 0.7, 0.5), 3); //drive slow to move bin
    	addSequential(new RotateXDistancePIDZZZ(40, 0.7, 0.1, 4), 2);  //rotate back
    	addSequential(new DriveXDistance(3.5, 0.7, 0.5), 4); //drive to get back to center line
    	addSequential(new RotateXDistancePIDZZZ(0, 0.7, 0.1, 4, true), 3);  //rotate back to absolute zero
    	
    	//bin is out of way so drive to next tote faster and lower lift in parallel
    	addParallel(new IntakeSingleTote());
    	addParallel(new LiftPIDPosition(18, 1), 1.5);
    	addParallel(new DriveXDistance(3, 0.7, 0.5), 2);
    	addSequential(new WaitForIntakeToClearObject());
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(5.45, 0.35),2.5);
    	    	
    	//Acquired 3rd tote so just drive to auto zone
    	addSequential(new EngageIntake(),2);
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateAboutRightWheel(85, 0.6), 1.3);
    	addParallel(new DriveXDistance(13.5, 1, 0.5), 1.5); //drive fast with stack
    	addSequential(new Sleep(), 0.3); //so we clear all bins before lower
    	
    	//at auto zone, so lets lower stack
    	addSequential(new WaitForObjectInIntake()); // delay to allow the intake
    	addSequential(new StopIntakeWheels());
    	addSequential(new LiftPIDPosition(0, 1), 1.3); // lower 2tote stack onto 3rd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new ReleaseGripper(),2);
    	addSequential(new DriveXDistance(-1, 1, 0.5), 2); //drive slow with statck
    
    	

    	
    }
}
