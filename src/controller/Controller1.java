package controller;

import model.*;

public class Controller1 {
	//private ImportCsv importCsv;
	public Controller1() {

	}

//	public String getFilePath(String newFilePath) {
//		return this.newFilePathes = newFilePath;
//	}
	
	public void insertFilePath(String newFilePath) {
		ImportCsv importCsv = new ImportCsv(newFilePath);
	}

	
}
