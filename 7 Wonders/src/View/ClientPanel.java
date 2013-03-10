package View;

import java.awt.*;
import javax.swing.*;

import Controls.Match;

public class ClientPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Match match;
	
	public ClientPanel() {
		match = new Match();
		
		GridBagLayout l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(l);
		JLabel label = new JLabel("Hello World"); 
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 0;
		c.weighty = 0;
		l.setConstraints(label, c);
		add(label);
	}
	
	
}
