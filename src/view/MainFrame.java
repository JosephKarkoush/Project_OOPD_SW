package view;
import controller.*;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	Controller1 ctr = new Controller1();
	MainPanel mainPanel = new MainPanel(ctr);
	
	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);
		setSize(1000, 1000);
		setVisible(true);
	}

}
