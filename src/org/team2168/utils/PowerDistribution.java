package org.team2168.utils;

import java.util.TimerTask;

import org.team2168.RobotMap;
import edu.wpi.first.wpilibj.PowerDistributionPanel;



public class PowerDistribution {
	private java.util.Timer executor;
	private long period;
	
	private PowerDistributionPanel pdp;
	
	volatile double[] channelCurrent;
	volatile double[] channelVoltage;
	volatile int[] channelError;
	
	volatile double batteryVoltage; 
	volatile double mainBreakerTemp;
	
	volatile double totalCurrent;
	volatile double totalEnergy;
	volatile double totalPower;
	volatile double temp;
	
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
}
