package org.team2168.replay;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Replay{
	
	private static String fileName;
	private static File logFile;
	private static FileWriter fileWriter;
	private static BufferedWriter bWriter; 
	
	public Replay() {
		fileName = "/home/lvuser/replayLogs/log." + System.currentTimeMillis() + ".raw";
		logFile = new File(fileName);
		try {
			logFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bWriter = new BufferedWriter(fileWriter);
		
		System.out.println("Created File " + fileName);
		
	}
	
	/**
	 * Writes data to the opened file
	 * @param motorData Motor data from drivetrain getMotorData
	 * @param encoderData Encoder data from drivetrain getEncoderData
	 */
	public void flushDataToFile(double[] motorData, double[] encoderData, double gryoValue) {
		String dataToFlush = "";
		
		dataToFlush += motorData[0] + ",";
		dataToFlush += motorData[5] + ":";
		
		
		dataToFlush += encoderData[0] + ",";
		dataToFlush += encoderData[1] + ";";	
		dataToFlush += gryoValue + System.getProperty("line.separator");
		
		try {
			bWriter.write(dataToFlush);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Must call this function last. Closes the file.
	 */
	public void end() {
		System.out.println("Closed stream");
		try {
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
