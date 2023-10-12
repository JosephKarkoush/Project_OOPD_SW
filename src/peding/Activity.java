package peding;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	String name;
	List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
	
	public Activity(List<TrackPoint> trackPointList) {
		this.trackPointList = trackPointList;
		
	}
	
}

