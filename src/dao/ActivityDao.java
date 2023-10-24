package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import db.DbConnectionManager;
import model.Activity;
import model.TrackPoint;

public class ActivityDao implements Dao<Activity> {
	DbConnectionManager dbConManagerSingleton = null;

	public ActivityDao() {
		dbConManagerSingleton = DbConnectionManager.getInstance();
	}

	@Override
	public Activity get(long id) {
		List<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
		Activity activity = null;

		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery(
					"SELECT activity_id, activity_name, user_id, activity_date FROM activity WHERE activity_id=" + id);
			if (!resultSet.next())
				throw new NoSuchElementException("The activity with id " + id + " doesen't exist in database");
			else {
				ResultSet trackPointCheck = dbConManagerSingleton
						.excecuteQuery("SELECT * form Activty WHERE activity_id=" + id);
				while (trackPointCheck.next()) {
					TrackPoint trackPoint = new TrackPoint();
					trackPoint.setAltitude(trackPointCheck.getDouble("altitude"));
					trackPoint.setCadence(trackPointCheck.getDouble("cadencce"));
					trackPoint.setDate(trackPointCheck.getString("date"));
					trackPoint.setDistance(trackPointCheck.getDouble("distance"));
					trackPoint.setElapsedTime(trackPointCheck.getInt("elapsed_time"));
					trackPoint.setHeartRate(trackPointCheck.getInt("hearrate"));
					trackPoint.setLatitude(trackPointCheck.getDouble("latitude"));
					trackPoint.setLongitude(trackPointCheck.getDouble("longitude"));
					trackPoint.setSpeed(trackPointCheck.getDouble("speed"));
					trackPoint.setTime(trackPointCheck.getString("time"));
					trackPointList.add(trackPoint);
				}
				activity = new Activity(trackPointList, resultSet.getInt(1), resultSet.getString(2),
						resultSet.getInt(3));
			}

			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return activity;
	}

	@Override
	public List<Activity> getAll() {
		List<Activity> list = new ArrayList<>();
		List<Integer> activityID = new ArrayList<>();

		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT activity_id from Activty");
			while (resultSet.next()) {
				activityID.add(resultSet.getInt(1));
			}
			for (int i = 0; i < activityID.size(); i++) {
				Activity ac = get(activityID.get(i));
				list.add(ac);
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Activity save(Activity t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (t.getId() != 0)
			return t;
		try {
			// *******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton
					.prepareStatement("INSERT INTO activity (activity_name, user_id) " + "VALUES (?, ?) RETURNING id;");

			preparedStatement.setString(1, t.getName());
			preparedStatement.setLong(2, t.getUserId());

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			resultSet.next();
			long generatedId = resultSet.getLong(1);
			return new Activity(t.getList(), generatedId, t.getName(), t.getUserId());
			// ********************************************************************
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<TrackPoint> emptyList = new ArrayList<TrackPoint>();
		return new Activity(emptyList, 0, "No Name", 0);
	}

	public void saveTrackPoint(List<TrackPoint> trackPointList) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"INSERT INTO LoggData (activity_id, activity_date,time,latitud,longitud,altitud,speed,distance,heart_rate,cadence,elapsed_time)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;");
			for (TrackPoint trackPoint : trackPointList) {

				preparedStatement.setLong(1, trackPoint.getActivityId());
				preparedStatement.setString(2, trackPoint.getDate());
				preparedStatement.setString(3, trackPoint.getTime());
				preparedStatement.setDouble(4, Double.parseDouble(trackPoint.getLatitude()));
				preparedStatement.setDouble(5, Double.parseDouble(trackPoint.getLongitude()));
				preparedStatement.setDouble(6, Double.parseDouble(trackPoint.getAltitude()));
				preparedStatement.setDouble(7, Double.parseDouble(trackPoint.getSpeed()));
				preparedStatement.setDouble(8, Double.parseDouble(trackPoint.getDistance()));
				preparedStatement.setLong(9, Integer.parseInt(trackPoint.getHeartRate()));
				preparedStatement.setDouble(10, Double.parseDouble(trackPoint.getCadence()));
				preparedStatement.setLong(11, Integer.parseInt(trackPoint.getElapsedTime()));

				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Activity update(Activity t, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity update(Activity t) {
		PreparedStatement preparedStatement = null;
		if (t.getId() == 0) // Activity has not been saved before and therefore can't be updated.
			return t;
		try {
			// *******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton
					.prepareStatement("UPDATE activity SET activity_name=?, student_id=? WHERE id=? RETURNING id;");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setLong(2, t.getUserId());
			preparedStatement.setLong(3, t.getId());
			boolean affectedRows = preparedStatement.execute();
			if (!affectedRows) {
				throw new SQLException("No update was performed on activity with 'id' " + t.getId());
			}

			// ********************************************************************
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Activity delete(Activity t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
