package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DisplayPanel extends JPanel{
	
	GraphPanel graphPanel = new GraphPanel();
	
	JPanel buttonPanel = new JPanel();
	JButton importButton = new JButton("Import");
	JButton addListButton = new JButton("Add to list");
	
	public DisplayPanel() {
		setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(addListButton);
		buttonPanel.add(importButton);
		add(buttonPanel, BorderLayout.SOUTH);
		add(graphPanel, BorderLayout.CENTER);
		
		
	}

}
