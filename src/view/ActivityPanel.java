package view;

import java.awt.BorderLayout;

import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ActivityPanel extends JPanel {
	DataPanel dataPanel = new DataPanel();
	JButton changeName = new JButton("Change name");
	JComboBox<String> comboActivityList = new JComboBox<>();

	public ActivityPanel() {
		setLayout(new BorderLayout());
		add(changeName, BorderLayout.SOUTH);
		add(comboActivityList, BorderLayout.SOUTH);
		add(dataPanel, BorderLayout.CENTER);

	}
}
