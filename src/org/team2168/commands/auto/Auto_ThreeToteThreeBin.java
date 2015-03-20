package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.arcb.ARCBDeploy;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateAboutLeftWheel;
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
public class Auto_ThreeToteThreeBin extends CommandGroup {
    
    public  Auto_ThreeToteThreeBin() { 
    	//Start with first tote in robot
    	//Intake 1st tote
    	addParallel(new IntakeSingleTote(),5);    	
    	
    	//Zeros the lift
    	//addSequential(new ZeroLift(),2);
    	//addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);
    	
  
    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	//addSequential(new LiftPIDPosition(15, 1), 1.2); // raise 1st tote tote above next tote
    	   	
    	//rotate and grab bin 1st
    	addSequential(new ARCBDeployRight());
    	addSequential(new RotateXDistancePIDZZZ(-55, 0.4),2);
    	addSequential(new DriveXDistance(7, 0.5),1); //drive slow to move bin
    	addSequential(new RotateXDistancePIDZZZ(45, 0.4),2);
    	addSequential(new DriveXDistance(2, 0.5),3); //drive at angle
    	
  
    	//drive back to second tote  	
    	addSequential(new RotateXDistancePIDZZZ(45, 0.4),2); 
    	addSequential(new DriveXDistance(4.5, 0.5),3); //drive at angle
    	addSequential(new RotateAboutLeftWheel(-55, 0.7),2);
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(2, 0.7),2); //drive at angle
    	
    	
    	//Lift Second Tote
    	//addSequential(new LiftPIDPosition(1, 0.8), 1); // lower 1st tote onto 2nd tote
    	//addSequential(new ZeroLift(),2);
    	//addSequential(new ZeroLift(),2);
    	addSequential(new DisengageIntake(),2);
    	//addSequential(new LiftPIDPosition(16, 1), 1.2); // raise 1st tote above next tote 
    	
    	
    	//Grab second bin
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.7),2); //square up to tote, need more power because of drag
    	addSequential(new DriveXDistance(7, 0.5),3); //intake second tote
    	addSequential(new RotateXDistancePIDZZZ(45, 0.4),2);
    	addSequential(new DriveXDistance(2, 0.7),3); //drive at angle
    	
    	
    	addSequential(new Sleep(), 20);
    	
    	//drive back to third tote  	
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new RotateXDistancePIDZZZ(40, 0.4),2); //square up to tote
    	addSequential(new DriveXDistance(4.5, 0.5),3); //drive at angle
    	
    	
    	addSequential(new Sleep(), 20);
    	
    	
    	//rotate and grab bin 2nd
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.4),1.3);
    	addSequential(new DriveXDistance(7, 0.7),1); //drive slow to move bin
    	addSequential(new RotateXDistancePIDZZZ(55, 0.4),1.3);
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(8, 0.7),3); //drive at angle
    	addSequential(new RotateXDistancePIDZZZ(-25, 0.4),1.3); //square up to tote
    	addSequential(new DriveXDistance(6, 0.7),3); //intake second tote
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addSequential(new ARCBDeployLeft());
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateXDistancePIDZZZ(85, 0.4),1.3);
    	addParallel(new DriveXDistance(16, 1),4.2); //drive slow with stack
    	
    	
    	//at auto zone, so lets lower stack
    	//addSequential(new LiftPIDPosition(1, 0.8), 2); // lower 2tote stack onto 3rd tote
    	//addSequential(new ZeroLift(),2);
    	//addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new ReleaseGripper(),2);
    	//addSequential(new DriveXDistance(-0.4, 0.5),2); //drive slow with statck
    	
    	//DONE
    	

    	
    }
}
