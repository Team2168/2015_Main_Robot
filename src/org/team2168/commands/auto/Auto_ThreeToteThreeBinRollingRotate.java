package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.arcb.ARCBDeploy;
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
import org.team2168.commands.intake.IntakeSingleToteForAuto;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.intake.StopIntakeWheels;
import org.team2168.commands.intake.WaitForObjectInIntake;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_ThreeToteThreeBinRollingRotate extends CommandGroup {
    
    public  Auto_ThreeToteThreeBinRollingRotate() {
    	
    	//Start with first tote in robot
    	addParallel(new IntakeSingleTote(),5);
    	
    	//Zeros the lift, deploys bin grabber
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);
    	//addSequential(new ARCBDeploy(), 2);
    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(34, 1), 1.3);
    	
    	//total time is 1.8 seconds
    	
    	//Roll 1st bin out of the way towards right using intake wheels
    	addSequential(new DisengageIntake(), 2);
    	addSequential(new DisengageIntake(), 2);
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	addSequential(new RotateXDistancePIDZZZ(-20, 0.5, 0.1, 4), 2);  //rotate to get bin off center
    	addSequential(new DriveXDistance(3.5, 0.6, 0.5), 3); //drive slow to move bin
    	addSequential(new RotateXDistancePIDZZZ(20, 0.325, 0.1, 4), 2);  //rotate back
    	
    	addSequential(new DriveXDistance(3.5, 0.6, 0.5), 4); //drive to get back to center line
    	
    	addSequential(new DisengageIntake());
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	//addSequential(new Sleep(),0.3);
    	//total time is 3.3 seconds
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(), 5);
    	addSequential(new DriveXDistance(4, 0.5, 0.5), 1.6); //drive fast to get tote // 
    	addParallel(new DriveXDistance(2, 0.3, 0.5));
    	//total time is 5.3 seconds
    	
    	//aquired 2nd tote, so now we lift
    	addSequential(new WaitForObjectInIntake()); // delay to allow the intake
    	addParallel(new DriveXDistance(1, 0.3, 0.5));
    	//addSequential(new EngageIntake(), 2);
    	//addSequential(new StopIntakeWheels());  // if a tote shows up make sure to stop running wheels
    	addSequential(new LiftPIDPosition(0, 1), 10); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	addSequential(new DisengageIntake());
    	addSequential(new LiftPIDPosition(34, 1), 1.5); // raise 2nd tote above garbage can
    	
    	
    	//Roll 2nd bin out of the way towards right
    	addSequential(new DisengageIntake(), 2);
    	addParallel(new DriveIntakeWheelIndependt(-1, 1)); //drive bin to right
    	addSequential(new RotateXDistancePIDZZZ(-20, 0.5, 0.1, 4), 2);  //rotate to get bin off center
    	addSequential(new DriveXDistance(3.5, 0.6, 0.5),3.0); //drive slow to move bin
    	addSequential(new RotateXDistancePIDZZZ(20, 0.325, 0.1, 4), 2);  //rotate back
    	addSequential(new DriveXDistance(3.5, 0.6, 0.5), 4); //drive slow to move bin
    	
   
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(5.45, 0.6),2.5);
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addSequential(new EngageIntake(),2);
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateAboutRightWheel(85, 0.6), 1.3);
    	addParallel(new DriveXDistance(10, 1, 0.5), 1.5); //drive fast with stack
    	
    	
    	//at auto zone, so lets lower stack
    	addSequential(new LiftPIDPosition(0, 1), 10); // lower 2tote stack onto 3rd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new ReleaseGripper(),2);
    	addSequential(new DriveXDistance(-1, 1, 0.5), 2); //drive slow with statck
    
    	//DONE
    	

    	
    }
}
