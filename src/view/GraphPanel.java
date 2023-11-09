package view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller1;
import model.Activity;

public class GraphPanel extends JTabbedPane {
	private Controller1 ctr;
	private Activity activity;

	public GraphPanel(Controller1 ctr) {
		this.ctr = ctr;
	}

	public void updateGraphs() {
		removeAll();
		this.activity = ctr.getCurrentActivity();
		addTab("Hastighet", new PlotView("Hastighet", this.activity, tp -> Double.parseDouble(tp.getSpeed())));
		addTab("Puls", new PlotView("Puls", this.activity, tp -> Double.parseDouble(tp.getHeartRate())));
		addTab("Altitude", new PlotView("Altitude", this.activity, tp -> Double.parseDouble(tp.getAltitude())));
		addTab("Map graph", new MapGraph(ctr));

	}

}
