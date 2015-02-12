package org.team2168.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.TimerTask;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.team2168.Robot;
import org.team2168.RobotMap;



public class ConsolePrinter {
	private java.util.Timer executor;
	private long period;
	
	PrintWriter log;

	public ConsolePrinter(long period) {
		this.period = period;
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new ConsolePrintTask(this), 0L, this.period);
		
		try {
			this.log = new PrintWriter("/home/lvuser/gyroLog.txt", "UTF-8");
			log.println("Time \t SensorData \t RegisterData");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void print() {
		if (RobotMap.PRINT_SD_DEBUG_DATA) {

	    	SmartDashboard.putNumber("Left Encoder Distance",Drivetrain.getInstance().getLeftPosition());
	    	SmartDashboard.putNumber("Right Encoder Distance:",Drivetrain.getInstance().getRightPosition());
	    	SmartDashboard.putBoolean("isPracticeBot", Drivetrain.getInstance().isPracticeBot());
	    	
	    	log.println(Robot.drivetrain.gyroSPI.getDeltatime() + "\t" + Robot.drivetrain.gyroSPI.getRate() + "\t" + Robot.drivetrain.gyroSPI.getAngle() );
	    	log.flush();
	    	SmartDashboard.putNumber("GYRO DeltTime:", Robot.drivetrain.gyroSPI.getDeltatime());
	    	SmartDashboard.putNumber("GYRO Rate:", Robot.drivetrain.gyroSPI.getRate());
	    	SmartDashboard.putNumber("GYRO Angle SPI:", Robot.drivetrain.gyroSPI.getAngle());
	    	SmartDashboard.putNumber("GYRO Angle Analog:", Robot.drivetrain.gyroAnalog.getAngle());
	    	SmartDashboard.putNumber("GYRO Status:", Robot.drivetrain.gyroSPI.getStatus());
	    	SmartDashboard.putNumber("GYRO ID:", Robot.drivetrain.gyroSPI.getID());
	 		SmartDashboard.putNumber("GYRO Temp:", Robot.drivetrain.gyroSPI.getTemp());
	    	
	    	SmartDashboard.putString("Gyro Message Bin:", Robot.drivetrain.gyroSPI.getMessageBin());
	    	SmartDashboard.putString("Gyro Rate Bin:", Robot.drivetrain.gyroSPI.getRateBin());
	    	
	    	
	    	SmartDashboard.putNumber("Accel X:", Robot.accel.getX());
	    	SmartDashboard.putNumber("Accel Y:", Robot.accel.getY());
	    	SmartDashboard.putNumber("Accel Z:", Robot.accel.getZ());
	    	
	    	SmartDashboard.putNumber("DTRight1MotorVoltage", Robot.drivetrain.getRight1MotorVoltage());
	    	SmartDashboard.putNumber("DTRight2MotorVoltage", Robot.drivetrain.getRight2MotorVoltage());
	    	SmartDashboard.putNumber("DTRight3MotorVoltage", Robot.drivetrain.getRight3MotorVoltage());
	    	
	    	SmartDashboard.putNumber("DTLeft1MotorVoltage", Robot.drivetrain.getLeft1MotorVoltage());
	    	SmartDashboard.putNumber("DTLeft2MotorVoltage", Robot.drivetrain.getLeft2MotorVoltage());
	    	SmartDashboard.putNumber("DTLeft3MotorVoltage", Robot.drivetrain.getLeft3MotorVoltage());

		}
	}

	private class ConsolePrintTask extends TimerTask {
		private ConsolePrinter console;

		private ConsolePrintTask(ConsolePrinter printer) {
			if (printer == null) {
				throw new NullPointerException("printer was null");
			}
			this.console = printer;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			console.print();
		}
	}
}
