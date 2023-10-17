package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ActivityPanel extends JPanel {
	DataPanel dataPanel = new DataPanel();
	
	JPanel secondButtonPanel = new JPanel();
	JButton changeName = new JButton("Change name");
	JComboBox<String> comboActivityList = new JComboBox<>();

	public ActivityPanel() {
		setLayout(new BorderLayout());
		secondButtonPanel.setLayout(new GridLayout(1,2));
		secondButtonPanel.add(changeName);
		secondButtonPanel.add(comboActivityList);
		
		add(secondButtonPanel, BorderLayout.SOUTH);
		add(dataPanel, BorderLayout.CENTER);

	}
}
