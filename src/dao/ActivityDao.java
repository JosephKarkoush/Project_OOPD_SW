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
			ResultSet resultSet = dbConManagerSingleton
					.excecuteQuery("SELECT activity_id, activity_name, user_id, activity_date FROM Activity WHERE activity_id=" + id);
			if (!resultSet.next())
				throw new NoSuchElementException("The activity with id " + id + " doesen't exist in database");
			else {
				ResultSet trackPointCheck = dbConManagerSingleton.excecuteQuery("SELECT * form Activty WHERE activity_id=" + id);
				while(trackPointCheck.next()) {
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
				activity = new Activity(trackPointList, resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
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

		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT * form Activty");
			while (resultSet.next()) {
				list.add(new Activity(trackPointList,resultSet.getInt(1), resultSet.getString(2).trim(), resultSet.getInt(3)));
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
					.prepareStatement("INSERT INTO activitys (activity_name, user_id) " + "VALUES (?, ?) RETURNING id;");
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
		return new Activity(emptyList,0, "No Name", 0);
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
					.prepareStatement("UPDATE activitys SET title=?, student_id=? WHERE id=? RETURNING id;");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setLong(2, t.getUserId());
			preparedStatement.setLong(3, t.getId());
			boolean affectedRows = preparedStatement.execute();
			if( !affectedRows) {
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
