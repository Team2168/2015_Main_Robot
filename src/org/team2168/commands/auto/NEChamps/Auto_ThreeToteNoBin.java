package org.team2168.commands.auto.NEChamps;

import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.DriveXDistance;
import org.team2168.commands.drivetrain.DriveXDistanceUntilObject;
import org.team2168.commands.drivetrain.RotateXDistance;
import org.team2168.commands.drivetrain.RotateXDistancePIDZZZ;
import org.team2168.commands.gripper.EngageGripper;
import org.team2168.commands.gripper.ReleaseGripper;
import org.team2168.commands.intake.DisengageIntake;
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
 *placed holding 1 tote, and that 2 bins are moved by partners
 */
public class Auto_ThreeToteNoBin extends CommandGroup {
    
    public  Auto_ThreeToteNoBin() { 
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
    	addSequential(new DriveXDistance(7, 0.7), 2.5);
    	addSequential(new DriveXDistanceUntilObject(13, 0.4), 2.5);
    	
    	//intake second tote
    	addSequential(new EngageIntake(), 2);
    	addSequential(new WaitForObjectInIntake()); // delay to allow the intake
    	addSequential(new StopIntakeWheels());

    	addSequential(new LiftPIDPosition(0, 0.8), 1.3); // lower 1st tote onto 2nd tote
    	addSequential(new ZeroLift(), 2);
    	addSequential(new ZeroLift(), 2);
    	
    	//lift 2nd tote
    	addSequential(new DisengageIntake(), 2); //let go of tote before lifting
    	addSequential(new LiftPIDPosition(16, 1), 2); // raise 2nd tote above next tote 
    	addSequential(new WaitForIntakeToClearObject()); //wait for object to clear sensor
    	
    	
    	//bin is out of way so drive to next tote while intaking
    	addParallel(new IntakeSingleTote(), 5);
    	//addSequential(new DriveXDistance(10, 0.4), 1.7);
    	addSequential(new DriveXDistance(7, 0.7), 2.5);
    	addSequential(new DriveXDistanceUntilObject(13, 0.4), 2.5);
    	
    	
    	//Acquired 3rd tote so just drive to auto zone
    	//addParallel(new SetIntakeSpeed(0.5), 2);
    	//intake second tote
    	addSequential(new EngageIntake(), 2);
    	addParallel(new SetIntakeSpeed(0.5), 2);
    	addSequential(new WaitForObjectInIntake()); // delay to allow the intake
    	addSequential(new RotateXDistancePIDZZZ(90, 0.4), 1.6);
    	addParallel(new DriveXDistance(16, 1), 4.2);
    	
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
