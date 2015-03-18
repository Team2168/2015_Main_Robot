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
 *This command drives in a straight line and performs a three
 *tote stack atonomously. This autonomous assumes the robot is
 *placed holding 1 tote, and that 2 bins are moved by partners
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
    	
    	
    	//Wait for partner to move both bin out of the way
    	addSequential(new Sleep(), 1.25);
    	
    	
    	//lift 1st tote above tote height
    	addSequential(new DisengageIntake(),2);
    	addParallel(new LiftPIDPosition(15, 0.7), 1.2); // raise 1st tote above next tote    	
    	addSequential(new Sleep(), 1.3); //sleep so that tote clears IR sensor before next command runs
    	
    	    
    	//Drive to second tote while intaking
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(13, 0.6),3);

    	//intake second tote
    	addSequential(new LiftPIDPosition(0, 0.7), 1); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(),2);
    	addSequential(new ZeroLift(),2);
    	
    	//lift 2nd tote
    	addSequential(new DisengageIntake(),2); //let go of tote before lifting
    	addParallel(new LiftPIDPosition(16, 0.7), 1.2); // raise 2nd tote above next tote 
    	addSequential(new Sleep(), 1.3); //sleep so that tote clears IR sensor before next command runs
    	
    	
    	//bin is out of way so drive to next tote while intaking
    	addParallel(new IntakeSingleTote(),5);
    	addSequential(new DriveXDistance(10, 0.6),1.7); 
    	
    	
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
    
    	//DONE
    	

    	
    }
}
