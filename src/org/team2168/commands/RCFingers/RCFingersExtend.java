package org.team2168.commands.RCFingers;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RCFingersExtend extends Command {

	public RCFingersExtend(){
		requires(Robot.rcfingers);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Robot.rcfingers.extendLeft();
		Robot.rcfingers.extendRight();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
