package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.Controller1;

public class MainPanel extends JPanel {
	Controller1 ctr;
	
	public MainPanel(Controller1 ctr) {
		this.ctr=ctr;
		ActivityPanel activityPanel = new ActivityPanel(this.ctr);
		DisplayPanel displayPanel = new DisplayPanel(this.ctr, activityPanel);
		setLayout(new GridLayout(1, 2));
		add(displayPanel);
		add(activityPanel);
		
		
	}

}
