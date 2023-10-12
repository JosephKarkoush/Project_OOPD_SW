package peding;

import java.util.ArrayList;
import java.util.List;

public class Statistic {
	String totalDistance;
	String startTime;
	String endTime;
	String minSpeed;
	String maxSpeed;
	String avgSpeed;
	String minCandece;
	String maxCandece;
	String avgCadence;
	String minHeartRate;
	String maxHeartRate;
	String avgHeartRate;
	Activity activity;
	double sumSpeed = 0;
	double minSpeedValue = 1000;
	double sumHeartRate = 0;
	double minHeartRateValue = 1000;
	double sumCadence = 0;
	double minCadenceValue = 10000;


	public Statistic(Activity activity) {
		this.activity = activity;
		getAvgCadence();
		getAvgHeartRate();
		getAvgSpeed();
	}

	public String getAvgSpeed() {
		for (int i = 0; i < activity.trackPointList.size(); i++) {
			// räknar summan av alla hastighet
			double speedValue = Double.parseDouble(activity.trackPointList.get(i).getSpeed());
			sumSpeed += speedValue;
		}
		sumSpeed = sumSpeed / activity.trackPointList.size();
		this.avgSpeed = Double.toString(sumSpeed);
		return avgSpeed;
	}

	public String getMinSpeed() {
		for (int i = 0; i < activity.trackPointList.size(); i++) {
			if (Double.parseDouble(activity.trackPointList.get(i).getSpeed()) < minSpeedValue) {
				minSpeedValue = Double.parseDouble(activity.trackPointList.get(i).getSpeed());
			}

		}
		this.minSpeed = Double.toString(minSpeedValue);
		return minSpeed;

	}

	public String getAvgHeartRate() {
		for (int i = 0; i < activity.trackPointList.size(); i++) {
			// räknar ut summan av alla hjärtslag
			double heartRateValue = Double.parseDouble(activity.trackPointList.get(i).getHeartRate());
			sumHeartRate += heartRateValue;
		}
		sumHeartRate = sumHeartRate / activity.trackPointList.size();
		this.avgHeartRate = Double.toString(sumHeartRate);
		return avgHeartRate;
	}
	
	public String getMinHeartRate() {
		for (int i = 0; i < activity.trackPointList.size(); i++) {
			if (Double.parseDouble(activity.trackPointList.get(i).getHeartRate()) < minHeartRateValue) {
				minHeartRateValue = Double.parseDouble(activity.trackPointList.get(i).getHeartRate());
			}

		}
		this.minHeartRate = Double.toString(minHeartRateValue);
		return minHeartRate;

	}

	public String getAvgCadence() {
		for (int i = 0; i < activity.trackPointList.size(); i++) {
			// räkna ut summan av candence
			double cadenceValue = Double.parseDouble(activity.trackPointList.get(i).getCadence());
			sumCadence += cadenceValue;
		}
		sumCadence = sumCadence / activity.trackPointList.size();
		this.avgCadence = Double.toString(sumCadence);
		return avgHeartRate;
	}
	
	public String getMinCadence() {
		for (int i = 0; i < activity.trackPointList.size(); i++) {
			if (Double.parseDouble(activity.trackPointList.get(i).getCadence()) < minCadenceValue) {
				minCadenceValue = Double.parseDouble(activity.trackPointList.get(i).getCadence());
			}

		}
		this.minCandece = Double.toString(minCadenceValue);
		return minCandece;

	}

}
