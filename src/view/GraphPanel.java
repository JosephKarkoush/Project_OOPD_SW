package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller1;

public class GraphPanel extends JTabbedPane {
	Controller1 ctr;
	MapGraph mapGraph;
	AltitudeGraph altitudeGraph;
	SpeedGraph speedGraph;
	HeartRateGraph heartRateGraph;
	
	public GraphPanel(Controller1 ctr) {
		this.ctr = ctr;
		speedGraph = new SpeedGraph(ctr);
		heartRateGraph = new HeartRateGraph(ctr);
		altitudeGraph = new AltitudeGraph(ctr);
		mapGraph = new MapGraph(ctr);
		addTab("Map Graph", mapGraph);
		addTab("Map Altitude", altitudeGraph);
		addTab("Speed Graph", speedGraph);
		addTab("Heart Rate Graph", heartRateGraph);
	}
	
	public void updateGraphs() {
		speedGraph.updateSpeedGraph();
	}
}
