package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.Controller1;
import model.TrackPoint;

public class MapGraph extends JPanel {
	private Controller1 ctr;
	private List<TrackPoint> trackpoints;
	private double minLat;
	private double minLon;
	private double maxLat;
	private double maxLon;

	public MapGraph(Controller1 ctr) {
		this.ctr = ctr;
		this.trackpoints = ctr.getCurrentActivity().getList();
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder("map"));
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
		maxLon = Double.parseDouble(trackpoints.get(0).getLongitude());
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
		System.out.println(xPix);
		return xPix;
	}

	private int getYPixValue(TrackPoint tp) {
		int yPix = (int) (((Double.parseDouble(tp.getLatitude()) - minLat) / (maxLat - minLat)) * getHeight());
		yPix = getHeight() - yPix; // To adjust for y-axis going "downwards" in graphics
		System.out.println(yPix);
//		for(TrackPoint tc : trackpoints) {
//			System.out.println(tc.getLatitude());
//		}

		return yPix;
	}

}
