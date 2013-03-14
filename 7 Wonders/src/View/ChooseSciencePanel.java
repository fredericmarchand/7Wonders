package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ChooseSciencePanel extends JPanel {
	public ChooseSciencePanel() {
		setLayout(null);
		
		JLabel lblScience = new JLabel("");
		lblScience.setIcon(new ImageIcon(ChooseSciencePanel.class.getResource("/Images/Icons/sciencepicker.png")));
		lblScience.setBounds(0, 26, 178, 88);
		add(lblScience);
		
		JLabel lblPurpose = new JLabel("Choose one symbol:");
		lblPurpose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPurpose.setBounds(10, 8, 159, 14);
		add(lblPurpose);
	}
}
