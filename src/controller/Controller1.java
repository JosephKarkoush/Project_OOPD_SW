package controller;

import model.*;

public class Controller1 {
	private String newFilePathes;
	//private ImportCsv importCsv;
	public Controller1() {

	}

//	public String getFilePath(String newFilePath) {
//		return this.newFilePathes = newFilePath;
//	}
	
	public void insertFilePath(String newFilePath) {
		this.newFilePathes = newFilePath;
		ImportCsv importCsv = new ImportCsv(newFilePath);
	}

	
}
