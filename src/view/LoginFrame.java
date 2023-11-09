package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.Controller1;

public class LoginFrame extends JFrame {
	Controller1 ctr = new Controller1();

	private JPanel panel = new JPanel();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton button = new JButton("Login");

	public LoginFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
		panel.setLayout(new GridLayout(5, 1));
		add(panel);
		panel.add(new JLabel("Användernamn:"));
		panel.add(username);
		panel.add(new JLabel("Lösenord:"));
		panel.add(password);
		panel.add(button);

		button.addActionListener(e -> checkbutton());

	}

	public void checkbutton() {

		if (ctr.getUser().getUserName().equals(username.getText())
				&& ctr.getUser().getPassword().equals(password.getText())) {
			SwingUtilities.invokeLater(() -> new MainFrame(ctr));
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Fel användernamn eller lösenord! Försök igen");
		}
	}

}
