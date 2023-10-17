package view;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class DataPanel extends JScrollPane {
	
	JLabel dataInformation = new JLabel();
	
	public DataPanel() {
		add(dataInformation);
		
	}
	
	public void setText(String str) {
		dataInformation.setText(str);
	}

}
