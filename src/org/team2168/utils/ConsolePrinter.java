package org.team2168.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.TimerTask;

import org.team2168.OI;
import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
			this.log = new PrintWriter("/home/lvuser/Log.txt", "UTF-8");
			log.println("Time \t VoltageL1 \t VoltageL2 \t VoltageL3 \t VoltageR1 \t VoltageR2 \t VoltageR3 \t CurrentL1 \t CurrentL2 \t CurrentL3 \t CurrentR1 \t CurrentR2 \t CurrentR3 \t Gyrot SPI Gyro Angle \t SPI Gyro Rate \t Analog Gyro Angle \t Analog Gyro Rate \t Left Encoder Position \t Left Encoder Rate \t Right Encoder Position \t Right Encoder Rate \t Lift Voltage \t Lift Current \t Lift Position \t Lift Rate");
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
			SmartDashboard.putBoolean("isPracticeBot", Robot.isPracticeRobot());
	    	
			SmartDashboard.putNumber("GYRO DeltTime:", Robot.drivetrain.gyroSPI.getDeltatime());
			SmartDashboard.putNumber("GYRO Rate:", Robot.drivetrain.gyroSPI.getRate());
			SmartDashboard.putNumber("GYRO Angle SPI:", Robot.drivetrain.gyroSPI.getAngle());
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

			SmartDashboard.putNumber("Battery Voltage", Robot.pdp.getBatteryVoltage());


			SmartDashboard.putNumber("DTRight1MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP));
			SmartDashboard.putNumber("DTRight2MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP));
			SmartDashboard.putNumber("DTRight3MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP));

			SmartDashboard.putNumber("DTLeft1MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP));
			SmartDashboard.putNumber("DTLeft2MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP));
			SmartDashboard.putNumber("DTLeft3MotorCurrent", Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP));

			SmartDashboard.putNumber("Lift Encoder Position", Robot.lift.getPosition());
			SmartDashboard.putNumber("Lift Encoder Rage", Robot.lift.getRate());

			SmartDashboard.putNumber("Lift Motor Current", Robot.pdp.getChannelCurrent(RobotMap.LIFT_MOTOR_PDP));
			SmartDashboard.putNumber("Lift Motor Voltage", Robot.lift.getMotorVoltage());


			SmartDashboard.putNumber("Tote IR (V)", Robot.intake.getAveragedRawToteDistance());

			SmartDashboard.putNumber("Left Stick Raw Value", OI.getInstance().driverJoystick.getLeftStickRaw_Y());
			SmartDashboard.putNumber("Right Stick Raw Value", OI.getInstance().driverJoystick.getRightStickRaw_Y());

			SmartDashboard.putNumber("Left Trigger Raw Value", OI.getInstance().driverJoystick.getLeftTriggerAxisRaw());
			SmartDashboard.putNumber("Right Trigger Raw Value", OI.getInstance().driverJoystick.getRightTriggerAxisRaw());

			SmartDashboard.putNumber("Operator Left Stick Raw Value", OI.getInstance().operatorJoystick.getLeftStickRaw_Y());
			SmartDashboard.putNumber("Operator Right Stick Raw Value", OI.getInstance().operatorJoystick.getRightStickRaw_Y());

			SmartDashboard.putNumber("Operator Left Trigger Raw Value", OI.getInstance().operatorJoystick.getLeftTriggerAxisRaw());
			SmartDashboard.putNumber("Operator Right Trigger Raw Value", OI.getInstance().operatorJoystick.getRightTriggerAxisRaw());
			
			SmartDashboard.putNumber("Angle", Robot.drivetrain.gyroSPI.getAngle());
			SmartDashboard.putNumber("Accumulated Rate", Robot.drivetrain.gyroSPI.accumulatedRate);
			SmartDashboard.putNumber("Current Rate", Robot.drivetrain.gyroSPI.currentRate);
			SmartDashboard.putNumber("Current Time", Robot.drivetrain.gyroSPI.timeElapsed);
			SmartDashboard.putNumber("Drift Rate", Robot.drivetrain.gyroSPI.driftRate);

			SmartDashboard.putBoolean("Lift Lowered", Robot.lift.isFullyLowered());
			SmartDashboard.putBoolean("Lift Raised", Robot.lift.isFullyRaised());
			
			
//				    	//file log
//				    	log.println(Timer.getFPGATimestamp() + "\t" +
//				    			Robot.drivetrain.getLeft1MotorVoltage() + "\t" +
//				    			Robot.drivetrain.getLeft2MotorVoltage() + "\t" +
//				    			Robot.drivetrain.getLeft3MotorVoltage() + "\t" +
//			
//								Robot.drivetrain.getRight1MotorVoltage() + "\t" +
//								Robot.drivetrain.getRight2MotorVoltage() + "\t" +
//								Robot.drivetrain.getRight3MotorVoltage() + "\t" +
//			
//								Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_1_PDP) + "\t" +
//								Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_2_PDP) + "\t" +
//								Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_LEFT_MOTOR_3_PDP) + "\t" +
//			
//								Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_1_PDP) + "\t" +
//								Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_2_PDP) + "\t" +
//								Robot.pdp.getChannelCurrent(RobotMap.DRIVETRAIN_RIGHT_MOTOR_3_PDP) + "\t" +
//			
//								Robot.drivetrain.gyroAnalog.getPos() + "\t" +
//								Robot.drivetrain.gyroAnalog.getRate() + "\t" +
//			
//								Robot.drivetrain.gyroSPI.getPos() + "\t" +
//								Robot.drivetrain.gyroSPI.getRate() + "\t" +
//			
//								Robot.drivetrain.drivetrainLeftEncoder.getPos() + "\t" +
//								Robot.drivetrain.drivetrainLeftEncoder.getRate() + "\t" +
//			
//								Robot.drivetrain.drivetrainRightEncoder.getPos() + "\t" +
//								Robot.drivetrain.drivetrainRightEncoder.getRate() + "\t" +
//								
//					Robot.lift.getMotorVoltage() + "\t" +
//				Robot.pdp.getChannelCurrent(RobotMap.LIFT_MOTOR_PDP) + "\t" +
//				Robot.lift.getPosition() + "\t" +
//				Robot.lift.liftEncoder.getRawRate()
//			
//				    			);
//				    	log.flush();


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
