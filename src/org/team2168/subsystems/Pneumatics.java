package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
//start and stop compressor
//get system pressure
/**
 *
 */

public class Pneumatics extends Subsystem {
	private static Pneumatics instance = null;
	private AnalogInput systemPressure;
	private Compressor compressor;

	/**
	 * A private constructor to prevent more than one instance from being created.
	 */
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

	/**
	 * Gets the raw voltage reading of the pressure sensor
	 * @return A value from 0 - 5
	 */
	public double getRawPressure() {
		//TODO: Add code to verify that the sensor is returning valid data.
		//      Values shouldn't be less than 0.5V or greater than 4.5V.
		return systemPressure.getVoltage();
	}


	/**
	 * Get the system pressure.
	 * @return value between 0 and 150 psi
	 */
	public double getPressure() {
		double slope = Util.slope(0.5, 0, 4.5, 150);
		double intercept = Util.intercept(slope, 4.5, 150);
		return slope * getRawPressure() + intercept;
	}
}

