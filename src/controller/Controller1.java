package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import dao.ActivityDao;
import model.*;

public class Controller1 {
	ImportCsv importCsv;
	Activity activity;
	List<Activity> allActivities;
	ActivityDao dao = new ActivityDao();

	// private ImportCsv importCsv;
	public Controller1() {
	}

	public void insertFilePath(String newFilePath) {
		importCsv = new ImportCsv(newFilePath);
	}

	public void saveActivity() {
		this.activity = dao.save(importCsv.getAct());
	}

	public List<Activity> getAllActivities() {
		this.allActivities = dao.getAll();
		return allActivities;
	}

	public String[] getAllActivityNames() {
		getAllActivities();
		String[] activityNames = new String[allActivities.size()];
		for (int i = 0; i < allActivities.size(); i++) {
			activityNames[i] = allActivities.get(i).getName();
		}
		return activityNames;

	}

	public void setName(String str) {
		dao.update(this.activity, str);
	}

	public void setCurrentActivity(String str) {
		System.out.println(str);
		for (Activity ac : allActivities) {
			if (str.equals(ac.getName())) {
				this.activity = ac;
			}
		}
	}

	public Activity getCurrentActivity() {
		return this.activity;
	}

	public void deleteActivity() {
		dao.delete(this.activity);

	}

	public void deleteAllActivities() {
		dao.deleteAll();
	}
	
	public User getUser() {
		return dao.getInfo();
	}

}
