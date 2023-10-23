package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DisplayPanel extends JPanel {
	String fileName;
	GraphPanel graphPanel = new GraphPanel();

	JPanel buttonPanel = new JPanel();
	JButton importButton = new JButton("Import");
	JButton addListButton = new JButton("Add to list");
	JFileChooser fileChooser = new JFileChooser();

	public DisplayPanel() {
		setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(addListButton);
		buttonPanel.add(importButton);
		add(buttonPanel, BorderLayout.SOUTH);
		add(graphPanel, BorderLayout.CENTER);
		importButton.addActionListener(e -> readFileName());
	}

	private void readFileName() {
		int userResponse = fileChooser.showOpenDialog(null);
		if (userResponse == JFileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
		}
	}
}
