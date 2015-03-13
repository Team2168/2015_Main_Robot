package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.DriveXDistance;
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
public class Auto_ThreeToteNoBin extends CommandGroup {
    
    public  Auto_ThreeToteNoBin() { 
    	//Start with first tote in robot
    	//Intake 1st tote
    	addParallel(new IntakeSingleTote(),5);    	
    	
    	//Zeros the lift
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);
    	
    	
    	//Wiat for partner to move both bin out of the way
    	addSequential(new Sleep(), 1.25);
    	
    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	//addSequential(new LiftPIDPosition(20, 1), 2.5); // raise 1st tote tote above next tote
    	addParallel(new LiftPIDPosition(15, 1), 1.2); // raise 1st tote above next tote    	
    	
    	
    	addSequential(new Sleep(), 1.3); //change to wait until no 
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	
    	
    	addSequential(new DriveXDistance(13, 0.6),3); //drive slow to move bin
    	//aquired 2nd tote, so now we lift
    	addSequential(new LiftPIDPosition(1, 0.8), 1); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new Sleep(), 0.2); //TODO : Why do we need this?
    	addSequential(new DisengageIntake(),2);
    	//addSequential(new LiftPIDPosition(35, 1), 2.5); // raise 2nd tote above garbage can
    	addParallel(new LiftPIDPosition(16, 1), 1.2); // raise 1st tote above next tote 
    	
    	addSequential(new Sleep(), 1.3);
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),5);
    	//TODO : WHyare this drive distance different than the previouse drive distance of 6.5
    	addSequential(new DriveXDistance(10, 0.6),1.7); //drive slow to move bin
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addParallel(new SetIntakeSpeed(0.5),2);
    	addSequential(new RotateXDistancePIDZZZ(85, 0.4),1.3);
    	addParallel(new DriveXDistance(16, 1),4.2); //drive slow with stack
    	
    	
    	//at auto zone, so lets lower stack
    	addSequential(new LiftPIDPosition(1, 0.8), 2); // lower 2tote stack onto 3rd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new ReleaseGripper(),2);
    	//addSequential(new DriveXDistance(-0.4, 0.5),2); //drive slow with statck
    	
    	//DONE
    	

    	
    }
}
