package view;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.Controller1;
import model.Activity;

public class SpeedGraph extends JPanel {

	Controller1 ctr;
	Activity activity;
	PlotView pw;
	
	public SpeedGraph(Controller1 ctr) {
		this.ctr = ctr;
		add(pw);

		}
	
	public void updateSpeedGraph() {
		if(ctr.getCurrentActivity() != null)
		activity = ctr.getCurrentActivity();
		pw = new PlotView("hastighet", this.activity,tp -> Double.parseDouble(tp.getSpeed()));

		
	}
	}


