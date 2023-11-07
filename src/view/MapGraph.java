package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Controller1;
import model.TrackPoint;

public class MapGraph extends JPanel {
	Controller1 ctr;
	List<TrackPoint> trackpoints;
	double minLat;
	double minLon;
	double maxLat;
	double maxLon;

	public MapGraph(Controller1 ctr) {
		this.ctr = ctr;
		this.trackpoints = ctr.getCurrentActivity().getList();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		findMaxMinLongLat();
		System.out.println("paint");
		// Initialize arrays
		int[] xArray = new int[trackpoints.size()];
		int[] yArray = new int[trackpoints.size()];

		int i = 0;
		for (TrackPoint tp : trackpoints) {
			xArray[i] = getXPixValue(tp);
			yArray[i] = getYPixValue(tp);
			i++;
		}
		g.setColor(Color.BLUE);
		g.drawPolyline(xArray, yArray, xArray.length);
	}

	private void findMaxMinLongLat() {
		// just to start with some value:
		minLat = Double.parseDouble(trackpoints.get(0).getLatitude());
		minLon = Double.parseDouble(trackpoints.get(0).getLongitude());
		maxLat = Double.parseDouble(trackpoints.get(0).getLatitude());
		maxLat = Double.parseDouble(trackpoints.get(0).getLongitude());
		System.out.println("Mohammed");
		// Finds min and max:
		for (TrackPoint tp : trackpoints) {
			double lon = Double.parseDouble(tp.getLongitude());
			double lat = Double.parseDouble(tp.getLatitude());
			if (lon > maxLon)
				maxLon = lon;
			else if (lon < minLon)
				minLon = lon;
			if (lat > maxLat)
				maxLat = lat;
			else if (lat < minLat)
				minLat = lat;
		}
	}

	private int getXPixValue(TrackPoint tp) {
		int xPix = (int) (((Double.parseDouble(tp.getLongitude()) - minLon) / (maxLon - minLon)) * getWidth());
		System.out.println("getX");
		return xPix;
	}

	private int getYPixValue(TrackPoint tp) {
		int yPix = (int) (((Double.parseDouble(tp.getLatitude()) - minLat) / (maxLat - minLat)) * getHeight());
		yPix = getHeight() - yPix; // To adjust for y-axis going "downwards" in graphics
		System.out.println("gety");
		return yPix;
	}

}
