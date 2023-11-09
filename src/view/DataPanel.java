package view;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller1;

public class DataPanel extends JPanel {
	private Controller1 ctr;
	private String totalDistance;
	private String startTime;
	private String endTime;
	private String minSpeed;
	private String maxSpeed;
	private String avgSpeed;
	private String minCandece;
	private String maxCandece;
	private String avgCadence;
	private String minHeartRate;
	private String maxHeartRate;
	private String avgHeartRate;

	JLabel dataInformation = new JLabel("");

	public DataPanel(Controller1 ctr) {
		this.ctr = ctr;
		dataInformation.setFont(new Font("Arial", Font.PLAIN, 25));
		setLayout(new FlowLayout());
		add(dataInformation);
		setVisible(true);
		setEnabled(true);
	}

	public void setText(String str) {
		dataInformation.setText(str);
	}

	public void updateData() {
		setText("");
		if (ctr.getCurrentActivity() != null) {

			totalDistance = ctr.getCurrentActivity().getStatistic().getTotalDistance();
			startTime = ctr.getCurrentActivity().getStatistic().getStartTime();
			endTime = ctr.getCurrentActivity().getStatistic().getEndTime();
			minSpeed = ctr.getCurrentActivity().getStatistic().getMinSpeed();
			maxSpeed = ctr.getCurrentActivity().getStatistic().getMaxSpeed();
			avgSpeed = ctr.getCurrentActivity().getStatistic().getAvgSpeed();
			minCandece = ctr.getCurrentActivity().getStatistic().getMinCadence();
			maxCandece = ctr.getCurrentActivity().getStatistic().getMaxCadence();
			avgCadence = ctr.getCurrentActivity().getStatistic().getAvgCadence();
			minHeartRate = ctr.getCurrentActivity().getStatistic().getMinHeartRate();
			maxHeartRate = ctr.getCurrentActivity().getStatistic().getMaxHeartRate();
			avgHeartRate = ctr.getCurrentActivity().getStatistic().getAvgHeartRate();
			setText("<html>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "Total Distance: " + totalDistance
					+ "<br>" + "<br>" + "Start Time: " + startTime + "<br>" + "<br>" + "End Time: " + endTime + "<br>"
					+ "<br>" + "Minimum Speed: " + minSpeed + "<br>" + "<br>" + "Maximum Speed: " + maxSpeed + "<br>"
					+ "<br>" + "Avrage Speed: " + avgSpeed + "<br>" + "<br>" + "Minimum Cadence: " + minCandece + "<br>"
					+ "<br>" + "Maximum Cadence: " + maxCandece + "<br>" + "<br>" + "Avrage Cadence: " + avgCadence
					+ "<br>" + "<br>" + "Minimum Heart Rate: " + minHeartRate + "<br>" + "<br>" + "Maximum Heart Rate: "
					+ maxHeartRate + "<br>" + "<br>" + "Avrage Heart Rate: " + avgHeartRate + "<br>" + "<br>"
					+ "</html>");
		} else {
			setText("Ingen data att visa ut!");
		}
	}

}
