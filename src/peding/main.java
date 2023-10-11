package peding;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
		FileReader fileReader = new FileReader("C:\\Users\\moham\\Downloads\\csv\\csv\\argostoliGrekland\\activity_2001397372.csv");
		Scanner sc = new Scanner(fileReader);
		String line;
		sc.nextLine();
		
	
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			TrackPoint tp = new TrackPoint(line);
			trackPointList.add(tp);
		}
		
		System.out.println(trackPointList.get(1).getAltitude());
		
		
	}
	
}
