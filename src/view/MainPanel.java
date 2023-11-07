package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.Controller1;

public class MainPanel extends JPanel {
	Controller1 ctr;
	ActivityPanel activityPanel;
	DisplayPanel displayPanel;
	public MainPanel(Controller1 ctr) {
		this.ctr=ctr;
		activityPanel = new ActivityPanel(this.ctr, this);
		displayPanel = new DisplayPanel(this.ctr);
		setLayout(new GridLayout(1, 2));
		add(displayPanel);
		add(activityPanel);
		
		
	}
	
	public void updateGraphs() {
		displayPanel.updateGraphs();
	}

}
