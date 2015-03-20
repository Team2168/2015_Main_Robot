package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.arcb.ARCBDeploy;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateAboutLeftWheel;
import org.team2168.commands.drivetrain.RotateAboutRightWheel;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_ThreeToteDrivingAroundBin extends CommandGroup {
    
    public  Auto_ThreeToteDrivingAroundBin() { 


    	//Start with first tote in robot
    	addParallel(new IntakeSingleTote(),5);
    	
    	//Zeros the lift, deploys bin grabber
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);

    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(15, 1), 1.2);
    	   	
    	//rotate around 1st bin
    	addSequential(new RotateXDistancePIDZZZ(45, 0.6),2);
    	addSequential(new DriveXDistance(7, 0.6),1); 
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.6),2);
    	addSequential(new DriveXDistance(2, 0.5),3); //drive at angle
    	
  
    	//drive back to second tote  	
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.4),2); 
    	addSequential(new DriveXDistance(4.5, 0.6),3); 
    	addSequential(new RotateAboutRightWheel(-55, 0.9),2);
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(2, 0.7),2); //drive at angle
    	
    	
    	//aquired 2nd tote, so now we lift
    	addSequential(new LiftPIDPosition(0, 0.7), 1.2); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(15, 1), 1.2); // raise 2nd tote above garbage can
    	

    	//rotate around 2nd bin
    	addSequential(new RotateXDistancePIDZZZ(45, 0.6),2);
    	addSequential(new DriveXDistance(7, 0.6),1); 
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.6),2);
    	addSequential(new DriveXDistance(2, 0.5),3); //drive at angle
    	
  
    	//drive back to third tote  	
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.4),2); 
    	addSequential(new DriveXDistance(4.5, 0.6),3); 
    	addSequential(new RotateAboutRightWheel(-55, 0.9),2);
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(2, 0.7),2); //drive at angle
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateXDistancePIDZZZ(85, 0.4),1.3);
    	addParallel(new DriveXDistance(16, 1),4.2); //drive slow with stack
    	
    	
    	//at auto zone, so lets lower stack
    	addSequential(new LiftPIDPosition(0, 0.7), 2); // lower 2tote stack onto 3rd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new ReleaseGripper(),2);
    	//addSequential(new DriveXDistance(-0.2, 1),2); //drive slow with statck
    	

    	
    }
}
