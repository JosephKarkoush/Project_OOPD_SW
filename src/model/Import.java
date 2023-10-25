package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ActivityDao;

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
		Statistic statistic = new Statistic(trackPointList);
		Activity newActivity = new Activity(trackPointList,statistic);
		ActivityDao dao = new ActivityDao();
		dao.update(3,"kalleanka");
		
		
		this.test = newActivity.statistic.getMaxCadence();
		
	}
	public String test() {
		return test;
	}
	
}
