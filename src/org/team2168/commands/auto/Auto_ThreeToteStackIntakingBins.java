package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.arcb.ARCBDeploy;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.DriveXDistanceUntilObject;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
import org.team2168.commands.intake.DriveIntakeWheelIndependt;
import org.team2168.commands.intake.EngageIntake;
import org.team2168.commands.intake.IntakeSingleTote;
import org.team2168.commands.intake.SetIntakeSpeed;
import org.team2168.commands.intake.WaitForIntakeToClearObject;
import org.team2168.commands.lift.LiftOneTote;
import org.team2168.commands.lift.ZeroLift;
import org.team2168.commands.lift.PIDCommands.LiftPIDPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command performs a 3 tote / 3 bin auto by intake and outtaking
 * bins
 *
 */
public class Auto_ThreeToteStackIntakingBins extends CommandGroup {
    
    public  Auto_ThreeToteStackIntakingBins() { 
    	
    	//Start with first tote in robot
    	addParallel(new IntakeSingleTote(),5);
    	
    	//Zeros the lift, deploys bin grabber
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);
    	addSequential(new ARCBDeploy(), 2);
    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(40, 0.7), 3);
    	
    	
    	//intake 1st bin
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(3, 0.3),4);
    	addSequential(new RotateXDistancePIDZZZ(45, 0.4),1.5);
    	addParallel(new SetIntakeSpeed(-1), 2); //release bin
    	addSequential(new WaitForIntakeToClearObject(), 2);
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.4),1.5);
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(4, 0.6),2.5);

    	   	
    	//aquired 2nd tote, so now we lift
    	addSequential(new LiftPIDPosition(0, 0.7), 3); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(40, 0.7), 3); // raise 2nd tote above garbage can
    	
    	
    	//intake 2nd bin
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(3, 0.3),4);
    	addSequential(new RotateXDistancePIDZZZ(45, 0.4),1.5);
    	addParallel(new SetIntakeSpeed(-1), 2); //release bin
    	addSequential(new WaitForIntakeToClearObject(), 2);
    	addSequential(new RotateXDistancePIDZZZ(-45, 0.4),1.5);
    	
   
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistanceUntilObject(4, 0.6),2.5);
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateXDistancePIDZZZ(85, 0.7),5);
    	addParallel(new DriveXDistance(16, 1),4.2); //drive fast with stack
    	
    	
    	//at auto zone, so lets lower stack
    	addSequential(new LiftPIDPosition(0, 0.7), 2); // lower 2tote stack onto 3rd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new ReleaseGripper(),2);
    	//addSequential(new DriveXDistance(-0.2, 1),2); //drive slow with statck
    
    	//DONE
    	

    	
    }
}
