package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import db.DbConnectionManager;
import model.Activity;
import model.User;

/**
 * DAO for the persistent handling of a User object. It manages all CRUD
 * operations and conversion between the object world user and the relational
 * version user (DB version). Due to the use of a DbConnectionManager the DAO
 * doesen't need to use, or even know, about any of lower level connections to
 * the Database. It 'speaks' in Objects with the object world (Domain model)and
 * in relational sql strings, tables, columns and result sets with the database.
 * 
 * @author awi
 * @version 2018, update 2020, update 2023
 */
public class UserDao implements Dao<User> {

	DbConnectionManager dbConManagerSingleton = null;

	public UserDao() {
		dbConManagerSingleton = DbConnectionManager.getInstance();
	}

	@Override

	public User get(long id) throws NoSuchElementException {
		User user = null;
		try {
			ResultSet resultSet = dbConManagerSingleton
					.excecuteQuery("SELECT id, name, birth_year FROM users WHERE id=" + id);
			if (!resultSet.next())
				throw new NoSuchElementException("The user with id " + id + " doesen't exist in database");
			else {
				user = new User(resultSet.getInt(1), resultSet.getString(2));
				ResultSet activityResultSet = dbConManagerSingleton
						.excecuteQuery("SELECT id, title FROM activitys WHERE user_id=" + id);
				while (activityResultSet.next()) {
					Activity activity = new Activity(activityResultSet.getInt(1), activityResultSet.getString(2).trim(),
							id);
					user.addActivity(activity);
				}
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> getAll() {

		List<User> list = new ArrayList<>();

		try {
			ResultSet userResultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM users");
			while (userResultSet.next()) {
				User user = new User(userResultSet.getInt(1), userResultSet.getString(2).trim());
				ResultSet activityResultSet = dbConManagerSingleton.excecuteQuery(
						"SELECT id, title, user_id FROM activitys WHERE user_id=" + userResultSet.getInt(1));
				while (activityResultSet.next()) {
					Activity activity = new Activity(activityResultSet.getInt(1), activityResultSet.getString(2).trim(),
							userResultSet.getInt(1));
					user.addActivity(activity);
				}
				list.add(user);
			}

			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User save(User t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (t.getId() != 0)
			return t;
		try {
			// *******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton
					.prepareStatement("INSERT INTO users (name, birth_year) " + "VALUES (?, ?) RETURNING id;");
			preparedStatement.setInt(2, t.getId());
			preparedStatement.setString(1, t.getName());
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			resultSet.next();
			int generatedId = resultSet.getInt(1);
			User newUser = new User(generatedId, t.getName());
			// ********************************************************************
			if (t.getActivities() != null) {
				ActivityDao activityDao = new ActivityDao();
				for (Activity activity : t.getActivities()) {
					activity.setUserId(generatedId);
					newUser.addActivity(activityDao.save(activity));
				}
			}
			return newUser;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new User(0, "No Name");
	}

	/**
	 * This method uses a temporary User set with the desired changed values. It
	 * must have a 'id' that corresponds to a existing record in the database. The
	 * String array provides the attribute names of User class that is subject to
	 * change. Do not use the column names from the table, this will increase
	 * coupling and is bad. The method should make the coupling between the Users
	 * attribute and corresponding column name in table users, it should be the same
	 * but there's no guarantee. In this way the calling object need not to know
	 * anything about the construction of the database table, and that is a good
	 * thing.
	 * 
	 * @param t      - an instance of a User with new values on attributes but an
	 *               'id' identical to an existing user in the DB
	 * @param params - an array with the attribute names of the user that is subject
	 *               to change with this update.
	 */
	@Override
	public User update(User t, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Simplified update method that updates everything in the database-record that
	 * corresponds to the set 'id' in argument object User. Basically it´s a
	 * "assign" operation NOTE: This update method does NOT updates removed
	 * activitys - its up to You!
	 * 
	 * @param t A User object with id and other data
	 * @return If update is successful, the parameter object is returned otherwise a
	 *         new user object with 'id' set to 0 is returned
	 */
	@Override
	public User update(User t) {
		PreparedStatement preparedStatement = null;
		try {
			// *******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton
					.prepareStatement("UPDATE users SET name = ?, birth_year = ? WHERE id = ? RETURNING id;");
			preparedStatement.setInt(3, t.getId());
			preparedStatement.setString(1, t.getName());
			boolean hasAaffectedRows = preparedStatement.execute();
			if (!hasAaffectedRows)
				throw new SQLException("No update was performed on User with 'id': " + t.getId());
			// ********************************************************************
			if (t.getActivities() != null) {
				ActivityDao activityDao = new ActivityDao();
				List<Activity> listOfActivities = t.getActivities();
				for (int i = 0; i < listOfActivities.size(); i++) {
					Activity activity = null;
					if (listOfActivities.get(i).getId() > 0) // Has valid 'id' -> update
						activity = activityDao.update(listOfActivities.get(i));
					else
						activity = activityDao.save(listOfActivities.get(i)); // Has not valid 'id' -> save
					listOfActivities.set(i, activity);
				}
			}
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new User(0, "No Name"); // Returning a user with 'id' 0 if Exception occurs.
	}

	@Override
	public User delete(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
