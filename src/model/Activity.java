package model;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	String name;
	private long id;
	private long userId;
	List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
	
	public Activity(List<TrackPoint> trackPointList) {
		this.trackPointList = trackPointList;
	}
	
	public Activity(List<TrackPoint> trackPointList, long id, String name, long userId) {
		this.trackPointList = trackPointList;
		this.id = id;
		this.name = name;
		this.userId = userId;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setTitle(String title) {
		this.name = title;
	}
	public void setUserId(long studentId) {
		this.userId = studentId;
	}
	@Override
	public String toString() {
		return String.format("ID: %7d %nNAME:     %s %n", id, name);
	}

	public long getUserId() {
		return this.userId;
	}
	
}

