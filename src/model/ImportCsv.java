package model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ActivityDao;

public class ImportCsv {
	private String line;
	private Activity newActivity;

	public ImportCsv(String filePath) {

		List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
		try {
			FileReader fileReader = new FileReader(filePath);
			Scanner sc = new Scanner(fileReader);
			sc.nextLine();

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				TrackPoint tp = new TrackPoint(line);
				trackPointList.add(tp);
			}
			System.out.println("Här är import");
			sc.close();

		} catch (Exception e) {
			System.err.println("file not found");
		}
		Statistic statistic = new Statistic(trackPointList);
		newActivity = new Activity(trackPointList, statistic);
		System.out.println(trackPointList.get(trackPointList.size() - 100).getElapsedTime());

	}

	public Activity getAct() {
		return newActivity;

	}

}
