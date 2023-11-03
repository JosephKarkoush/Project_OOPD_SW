package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controller.Controller1;

public class DisplayPanel extends JPanel {
	private Controller1 ctr;
	ActivityPanel activityPanel;
	String fileName;

	GraphPanel graphPanel;
	JPanel buttonPanel = new JPanel();
	JButton importButton = new JButton("Import");
	JButton deleteListButton = new JButton("Delete");
	JFileChooser fileChooser = new JFileChooser();

	public DisplayPanel(Controller1 ctr, ActivityPanel activityPanel) {
		this.ctr = ctr;
		this.activityPanel = activityPanel;
		graphPanel = new GraphPanel(ctr);
		setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(deleteListButton);
		buttonPanel.add(importButton);
		add(buttonPanel, BorderLayout.SOUTH);
		add(graphPanel, BorderLayout.CENTER);
		importButton.addActionListener(e -> readFileName());
		deleteListButton.addActionListener(e -> delete());
	}

	public void delete() {
		if (activityPanel.errorCheck("Listan är tom")) {

			ctr.deleteActivity();
			activityPanel.updateComboBox();
			activityPanel.dataPanel.updateData();
		}
	}

	private void readFileName() {
		int userResponse = fileChooser.showOpenDialog(null);
		if (userResponse == JFileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println(fileName);
			ctr.insertFilePath(fileName);
			ctr.saveActivity();
			String userInput = JOptionPane.showInputDialog("Ange en beskrivning för filen:");
			ctr.setName(userInput);
			activityPanel.updateComboBox();
			activityPanel.dataPanel.updateData();

		}
	}
}
