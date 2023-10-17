package peding;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Import {
	String line;
	String test;
	public Import() {

		List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
		try {
			FileReader fileReader = new FileReader(
					"C:\\Users\\moham\\Downloads\\csv\\csv\\argostoliGrekland\\activity_2016019890.csv");
			Scanner sc = new Scanner(fileReader);
			sc.nextLine();

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				TrackPoint tp = new TrackPoint(line);
				trackPointList.add(tp);
				
			}
			sc.close();
			
		} catch (Exception e) { 
			System.err.println("file not found");
		}

		Activity newActivity = new Activity(trackPointList);
		Statistic statistic = new Statistic(newActivity);
		
		this.test = statistic.getMaxCadence();
		
	}
	public String test() {
		return test;
	}
	
}
