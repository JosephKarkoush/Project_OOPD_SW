package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	ActivityPanel activityPanel = new ActivityPanel();
	DisplayPanel displayPanel = new DisplayPanel();
	
	public MainPanel() {
		setLayout(new GridLayout(1, 2));
		add(displayPanel);
		add(activityPanel);
		
		
	}

}
