package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.Controller1;
import model.Activity;

public class ActivityPanel extends JPanel {
	Controller1 ctr; 
	DataPanel dataPanel = new DataPanel(ctr);
	
	JPanel secondButtonPanel = new JPanel();
	JButton changeName = new JButton("Change name");
	
	
	

	public ActivityPanel(Controller1 ctr) {
		this.ctr = ctr;
		JComboBox comboBox = new JComboBox(ctr.getAllActivityNames());
		setLayout(new BorderLayout());
		secondButtonPanel.setLayout(new GridLayout(1,2));
		secondButtonPanel.add(changeName);
		secondButtonPanel.add(comboBox);
		
		add(secondButtonPanel, BorderLayout.SOUTH);
		add(dataPanel, BorderLayout.CENTER);
		System.out.println(ctr.getAllActivityNames().toString());
	}
	public void settActivites(List<Activity> activities) {
		
		
	
	}
	
}
