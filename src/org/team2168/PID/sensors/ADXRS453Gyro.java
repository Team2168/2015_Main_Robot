package org.team2168.PID.sensors;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.TimerTask;




import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;

/*
 *
 *
 *  Created on: Jan 18, 2015
 *      Author: Kevin Harrilal
 *      
 *
 *      
 *      
RATE DATA FORMAT

 */


public class ADXRS453Gyro implements PIDSensorInterface
{

	static final int DATA_SIZE = 4; //4 bytes = 32 bits
	static final  byte  PARITY_BIT = (byte) 0x01; //parity check on first bit
	static final byte STATUS_MASK = (byte) 0x0C;
	static final byte FIRST_BYTE_DATA_MASK = (byte)  0x03; //mask to find sensor data bits on first byte
	static final byte THIRD_BYTE_DATA_MASK = (byte) 0xFC; //mask to find sensor data bits on third byte
	static final byte READ_COMMAND = (byte) 0x20; //0010 0000


	//different register values available
	static final byte ADXRS453_REG_RATE     =  (byte) 0x00;
	static final byte ADXRS453_REG_TEM      =  (byte) 0x02;
	static final byte ADXRS453_REG_LOCST    =  (byte) 0x04;
	static final byte ADXRS453_REG_HICST    =  (byte) 0x06;
	static final byte ADXRS453_REG_QUAD     =  (byte) 0x08;
	static final byte ADXRS453_REG_FAULT    =  (byte) 0x0A;
	static final byte ADXRS453_REG_PID      =  (byte) 0x0C;
	static final byte ADXRS453_REG_SN_HIGH  =  (byte) 0x0E;
	static final byte ADXRS453_REG_SN_LOW   =  (byte) 0x10;


	//angle integration
	private Timer calibration_timer;
	private volatile double currentRate;
	private volatile double lastRate;
	private volatile double deltaTime;
	private volatile double currentTime;
	private volatile double lastTime;
	private volatile double angle;
	private volatile double accumulatedOffset;
	private double accum;
	
	//other gyro register data
	private volatile int id;
	private volatile double temp;
	private volatile int status;
	
	//calibration loop
	private volatile boolean calibrate;
	private volatile boolean firstLoop;

	private SPI spi;

	//debugging binary messages
	String binRate;
	String binMessage;

	//thread executor
	private java.util.Timer executor;
	private long period;


	public  ADXRS453Gyro()
	{

		//run at 333Hz loop
		this.period = (long)3; 

		spi = new SPI(Port.kOnboardCS0);
		spi.setClockRate(4000000); //4 MHz (rRIO max, gyro can go high)
		spi.setClockActiveHigh();
		spi.setChipSelectActiveLow();
		spi.setMSBFirst();
		
		currentRate = 0.0;
		accumulatedOffset = 0.0;

		calibration_timer = new Timer();

		lastTime =0;
		currentTime =0;
		lastRate =0;
		deltaTime=0;
		accum=0;

		calibrate = true;
		firstLoop = true;

		temp = 0;
		id = 0;

	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new GyroUpdateTask(this), 0L, this.period);
	}


	public String getMessageBin()
	{
		return binMessage;
	}

	public String getRateBin()
	{
		return binRate;
	}

	public void calibrate()
	{
		calibrate=true;
		firstLoop=true;
	}

	public void reset()
	{
		angle=0;

	}

	public double getRate() 
	{
		return currentRate;
	}

	public int getStatus()
	{
		return status;
	}
	public double getAngleDeg() 
	{
		return angle;
	}
	
	public double getAngleRadians()
	{
		return (getAngleDeg() * Math.PI) / 180.0;
	}
	
	public double getPos()
	{
		return getAngleDeg();
	}

	public double getDeltatime()
	{
		return deltaTime;
	}

	public int getID()
	{
		return id;
	}

	public double getTemp()
	{
		return temp;
	}

	public short getRegisterValue(byte registerAddress)
	{

		byte[] command = new byte[DATA_SIZE];
		byte[] data = new byte[DATA_SIZE];
		command[0] = 0;
		command[1] = 0;
		command[2] = 0;
		command[3] = 0;
		data[0] = 0;
		data[1] = 0;
		data[2] = 0;
		data[3] = 0;


		command[0] = (byte) ((0x01 << 7) | (registerAddress >> 7));
		command[1] = (byte) (registerAddress << 1);

		checkParity(command);
		spi.write(command,DATA_SIZE);
		spi.read(false, data, DATA_SIZE);

		short registerValue = 0;
		registerValue = (short) (((short)(data[1]) << 11) |
				((short)data[2] << 3) |
				((short)(data[3] >> 5)));


		return registerValue;
	}
	
	public static String getBinaryFromByte(byte[] bytes)
	{
		String temp = "";;
		for (byte b : bytes)

			temp+=Integer.toBinaryString(b & 255 | 256).substring(1) + " ";

		return temp;
	}

	////////// PRIVATE FUNCTIONS ////////////////

	private void checkParity(byte[] data)
	{
		if(BitSet.valueOf(data).cardinality() % 2 == 0)
			data[3] |= PARITY_BIT;
	}

	private double getSensorData()
	{
		byte[] command = new byte[DATA_SIZE];
		byte[] data = new byte[DATA_SIZE];
		command[0] = READ_COMMAND;
		command[1] = 0;
		command[2] = 0;
		command[3] = 0;
		data[0] = 0;
		data[1] = 0;
		data[2] = 0;
		data[3] = 0;

		checkParity(command);
		spi.write(command,DATA_SIZE);
		spi.read(false, data, DATA_SIZE);

		return sensorDataMask(data);
	}

	private double sensorDataMask(byte[] data)
	{
		//returns full binary from gyro for debugging
		binMessage = getBinaryFromByte(data);

		//00 Invalid data for sensor data response 01 Valid sensor data
		//01 Valid data
		//10 Sensor self-test data
		//11 Read/write response
		status = (short)(data[0] & STATUS_MASK) >> 2;

		//Pull out bytes 25-10 as data bytes for gyro rate
		byte[] rateByte = new byte[2];
		rateByte[0] = (byte) ((byte) ((data[1] >> 2) & 0x3F) | ((data[0] & FIRST_BYTE_DATA_MASK) << 6));
		rateByte[1] = (byte) ((byte) ((data[1] << 6) & 0xC0) | (data[2] & THIRD_BYTE_DATA_MASK) >> 2 & 0x3F); 

		//convert to 2's compo
		short value = ByteBuffer.wrap(rateByte).order(ByteOrder.BIG_ENDIAN).getShort();

		//view 16 bits in data for debugging purposes
		byte[] newB = new byte[2];
		newB[0] = (byte)((value >> 8) & 0xff);
		newB[1] = (byte)(value);
		binRate =  getBinaryFromByte(newB);

		//data has 80 LSB
		return value / 80.0;
	}

	private int GetID()
	{ 
		short id = getRegisterValue(ADXRS453_REG_PID);
		return id >> 8;
	}

	private double GetTemperature()
	{
		//TODO: reverify calc, not sure this works
		short registerValue = 0;
		short  temperature   = 0;

		registerValue = getRegisterValue(ADXRS453_REG_TEM);
		registerValue = (short) ( (registerValue >> 6) - 0x31F);
		temperature = (short) (registerValue / 5);

		return temperature;
	}


	private void update()
	{

		if(lastTime == 0)
		{
			lastTime = Timer.getFPGATimestamp();
			angle=0;
		}

		currentRate =  getSensorData();
		currentTime = Timer.getFPGATimestamp();
		deltaTime = currentTime - lastTime;

		//TODO: see if we can fix low-pass filter to stop drift
		//low-pass filter
		//remove until it can be further tested. Yields incorrect results
		//if(Math.abs(currentRate) < 2)
		//	currentRate = 0;

		angle += (currentRate * deltaTime) - accumulatedOffset * deltaTime;
		//angle += (cu

		if(calibrate)
		{
			if(firstLoop)
			{
				calibration_timer.start();
				calibration_timer.reset();
				accumulatedOffset=0;
				accum = angle;
				firstLoop = false;
			}
			if(calibration_timer.get() > 10)
			{
				accumulatedOffset = (angle - accum) / calibration_timer.get();
				firstLoop = true;
				calibrate = false;
				angle = 0;


				
				System.out.println("Accumulated Offset: " + accumulatedOffset + "\t" + "Delta Time: " + calibration_timer.get());
			}
		}

		lastRate = currentRate;
		lastTime = currentTime;

		//Get all other Gyro data here
		temp = GetTemperature();
		id = GetID();

	}

	private class GyroUpdateTask extends TimerTask {
		private ADXRS453Gyro gyro;

		private GyroUpdateTask(ADXRS453Gyro gyro) {
			if (gyro == null) {
				throw new NullPointerException("printer was null");
			}
			this.gyro = gyro;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			gyro.update();
		}
	}

}


