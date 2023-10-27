package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ActivityDao;
import model.*;

public class Controller1 {
	ImportCsv importCsv;
	List<Activity> allActivities;
	ActivityDao dao = new ActivityDao();
	//private ImportCsv importCsv;
	public Controller1() {
	}
	
	
	public void insertFilePath(String newFilePath) {
		importCsv = new ImportCsv(newFilePath);
	}
	
	public void saveActivity(){
		dao.save(importCsv.getAct());
	}
	
	public void getAllActivities(){
		this.allActivities = dao.getAll();
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
		dao.update(importCsv.getAct(), str);
	}
	
	public void changeName(String str) {
	}

	
}
