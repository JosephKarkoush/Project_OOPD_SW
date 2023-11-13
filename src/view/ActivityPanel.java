package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.nimbus.AbstractRegionPainter;

import controller.Controller1;
import model.Activity;
import model.User;

public class ActivityPanel extends JPanel {
	private Controller1 ctr;
	private DataPanel dataPanel;
	private User user;
	private MainPanel mainPanel;
	private JPanel secondButtonPanel = new JPanel();
	private JComboBox<String> comboBox;
	private JButton changeName = new JButton("Change name");
	private JButton applyActivity = new JButton("Apply");
	private JButton deleteAll = new JButton("Delete All Activities");

	private String fileName;
	private JButton importButton = new JButton("Import");
	private JButton deleteListButton = new JButton("Delete");
	private JFileChooser fileChooser = new JFileChooser();

	public ActivityPanel(Controller1 ctr, MainPanel mainPanel) {
		this.ctr = ctr;
		this.mainPanel = mainPanel;
		dataPanel = new DataPanel(ctr);
		this.comboBox = new JComboBox();
		updateComboBox();
		setActivity();
		setLayout(new BorderLayout());
		secondButtonPanel.setLayout(new GridLayout(2, 3));
		secondButtonPanel.add(changeName);
		secondButtonPanel.add(applyActivity);
		secondButtonPanel.add(comboBox);
		secondButtonPanel.add(deleteAll);
		secondButtonPanel.add(deleteListButton);
		secondButtonPanel.add(importButton);
		user = ctr.getUser();
		JLabel info = new JLabel("   | Inloggad Som: " + user.getUserName() + ",    " + "Namn: " + user.getName()
				+ ",    " + "Ålder: " + user.getAge() + ",    " + "Vikt: " + user.getWeight() + " |");
		info.setFont(new Font("Arial", Font.PLAIN, 18));

		add(secondButtonPanel, BorderLayout.SOUTH);
		add(dataPanel, BorderLayout.CENTER);
		add(info, BorderLayout.NORTH);
		applyActivity.addActionListener(e -> setActivity());
		changeName.addActionListener(e -> changeName());
		deleteAll.addActionListener(e -> deleteAll());

		importButton.addActionListener(e -> readFileName());
		deleteListButton.addActionListener(e -> delete());

	}

	public void changeName() {
		if (errorCheck("Listan är tom")) {

			String userInput = JOptionPane.showInputDialog("Ange en beskrivning för filen:");
			ctr.setName(userInput);
			updateComboBox();

		}

	}

	public void deleteAll() {
		if (errorCheck("Listan är tom")) {

			ctr.deleteAllActivities();
			updateComboBox();
		}
	}

	public void setActivity() {
		if (errorCheck("Tomt Lista, Vänligen Importera")) {

			String str = comboBox.getSelectedItem().toString();
			ctr.setCurrentActivity(str);
			dataPanel.updateData();
			if (mainPanel.displayPanel != null) {
				mainPanel.updateGraphs();
			}
		}
	}

	public void updateComboBox() {
		comboBox.removeAllItems();
		for (Activity ac : ctr.getAllActivities()) {
			comboBox.addItem(ac.getName());
		}
	}

	public boolean errorCheck(String errorMsg) {
		if (comboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(dataPanel, errorMsg);
			return false;
		} else {
			return true;
		}

	}

	private void readFileName() {
		int userResponse = fileChooser.showOpenDialog(null);
		if (userResponse == JFileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			String fileControll = ".csv";
			if (fileName.substring(fileName.length() - 4).equals(fileControll)) {
				ctr.insertFilePath(fileName);
				ctr.saveActivity();
				String userInput = JOptionPane.showInputDialog("Ange en beskrivning för filen:");
				ctr.setName(userInput);
				updateComboBox();
				dataPanel.updateData();
			} else {
				JOptionPane.showMessageDialog(dataPanel, "Fel fil format!");
			}

		}
	}

	public void delete() {
		if (errorCheck("Listan är tom")) {

			ctr.deleteActivity();
			updateComboBox();
			dataPanel.updateData();
		}
	}

}
