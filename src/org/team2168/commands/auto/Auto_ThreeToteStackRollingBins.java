package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.arcb.ARCBDeploy;
import org.team2168.commands.drivetrain.DriveWithJoysticks;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.DriveXDistanceUntilObject;
import org.team2168.commands.drivetrain.RotateAboutLeftWheel;
import org.team2168.commands.drivetrain.RotateAboutRightWheel;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.drivetrain.PIDCommands.WaitForDrivePIDToFinish;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.DriveIntakeWheelIndependt;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.IntakeSingleToteForAuto;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.intake.StopIntakeWheels;
import org.team2168.commands.intake.WaitForIntakeToClearObject;
import org.team2168.commands.intake.WaitForObjectInIntake;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_ThreeToteStackRollingBins extends CommandGroup {
    
    public  Auto_ThreeToteStackRollingBins() {
    	
    	//Start with first tote in robot
    	addParallel(new IntakeSingleTote(),5);
    	
    	//Zeros the lift, deploys bin grabber
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);

    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(35, 1), 1.4);
    	
    	//total time is 1.8 seconds
    	
    	//Roll 1st bin out of the way towards right using intake wheels
    	addSequential(new EngageIntake());
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	addParallel(new DriveXDistance(4.6, 0.29), 3); //drive slow to move bin
    	//addSequential(new Sleep(), 2); //do nothing while driving
    	//addSequential(new DisengageIntake());
    	addSequential(new WaitForDrivePIDToFinish());
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	addSequential(new WaitForIntakeToClearObject());
    	//total time is 3.3 seconds
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleToteForAuto(), 5);
    	addSequential(new DriveXDistanceUntilObject(3.6, 0.6), 0.7); //drive fast to get tote // 
    	//total time is 5.3 seconds
    	
    	//aquired 2nd tote, so now we lift
    	addSequential(new WaitForObjectInIntake()); // if 2nd tote is not seen, auto will stop
    	addSequential(new LiftPIDPosition(0, 1), 1.4); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	addSequential(new DisengageIntake());
    	addSequential(new LiftPIDPosition(35, 1), 1.6); // raise 2nd tote above garbage can
    	
    	
    	//Roll 2nd bin out of the way towards right
    	addSequential(new EngageIntake());
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive been to right
    	addSequential(new DriveXDistance(4.6, 0.29), 3); //drive slow to move bin
//    	addSequential(new Sleep(), 2); //do nothing while driving
//    	addSequential(new DisengageIntake());
    	//addSequential(new WaitForDrivePIDToFinish());
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	addSequential(new WaitForIntakeToClearObject());
    	//addSequential(new DriveXDistance(3, 0.3),4); //drive slow to move bin
    	
   
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(5, 0.6),2.5);
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addSequential(new EngageIntake(),2);
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateXDistancePIDZZZ(90, 0.5), 1.6);
    	addParallel(new DriveXDistance(9, 1), 4.2);
    	
    	//at auto zone, so lets lower stack
    	addSequential(new Sleep(), 0.3);
    	addSequential(new StopIntakeWheels());
    	
    	addSequential(new LiftPIDPosition(8, 1), 1.3); // lower 2tote stack onto 3rd tote
//    	addSequential(new ZeroLift(),2);
//    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(), 2);
    	addSequential(new ReleaseGripper(), 2);
    	addSequential(new Sleep(), 0.4);
    	addSequential(new DriveXDistance(-2, 0.5), 2); //drive away to clear the stack
    
    	//DONE
    }
}
