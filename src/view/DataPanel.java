package view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller1;

public class DataPanel extends JPanel {
	Controller1 ctr;
	JLabel dataInformation = new JLabel("");
	
	public DataPanel(Controller1 ctr) {
		this.ctr = ctr;
		setLayout(new FlowLayout());
		add(dataInformation);
		setVisible(true);
		setEnabled(true);
	}
	
	public void setText(String str) {
		dataInformation.setText(str);
	}

}
