package org.team2168.utils;

import java.util.TimerTask;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;



public class PowerDistribution {
	private java.util.Timer executor;
	private long period;

	private PowerDistributionPanel pdp;

	private volatile double[] channelCurrent;
	private volatile double[] channelVoltage;
	private volatile int[] channelError;

	private volatile double batteryVoltage;
	private volatile double mainBreakerTemp;

	private volatile double totalCurrent;
	private volatile double totalEnergy;
	private volatile double totalPower;
	private volatile double temp;

	public static final int NUM_OF_PDP_CHANNELS = 16;
	
	public PowerDistribution(long period) {
		this.period = period;
		pdp = new PowerDistributionPanel();

		channelCurrent = new double[NUM_OF_PDP_CHANNELS];
		channelVoltage = new double[NUM_OF_PDP_CHANNELS];
		channelError = new int[NUM_OF_PDP_CHANNELS];
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new PowerDistributionTask(this), 0L, this.period);

	}

	public int getChannelError(int channel)
	{
		return channelError[channel];
	}

	public double getChannelCurrent(int channel)
	{
		return channelCurrent[channel];
	}

	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	private void run() {

		for(int i=0; i<NUM_OF_PDP_CHANNELS; i++)
		{
			batteryVoltage = pdp.getVoltage();
			channelCurrent[i] = pdp.getCurrent(i);
			channelError[i] = 0; //no Error

			if(channelCurrent[i] > RobotMap.WARNING_CURRENT_LIMIT)
				channelError[i] = 1; //warning
			else if (channelCurrent[i] > RobotMap.STALL_CURRENT_LIMIT)
				channelError[i] = 2; //danger
		}


		totalCurrent = pdp.getTotalCurrent();
		totalEnergy = pdp.getTotalEnergy();
		temp = pdp.getTemperature();
		totalPower = pdp.getTotalPower();

	}

	private class PowerDistributionTask extends TimerTask {
		private PowerDistribution console;

		private PowerDistributionTask(PowerDistribution printer) {
			if (printer == null) {
				throw new NullPointerException("printer was null");
			}
			this.console = printer;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			console.run();
		}
	}
	
	/**
	 * Gets total Current
	 * @return Total Current
	 */
	public double getTotalCurrent() {
		return totalCurrent;
	}
	
	/**
	 * Gets total Energy
	 * @return Total Energy
	 */
	public double totalEnergy() {
		return totalEnergy;
	}

	/**
	 * Gets total Power
	 * @return Total Power
	 */
	public double totalPower() {
		return totalPower;
	}
	
	public boolean isChanelOneTrip() {
		if (channelError[1] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isChanelTwoTrip() {
		if (channelError[2] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isChanelThreeTrip() {
		if (channelError[3] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelFourTrip() {
		if (channelError[4] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelFiveTrip() {
		if (channelError[5] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelSixTrip() {
		if (channelError[6] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelSevenTrip() {
		if (channelError[7] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelEightTrip() {
		if (channelError[8] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelNineTrip() {
		if (channelError[9] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelTenTrip() {
		if (channelError[10] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelElevenTrip() {
		if (channelError[11] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelTwelveTrip() {
		if (channelError[12] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelThirteenTrip() {
		if (channelError[13] == 2)
			return true;
		else
			return false;
	}
	
	public boolean isChanelFourteenTrip() {
		if (channelError[14] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelFifteenTrip() {
		if (channelError[15] == 2) 
			return true;
		else
			return false;
	}
	
	public boolean isChanelSixteenTrip() {
		if (channelError[16] == 2) 
			return true;
		else
			return false;
	}
}
