package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DisplayPanel extends JPanel{
	
	GraphPanel graphPanel = new GraphPanel();
	JButton importButton = new JButton("Import");
	JButton addListButton = new JButton("Add to list");
	
	public DisplayPanel() {
		setLayout(new BorderLayout());
		add(importButton, BorderLayout.SOUTH);
		add(addListButton, BorderLayout.SOUTH);
		add(graphPanel, BorderLayout.CENTER);
		
		
	}

}
