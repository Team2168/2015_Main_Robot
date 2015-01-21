package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.Debouncer;
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
	private Debouncer systemPressureSensorFailed;
	private Compressor compressor;

	/**
	 * A private constructor to prevent more than one instance from being created.
	 */
	private Pneumatics(){
		systemPressure = new AnalogInput(RobotMap.SYSTEM_PRESSURE);
		compressor = new Compressor(2);
		systemPressureSensorFailed = new Debouncer(1);
		
		//TODO fix this to run with commands
		compressor.setClosedLoopControl(true);
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

	/**
	 * Starts the compressor
	 */
	public void startPneumatics() {
		compressor.start();
	}
	
	/**
	 * Stops the compressor
	 */
	public void stopPneumatics() {
		compressor.stop();
	}
	
	/**
	 * Checks if the compressor is running
	 * @return true if compressor is running, false if not running
	 */
	public boolean isEnabled() {
		return compressor.enabled();
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
		double voltage = systemPressure.getVoltage();

		//If sensor is returning invalid values, flag as bad
		systemPressureSensorFailed.update(voltage < RobotMap.PRESS_SENSOR_LOW_VOLTAGE
				|| voltage > RobotMap.PRESS_SENSOR_HIGH_VOLTAGE);

		return voltage;
	}


	/**
	 * Check if the pressure sensor is returning valid data.
	 * @return true if failed.
	 */
	public boolean isPressureSensorFailed() {
		return systemPressureSensorFailed.getStatus();
	}

	/**
	 * Get the system pressure.
	 * @return value between 0 and 150 psi
	 */
	public double getPressure() {
		double slope = Util.slope(RobotMap.PRESS_SENSOR_LOW_VOLTAGE,
				RobotMap.PRESS_SENSOR_LOW_PRESSURE,
				RobotMap.PRESS_SENSOR_HIGH_VOLTAGE,
				RobotMap.PRESS_SENSOR_HIGH_PRESSURE);
		double intercept = Util.intercept(slope,
				RobotMap.PRESS_SENSOR_HIGH_VOLTAGE,
				RobotMap.PRESS_SENSOR_HIGH_PRESSURE);
		return slope * getRawPressure() + intercept;
	}
}

