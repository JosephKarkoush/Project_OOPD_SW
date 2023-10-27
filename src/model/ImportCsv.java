package model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ActivityDao;

public class ImportCsv {
	String line;

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
		Activity newActivity = new Activity(trackPointList, statistic);
		ActivityDao dao = new ActivityDao();
		dao.save(newActivity);

	}

}
