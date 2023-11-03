package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller1;

public class GraphPanel extends JTabbedPane {
	Controller1 ctr;
	MapGraph mapGraph = new MapGraph(ctr);
	AltitudeGraph altitudeGraph = new AltitudeGraph(ctr);
	
	HeartRateGraph heartRateGraph = new HeartRateGraph(ctr);
	
	public GraphPanel(Controller1 ctr) {
		this.ctr = ctr;
		SpeedGraph speedGraph = new SpeedGraph(ctr);
		addTab("Map Graph", mapGraph);
		addTab("Map Altitude", altitudeGraph);
		addTab("Speed Graph", speedGraph);
		addTab("Heart Rate Graph", heartRateGraph);
	}
}
