package org.team2168.commands.auto;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.gripper.EngageGripper;
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
public class Auto_ThreeToteStack extends CommandGroup {
    
    public  Auto_ThreeToteStack() {    	
    	//Start with first tote in robot
    	//Intake 1st tote
    	addParallel(new IntakeSingleTote(),2);
    	
    	//Zeros the lift
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new EngageGripper(),2);
    	
    	//lift 1st tote above can height
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(40, 1), 4); // raise 1st tote above garbage can
    	
    	
    	//Start spinning wheels backwards for 1st bin and driving fwd
    	addSequential(new EngageIntake(),2);
    	addParallel(new SetIntakeSpeed(-1),1);
    	addParallel(new DisengageIntake());
    	addParallel(new SetIntakeSpeed(-1));
    	addSequential(new DriveXDistance(6, 0.2),5); //drive slow to move bin
    	
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),2);
    	addSequential(new DriveXDistance(5, 0.4),5); //drive slow to move bin
    	
    	
    	//aquired 2nd tote, so now we lift
    	addSequential(new LiftPIDPosition(1, 0.8), 4); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	addSequential(new Sleep(), 2);
    	addSequential(new DisengageIntake(),2);
    	addSequential(new LiftPIDPosition(40, 1), 4); // raise 2nd tote above garbage can
    	
    	
    	//Start spinning wheels backwards for 2nd bin and driving fwd
    	addSequential(new EngageIntake(),2);
    	addParallel(new SetIntakeSpeed(-1),3);
    	addSequential(new DriveXDistance(6, 0.2),5); //drive slow to move bin
    	
    	//bin is out of way so drive to next tote faster
    	addParallel(new IntakeSingleTote(),2);
    	addSequential(new DriveXDistance(6, 0.4),5); //drive slow to move bin
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	addParallel(new SetIntakeSpeed(0.5),3);
    	addSequential(new RotateXDistancePIDZZZ(90, 0.4),4);
    	addSequential(new DriveXDistance(12, 0.5),5); //drive slow with stack
    	
    	
    	//at auto zone, so lets lower stack
    	addSequential(new LiftPIDPosition(1, 0.8), 4); // lower 2tote stack onto 3rd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//release set
    	addSequential(new DisengageIntake(),2);
    	addSequential(new DriveXDistance(2, 0.5),5); //drive slow with statck
    	
    	//DONE
    	

    	
    }
}
