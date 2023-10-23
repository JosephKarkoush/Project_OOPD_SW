package view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DataPanel extends JPanel {
	
	JLabel dataInformation = new JLabel("");
	
	public DataPanel() {
		setLayout(new FlowLayout());
		add(dataInformation);
		setVisible(true);
		setEnabled(true);
	}
	
	public void setText(String str) {
		dataInformation.setText(str);
	}

}
