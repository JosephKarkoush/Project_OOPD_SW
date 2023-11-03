package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import db.DbConnectionManager;
import model.Activity;
import model.Statistic;
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
					"SELECT activity_id, activity_name, user_id, activity_date  FROM \"Activity\" WHERE activity_id=" + id);
			if (!resultSet.next())
				throw new NoSuchElementException("The activity with id " + id + " doesen't exist in database");
			else {
				ResultSet trackPointCheck = dbConManagerSingleton
						.excecuteQuery("SELECT * from \"LoggData\" WHERE activity_id=" + id);
				while (trackPointCheck.next()) {
					TrackPoint trackPoint = new TrackPoint();
					trackPoint.setAltitude(trackPointCheck.getDouble(7));
					trackPoint.setCadence(trackPointCheck.getDouble(11));
					trackPoint.setDate(trackPointCheck.getString(3));
					trackPoint.setDistance(trackPointCheck.getDouble(9));
					trackPoint.setElapsedTime(trackPointCheck.getInt(12));
					trackPoint.setHeartRate(trackPointCheck.getInt(10));
					trackPoint.setLatitude(trackPointCheck.getDouble(5));
					trackPoint.setLongitude(trackPointCheck.getDouble(6));
					trackPoint.setSpeed(trackPointCheck.getDouble(8));
					trackPoint.setTime(trackPointCheck.getString(4));
					trackPointList.add(trackPoint);
				}
				Statistic statistic = new Statistic(trackPointList);
				activity = new Activity(trackPointList, statistic, resultSet.getInt(1), resultSet.getString(2),
						resultSet.getInt(3),resultSet.getString(4));
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
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT activity_id from \"Activity\"");
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
					.prepareStatement("INSERT INTO \"Activity\" (activity_name, user_id, activity_date) "
							+ "VALUES (?, ?, ?) RETURNING activity_id;");

			preparedStatement.setString(1, t.getName());
			preparedStatement.setLong(2, 1);
			preparedStatement.setString(3, t.getDate());

			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			resultSet.next();
			long generatedId = resultSet.getLong(1);
			saveTrackPoint(t.getList(), generatedId);
			saveStatistic(t.getStatistic(), generatedId);
			return new Activity(t.getList(), t.getStatistic(), generatedId, t.getName(), t.getUserId(),t.getDate());
			// ********************************************************************
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<TrackPoint> emptyList = new ArrayList<TrackPoint>();
		Statistic statistic = new Statistic();
		return new Activity(emptyList, statistic, 0, "No Name", 0, "no date");
	}

	public void saveTrackPoint(List<TrackPoint> trackPointList, long id) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"INSERT INTO \"LoggData\" (activity_id, activity_date,time,latitud,longitud,altitud,speed,distance,heart_rate,cadence,elapsed_time)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING loggdata_id;");
			for (TrackPoint trackPoint : trackPointList) {

				preparedStatement.setLong(1, id);
				preparedStatement.setString(2, trackPoint.getDate());
				preparedStatement.setString(3, trackPoint.getTime());
				preparedStatement.setDouble(4, Double.parseDouble(trackPoint.getLatitude()));
				preparedStatement.setDouble(5, Double.parseDouble(trackPoint.getLongitude()));
				preparedStatement.setDouble(6, Double.parseDouble(trackPoint.getAltitude()));
				preparedStatement.setDouble(7, Double.parseDouble(trackPoint.getSpeed()));
				preparedStatement.setDouble(8, Double.parseDouble(trackPoint.getDistance()));
				preparedStatement.setDouble(9, Double.parseDouble(trackPoint.getHeartRate()));
				preparedStatement.setDouble(10, Double.parseDouble(trackPoint.getCadence()));
				preparedStatement.setLong(11, Integer.parseInt(trackPoint.getElapsedTime()));
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveStatistic(Statistic statistic, long id) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"INSERT INTO \"refined_data\" (activity_id, total_distance,start_time,end_time,maximal_speed,minimal_speed,avg_speed,maximal_cadence,minimal_cadence,avg_cadence,maximal_heartrate,minimal_heartrate,avg_heartrate)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING refineddata_id;");

			preparedStatement.setLong(1, id);
			preparedStatement.setDouble(2, Double.parseDouble(statistic.getTotalDistance()));
			preparedStatement.setString(3, statistic.getStartTime());
			preparedStatement.setString(4, statistic.getEndTime());
			preparedStatement.setDouble(5, Double.parseDouble(statistic.getMaxSpeed()));
			preparedStatement.setDouble(6, Double.parseDouble(statistic.getMinSpeed()));
			preparedStatement.setDouble(7, Double.parseDouble(statistic.getAvgSpeed()));
			preparedStatement.setDouble(8, Double.parseDouble(statistic.getMaxCadence()));
			preparedStatement.setDouble(9, Double.parseDouble(statistic.getMinCadence()));
			preparedStatement.setDouble(10, Double.parseDouble(statistic.getAvgCadence()));
			preparedStatement.setDouble(11, Double.parseDouble(statistic.getMaxHeartRate()));
			preparedStatement.setDouble(12, Double.parseDouble(statistic.getMinHeartRate()));
			preparedStatement.setDouble(13, Double.parseDouble(statistic.getAvgHeartRate()));

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Activity t, String str) {
		long id = t.getId();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton
					.prepareStatement("update \"Activity\" set activity_name= '" + str + "' where activity_id=" + id);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void update(long id, String str) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton
					.prepareStatement("update \"Activity\" set activity_name= '" + str + "'where activity_id=" + id);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	@Override
	public void delete(Activity t) {
		long id = t.getId();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement("DELETE from \"Activity\" where activity_id=" + id);
			preparedStatement.execute();
			preparedStatement = dbConManagerSingleton.prepareStatement("DELETE from \"loggdata\" where activity_id=" + id);
			preparedStatement.execute();
			preparedStatement = dbConManagerSingleton.prepareStatement("DELETE from \"refined_data\" where activity_id=" + id);
			preparedStatement.execute();
		} catch (SQLException e) {
		}
	}
	
	@Override
	public void deleteAll() {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton.prepareStatement("DELETE from \"Activity\"");
			preparedStatement.execute();
			preparedStatement = dbConManagerSingleton.prepareStatement("DELETE from \"loggdata\"");
			preparedStatement.execute();
			preparedStatement = dbConManagerSingleton.prepareStatement("DELETE from \"refined_data\"");
			preparedStatement.execute();
		} catch (SQLException e) {
		}
	}

	@Override
	public void delete(long id) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dbConManagerSingleton
					.prepareStatement("DELETE from \"Activity\"  where activity_id=" + id);
			preparedStatement.execute();
			preparedStatement = dbConManagerSingleton
					.prepareStatement("DELETE from \"LoggData\" where activity_id=" + id);
			preparedStatement.execute();
			preparedStatement = dbConManagerSingleton
					.prepareStatement("DELETE from \"refined_data\" where activity_id=" + id);
			preparedStatement.execute();
		} catch (SQLException e) {
		}
	}

}
