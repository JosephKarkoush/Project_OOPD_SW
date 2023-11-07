package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginFrame extends JFrame {
	String userName = "22moal04";
	String passWord = "1234";
	
	JPanel panel = new JPanel();
	JTextField username = new JTextField() ;
	JPasswordField password = new JPasswordField() ;
	JButton button = new JButton("Login");

	public LoginFrame() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);
		panel.setLayout(new GridLayout(5,1));
		add(panel);
		panel.add(new JLabel("Användernamn:"));
		panel.add(username);
		panel.add(new JLabel("Lösenord:"));
		panel.add(password);
		panel.add(button);
		
		button.addActionListener(e -> checkbutton());
		
		

		
	}
	
	public void checkbutton() {
		
		if(userName.equals(username.getText()) && passWord.equals(password.getText())) {
			SwingUtilities.invokeLater(() -> new MainFrame());
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Fel användernamn eller lösenord! Försök igen");
		}
	}

}
