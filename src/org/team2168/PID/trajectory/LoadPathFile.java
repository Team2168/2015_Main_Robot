package org.team2168.PID.trajectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadPathFile {

	public static void main(String[] args)
	{
		LoadPathFile test = new LoadPathFile();
		
	
		Path newPath = test.readFile("C:\\Users\\2168Admin\\Desktop\\straightPath.txt");
		
		newPath.toString();
	}
	
	
  public static Path readFile(String path) {
    
	  File file = new File(path);

	  ArrayList<String[]> FileList = readFile(file);
	  
    
    String name = FileList.get(0)[0];
    int num_elements = Integer.parseInt(FileList.get(1)[0]);
    
    Trajectory left = new Trajectory(num_elements);
    for (int i = 3; i < num_elements+2; ++i) 
    
    {
      Trajectory.Segment segment = new Trajectory.Segment();
      String[] line = FileList.get(i);
      
      segment.pos = Double.parseDouble(line[0]);
      segment.vel = Double.parseDouble(line[1]);
      segment.acc = Double.parseDouble(line[2]);
      segment.jerk = Double.parseDouble(line[3]);
      segment.heading = Double.parseDouble(line[4]);
      segment.dt = Double.parseDouble(line[5]);
      segment.x = Double.parseDouble(line[6]);
      segment.y = Double.parseDouble(line[7]);
      
      left.setSegment(i, segment);
    }
    Trajectory right = new Trajectory(num_elements);
    for (int i = num_elements+2; i < FileList.size(); ++i) 
    {
        Trajectory.Segment segment = new Trajectory.Segment();
        String[] line = FileList.get(i);
        
        segment.pos = Double.parseDouble(line[0]);
        segment.vel = Double.parseDouble(line[1]);
        segment.acc = Double.parseDouble(line[2]);
        segment.jerk = Double.parseDouble(line[3]);
        segment.heading = Double.parseDouble(line[4]);
        segment.dt = Double.parseDouble(line[5]);
        segment.x = Double.parseDouble(line[6]);
        segment.y = Double.parseDouble(line[7]);
      
      right.setSegment(i, segment);
    }
    
    System.out.println("...finished parsing path from string.");
    return new Path(name, new Trajectory.Pair(left, right));
  }
  
	
	
	private static ArrayList<String[]> readFile(File fileLocation)
	{
		ArrayList<String[]> FileList = new ArrayList<String[]>();
		
		
		String delimiter = " ";
		Scanner sc = null;
		try {
			sc = new Scanner(fileLocation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		
		while (sc.hasNextLine()) 
		{
		    String line = sc.nextLine();
		    System.out.println(line);
		    FileList.add(line.split(delimiter));
		}
		
		return FileList;
		
	}
  
  
}
