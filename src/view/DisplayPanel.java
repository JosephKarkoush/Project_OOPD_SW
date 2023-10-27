package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller1;

public class DisplayPanel extends JPanel {
	private Controller1 ctr;
	String fileName;
	GraphPanel graphPanel = new GraphPanel(ctr);

	JPanel buttonPanel = new JPanel();
	JButton importButton = new JButton("Import");
	JButton addListButton = new JButton("Delete");
	JFileChooser fileChooser = new JFileChooser();

	public DisplayPanel(Controller1 ctr) {
		this.ctr=ctr;
		
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
			ctr.insertFilePath(fileName);
		}
	}
}
