package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RCCB extends Subsystem {

	private static RCCB instance = null;
	
	private DoubleSolenoid rccbSolenoid;
	
	private RCCB() {
		rccbSolenoid = new DoubleSolenoid(RobotMap.RCCBB_DOUBLE_SOLENOID_FORWARD, RobotMap.RCCB_BRAKE_DOUBLE_SOLENOID_REVERSE);
	}
	
	public static RCCB getInstance() {
		if(instance == null) {
			instance = new RCCB();
		}
		return instance;
	}
	
	public void EngageRCCB() {
		rccbSolenoid.set(Value.kForward);
	}
	
	public void DisengageRCCB() {
		rccbSolenoid.set(Value.kReverse);
	}
	
	public boolean isEngaged() {
		return rccbSolenoid.get() == Value.kForward;
	}
	
	public boolean isDisengaged() {
		return rccbSolenoid.get() == Value.kReverse;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

