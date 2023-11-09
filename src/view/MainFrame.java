package view;

import controller.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private Controller1 ctr;
	private MainPanel mainPanel;

	public MainFrame(Controller1 ctr) {
		this.ctr = ctr;
		this.mainPanel = new MainPanel(ctr);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
	}

}
