package org.team2168.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

import org.team2168.RobotMap;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.Compressor;
//start and stop compressor
//get system pressure
/**
 *
 */
	
public class Pneumatics extends Subsystem {
    private static Pneumatics instance = null;
	AnalogInput systemPressure;
	Compressor compressor;
	
	private Pneumatics(){
		systemPressure = new AnalogInput(RobotMap.SYSTEM_PRESSURE);
		compressor = new Compressor();
	}
	
	/**
	 * @return an instance of the subsystem
	 */
	public static Pneumatics getInstance() {
		if(instance == null) {
			instance = new Pneumatics();
		}

		return instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //0,0.5
    //150,4.5
    
   /**
    * Gets the raw voltage reading of the pressure sensor
    * @return A value from 0 - 5 
    */
    public double getRawPressure(){
    	return systemPressure.getVoltage();
    }
    
    
    /**
     * 
     * @return value between 0 and 150
     */
    public double getPressure(){
    	double slope = Util.slope(0.5, 0, 4.5, 150);
    	double intercept = Util.intercept(slope, 4.5, 150);
    	return slope * getRawPressure() + intercept;
    }
}

