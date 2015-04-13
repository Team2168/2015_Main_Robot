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
public class Auto_ThreeToteFirstBin extends CommandGroup {
    
    public  Auto_ThreeToteFirstBin() { 
    	addParallel(new IntakeSingleTote(),5);
    	
    	//Zeros the lift, deploys bin grabber
    	addSequential(new ZeroLift()); //no timeout for safe lift operation
    	addSequential(new ZeroLift(), 2);
    	addSequential(new EngageGripper(), 2);

    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(), 2);
    	addParallel(new RotateXDistancePIDZZZ(-20, 0.7, 0.1, 4), 1.3);  //rotate to get bin off center
    	addSequential(new LiftPIDPosition(34, 1), 1.3);

    	//Roll 1st bin out of the way towards right using intake wheels
    	addSequential(new DisengageIntake(), 2);
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	
    	//Drive around bin
    	addSequential(new DriveXDistance(3.5, 0.7, 0.5), 3); //drive slow to move bin
    	addSequential(new RotateXDistancePIDZZZ(40, 0.7, 0.1, 4), 2);  //rotate back
    	addSequential(new DriveXDistance(3.5, 0.7, 0.5), 4); //drive to get back to center line
    	addSequential(new RotateXDistancePIDZZZ(0, 0.7, 0.1, 4, true), 3);  //rotate back to absolute zero
    	
    	//bin is out of way so drive to next tote faster and lower lift in parallel
    	addParallel(new IntakeSingleTote());
    	addParallel(new LiftPIDPosition(16, 1), 1.1); // raise 2nd tote above next tote
    	addParallel(new DriveXDistance(3, 0.7, 0.5), 2);
    	addSequential(new WaitForIntakeToClearObject());
    	addSequential(new DriveXDistanceUntilObject(13, 0.45), 2.5);

    	//intake second tote
      	addSequential(new EngageIntake(), 2);
    	addSequential(new WaitForObjectInIntake()); //delay to allow the intake
    	addSequential(new StopIntakeWheels());

    	//lift second tote
    	addSequential(new LiftPIDPosition(0, 1), 1.5); //lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	addSequential(new DisengageIntake());
    	addSequential(new LiftPIDPosition(16, 1), 1.3); //raise 2nd tote above garbage can

    	//assume 2nd bin is already removed
    	//bin is out of way so drive to next tote faster
    	addSequential(new WaitForIntakeToClearObject());    	
    	addParallel(new IntakeSingleTote(), 5);
    	addSequential(new DriveXDistance(7, 0.7), 2.5);
    	addSequential(new DriveXDistanceUntilObject(13, 0.4), 2.5);

    	//Acquired 3rd tote so just drive to auto zone
    	addSequential(new EngageIntake(), 2);
    	addParallel(new SetIntakeSpeed(0.5), 2);
    	addSequential(new WaitForObjectInIntake()); // delay to allow the intake
    	addSequential(new RotateAboutRightWheel(90, 1), 1);
    	addParallel(new DriveXDistance(16, 1), 4.2);

    	//at auto zone, so lets lower stack
    	addSequential(new Sleep(), 0.3);
    	addSequential(new StopIntakeWheels());
    	
    	addSequential(new LiftPIDPosition(8, 1), 1.3); // lower 2tote stack onto 3rd tote
    	//addSequential(new ZeroLift(),2);
    	//addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(), 2);
    	addSequential(new ReleaseGripper(), 2);
    	addSequential(new Sleep(), 0.1);
    	addSequential(new DriveXDistance(-2, 0.5), 2); //drive away to clear the stack
    }
}
