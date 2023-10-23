package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Activity;

public class ActivityPanel extends JPanel {
	DataPanel dataPanel = new DataPanel();
	
	JPanel secondButtonPanel = new JPanel();
	JButton changeName = new JButton("Change name");
	
	
	private String[] activities = {"List"};
	JComboBox comboBox = new JComboBox(activities);
	

	public ActivityPanel() {
		setLayout(new BorderLayout());
		secondButtonPanel.setLayout(new GridLayout(1,2));
		secondButtonPanel.add(changeName);
		secondButtonPanel.add(comboBox);
		
		add(secondButtonPanel, BorderLayout.SOUTH);
		add(dataPanel, BorderLayout.CENTER);

	}
	public void settActivites(List<Activity> activities) {
		
		
	
	}
	
}
