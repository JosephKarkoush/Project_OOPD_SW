package model;

import java.util.ArrayList;
import java.util.List;

public class Statistic {
	private String totalDistance;
	private String startTime;
	private String endTime;
	private String minSpeed;
	private String maxSpeed;
	private String avgSpeed;
	private String minCandece;
	private String maxCandece;
	private String avgCadence;
	private String minHeartRate;
	private String maxHeartRate;
	private String avgHeartRate;
	private double sumSpeed = 0;
	private double minSpeedValue = 1000;
	private double maxSpeedValue = 0;

	private double sumHeartRate = 0;
	private double minHeartRateValue = 1000;
	private double maxHeartRateValue = 0;

	private double sumCadence = 0;
	private double minCadenceValue = 10000;
	private double maxCadenceValue = 0;
	private List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();

	public Statistic(List<TrackPoint> trackPointList) {
		this.trackPointList = trackPointList;
		getAvgCadence();
		getAvgHeartRate();
		getAvgSpeed();
	}

	public Statistic() {

	}

	public String getAvgSpeed() {
		for (int i = 0; i < trackPointList.size(); i++) {
			double speedValue = Double.parseDouble(trackPointList.get(i).getSpeed());
			sumSpeed += speedValue;
		}
		sumSpeed = sumSpeed / trackPointList.size();
		this.avgSpeed = Double.toString(sumSpeed);
		return avgSpeed;
	}

	public String getMinSpeed() {
		for (int i = 0; i < trackPointList.size(); i++) {
			if (Double.parseDouble(trackPointList.get(i).getSpeed()) < minSpeedValue) {
				minSpeedValue = Double.parseDouble(trackPointList.get(i).getSpeed());
			}
		}
		this.minSpeed = Double.toString(minSpeedValue);
		return minSpeed;

	}

	public String getMaxSpeed() {
		for (int i = 0; i < trackPointList.size(); i++) {
			if (Double.parseDouble(trackPointList.get(i).getSpeed()) > maxSpeedValue) {
				maxSpeedValue = Double.parseDouble(trackPointList.get(i).getSpeed());
			}
		}
		this.maxSpeed = Double.toString(maxSpeedValue);
		return maxSpeed;

	}

	public String getAvgHeartRate() {
		for (int i = 0; i < trackPointList.size(); i++) {
			double heartRateValue = Double.parseDouble(trackPointList.get(i).getHeartRate());
			sumHeartRate += heartRateValue;
		}
		sumHeartRate = sumHeartRate / trackPointList.size();
		this.avgHeartRate = Double.toString(sumHeartRate);
		return avgHeartRate;
	}

	public String getMinHeartRate() {
		for (int i = 0; i < trackPointList.size(); i++) {
			if (Double.parseDouble(trackPointList.get(i).getHeartRate()) < minHeartRateValue) {
				minHeartRateValue = Double.parseDouble(trackPointList.get(i).getHeartRate());
			}

		}
		this.minHeartRate = Double.toString(minHeartRateValue);
		return minHeartRate;

	}

	public String getMaxHeartRate() {
		for (int i = 0; i < trackPointList.size(); i++) {
			if (Double.parseDouble(trackPointList.get(i).getHeartRate()) > maxHeartRateValue) {
				maxHeartRateValue = Double.parseDouble(trackPointList.get(i).getHeartRate());
			}

		}
		this.maxHeartRate = Double.toString(maxHeartRateValue);
		return maxHeartRate;

	}

	public String getAvgCadence() {
		for (int i = 0; i < trackPointList.size(); i++) {
			double cadenceValue = Double.parseDouble(trackPointList.get(i).getCadence());
			sumCadence += cadenceValue;
		}
		sumCadence = sumCadence / trackPointList.size();
		this.avgCadence = Double.toString(sumCadence);
		return avgHeartRate;
	}

	public String getMinCadence() {
		for (int i = 0; i < trackPointList.size(); i++) {
			if (Double.parseDouble(trackPointList.get(i).getCadence()) < minCadenceValue) {
				minCadenceValue = Double.parseDouble(trackPointList.get(i).getCadence());
			}

		}
		this.minCandece = Double.toString(minCadenceValue);
		return minCandece;

	}

	public String getMaxCadence() {
		for (int i = 0; i < trackPointList.size(); i++) {
			if (Double.parseDouble(trackPointList.get(i).getCadence()) > maxCadenceValue) {
				maxCadenceValue = Double.parseDouble(trackPointList.get(i).getCadence());
			}

		}
		this.maxCandece = Double.toString(maxCadenceValue);
		return maxCandece;

	}

	public String getTotalDistance() {
		int listSize = trackPointList.size() - 1;

		return trackPointList.get(listSize).getDistance();
	}

	public String getStartTime() {
		return trackPointList.get(0).getTime();

	}

	public String getEndTime() {
		int listSize = trackPointList.size() - 1;

		return trackPointList.get(listSize).getTime();
	}

}
