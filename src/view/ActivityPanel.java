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
	Controller1 ctr;
	DataPanel dataPanel;
	//UserInfo userInfo;
	User user;
	MainPanel mainPanel;
	JPanel secondButtonPanel = new JPanel();
	JComboBox<String> comboBox;
	JButton changeName = new JButton("Change name");
	JButton applyActivity = new JButton("Apply");
	JButton deleteAll = new JButton("Delete All Activities");

	String fileName;
	JPanel buttonPanel = new JPanel();
	JButton importButton = new JButton("Import");
	JButton deleteListButton = new JButton("Delete");
	JFileChooser fileChooser = new JFileChooser();

	public ActivityPanel(Controller1 ctr, MainPanel mainPanel) {
		this.ctr = ctr;
		this.mainPanel = mainPanel;
		dataPanel = new DataPanel(ctr);
		this.comboBox = new JComboBox();
		//this.userInfo = new UserInfo(ctr);
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
		JLabel info = new JLabel("   | Inloggad Som: " + user.getUserName()+ ",    " + "Namn: " + user.getName() + ",    " + "Ålder: " + user.getAge() + ",    " + "Vikt: " + user.getWeight() + " |");
		info.setFont(new Font("Arial", Font.PLAIN, 25));
		
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
			System.out.println(fileName);
			ctr.insertFilePath(fileName);
			ctr.saveActivity();
			String userInput = JOptionPane.showInputDialog("Ange en beskrivning för filen:");
			ctr.setName(userInput);
			updateComboBox();
			dataPanel.updateData();

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
