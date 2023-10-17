package view;

import javax.swing.SwingUtilities;

public class GuiTest {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainFrame());
	}
}
