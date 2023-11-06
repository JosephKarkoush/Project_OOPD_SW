package model;

import java.util.ArrayList;
import java.util.List;

public class TrackPoint {
	long activityId;
	String date;
	String time;
	String elapsedTime;
	String latitude;
	String longitude;
	String altitude;
	String distance;
	String heartRate;
	String speed;
	String cadence;

	String[] atributeslist = new String[9];
	
	public TrackPoint() {
		
	}

	public TrackPoint(String inputLine) {
		String line = inputLine.replaceAll(",", ".");
		atributeslist = line.split(";");
		this.date = atributeslist[0];
		this.time = atributeslist[1];
		this.elapsedTime = atributeslist[2];
		this.latitude = atributeslist[3];
		this.longitude = atributeslist[4];
		this.altitude = atributeslist[5];
		this.distance = atributeslist[6];
		this.heartRate = atributeslist[7];
		this.speed = atributeslist[8];
		this.cadence = atributeslist[9];

	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setElapsedTime(double d) {
		this.elapsedTime = String.valueOf(d);
	}

	public void setLatitude(double latitide) {
		this.latitude = String.valueOf(latitide);
	}

	public void setLongitude(double longitude) {
		this.longitude = String.valueOf(longitude);;
	}

	public void setAltitude(double altitide) {
		this.altitude = String.valueOf(altitide);

	}

	public void setDistance(double distance) {
		this.distance = String.valueOf(distance);
	}

	public void setHeartRate(int HeartRate) {
		this.heartRate = String.valueOf(HeartRate);;
	}

	public void setSpeed(double speed) {
		this.speed = String.valueOf(speed);

	}

	public void setCadence(double cadence) {
		this.cadence = String.valueOf(cadence);

	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getAltitude() {
		return altitude;

	}

	public String getDistance() {
		return distance;
	}

	public String getHeartRate() {
		return heartRate;
	}

	public String getSpeed() {
		return speed;

	}

	public String getCadence() {
		return cadence;

	}
	public long getActivityId() {
		return activityId;
		
	}
}
