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

	GraphPanel graphPanel;


	public DisplayPanel(Controller1 ctr) {
		this.ctr = ctr;
		setLayout(new BorderLayout());
		graphPanel = new GraphPanel(ctr);
		add(graphPanel, BorderLayout.CENTER);

	}
	public void updateGraphs() {
		graphPanel.updateGraphs();
	}
}
