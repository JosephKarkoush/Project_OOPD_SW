package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable POJO version of Domain model class Student
 * 
 * @author awi
 *
 */
public class User {
	private int id;
	private String name;
	private List<Activity> activities;

	/**
	 * Constructs a Student with name. Id is automatically added in
	 * database
	 * 
	 * @param name      (String)
	 * @param birthYear (integer)
	 */
	public User(String name) {
		this(0, name);
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		activities = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addActivity(Activity activity) {
		activity.setUserId(this.id);
		activities.add(activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	@Override
	public String toString() {
		StringBuilder activitiesInfo = new StringBuilder();
		for (Activity activity : activities) {
			activitiesInfo.append(activity);
		}
		return String.format("Id: %7d %nName:     %s %nBirth year: %4d%n", id, name)
				+ String.format("BOOKS:\n%s%n", activitiesInfo);
	}
}
