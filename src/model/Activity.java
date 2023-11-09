package model;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	String name;
	private long id;
	private long userId;;
	private String date;
	private Statistic statistic;
	private List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();

	public Activity(List<TrackPoint> trackPointList, Statistic statistic) {
		this.statistic = statistic;
		this.trackPointList = trackPointList;
		this.date = trackPointList.get(0).getDate();
		this.statistic = new Statistic(trackPointList);
	}

	public Activity(List<TrackPoint> trackPointList, Statistic statistic, long id, String name, long userId,
			String date) {
		this.trackPointList = trackPointList;
		this.id = id;
		this.name = name;
		this.date = date;
		this.userId = userId;
		this.statistic = new Statistic(trackPointList);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<TrackPoint> getList() {
		return trackPointList;

	}

	public String getDate() {
		return this.date;
	}

	public Statistic getStatistic() {
		return this.statistic;
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
