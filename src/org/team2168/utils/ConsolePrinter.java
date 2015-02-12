package org.team2168.utils;

import java.util.TimerTask;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConsolePrinter {
	private java.util.Timer executor;
	private long period;

	public ConsolePrinter(long period) {
		this.period = period;
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new ConsolePrintTask(this), 0L, this.period);
	}

	public void print() {
		if (RobotMap.PRINT_SD_DEBUG_DATA) {
			SmartDashboard.putNumber("Left Encoder Distance",Drivetrain.getInstance().getLeftPosition());
			SmartDashboard.putNumber("Right Encoder Distance:",Drivetrain.getInstance().getRightPosition());
			SmartDashboard.putBoolean("isPracticeBot", Drivetrain.getInstance().isPracticeBot());

			SmartDashboard.putNumber("Accel X:", Robot.accel.getX());
			SmartDashboard.putNumber("Accel Y:", Robot.accel.getY());
			SmartDashboard.putNumber("Accel Z:", Robot.accel.getZ());
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
