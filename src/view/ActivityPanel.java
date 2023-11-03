package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.nimbus.AbstractRegionPainter;

import controller.Controller1;
import model.Activity;

public class ActivityPanel extends JPanel {
	Controller1 ctr;
	DataPanel dataPanel;

	JPanel secondButtonPanel = new JPanel();
	JComboBox<String> comboBox;
	JButton changeName = new JButton("Change name");
	JButton applyActivity = new JButton("Apply");
	JButton deleteAll = new JButton("Delete All Activities");

	public ActivityPanel(Controller1 ctr) {
		this.ctr = ctr;
		 dataPanel = new DataPanel(ctr);
		this.comboBox = new JComboBox();
		
		updateComboBox();
		setActivity();
		setLayout(new BorderLayout());
		secondButtonPanel.setLayout(new GridLayout(2, 2));
		secondButtonPanel.add(changeName);
		secondButtonPanel.add(applyActivity);
		secondButtonPanel.add(comboBox);
		secondButtonPanel.add(deleteAll);
		add(secondButtonPanel, BorderLayout.SOUTH);
		add(dataPanel, BorderLayout.CENTER);
		applyActivity.addActionListener(e -> setActivity());
		changeName.addActionListener(e -> changeName());
		deleteAll.addActionListener(e -> deleteAll());
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

}
