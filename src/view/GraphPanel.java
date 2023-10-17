package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GraphPanel extends JTabbedPane {
	
	MapGraph mapGraph = new MapGraph();
	AltitudeGraph altitudeGraph = new AltitudeGraph();
	SpeedGraph speedGraph = new SpeedGraph();
	HeartRateGraph heartRateGraph = new HeartRateGraph();
	
	public GraphPanel() {
		
		addTab("Map Graph", mapGraph);
		addTab("Map Altitude", altitudeGraph);
		addTab("Speed Graph", speedGraph);
		addTab("Heart Rate Graph", heartRateGraph);
	}
}
