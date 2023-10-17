package view;

import javax.swing.JFrame;

public class MainFram extends JFrame {
	
	MainPanel mainPanel = new MainPanel();
	
	public MainFram() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);
		setSize(1000, 1000);
		setVisible(true);
	}

}
