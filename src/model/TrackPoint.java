package model;

import java.util.ArrayList;
import java.util.List;

public class TrackPoint {
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

	public TrackPoint(String inputLine) {
		atributeslist = inputLine.replace(",", ".").split(";");
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
}
