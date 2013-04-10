package view.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import image.Images;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.resources.JLabel2D;

public class WaitingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel bgimg;
	private JLabel2D waitingLabel, countLabel;
	
	public WaitingPanel() {
		setLayout(null);
		setSize(1280, 860);
		
		waitingLabel = new JLabel2D("Waiting for other players to connect.", SwingConstants.CENTER);
		waitingLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		waitingLabel.setForeground(Color.WHITE);
		waitingLabel.setOutlineColor(Color.BLACK);
		waitingLabel.setStroke(new BasicStroke(2f));
		waitingLabel.setBounds(290, 428, 700, 43);
		add(waitingLabel);
		
		countLabel = new JLabel2D("", SwingConstants.CENTER);
		countLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		countLabel.setForeground(Color.WHITE);
		countLabel.setOutlineColor(Color.BLACK);
		countLabel.setStroke(new BasicStroke(2f));
		countLabel.setBounds(290, 471, 700, 43);
		add(countLabel);
		
		bgimg = new JLabel();
		bgimg.setLocation(0, 0);
		bgimg.setSize(1280, 860);
		add(bgimg);
		
		update(2, 3);
	}
	
	public void update(int current, int total) {
		countLabel.setText("" + current + " of " + total + " players are connected. Waiting for " + (total-current) + " more player" + ((total-current == 1) ? "" : "s") + ".");
	}
	
	public void showGUI() {
		bgimg.setIcon(Images.get("bg"));
		setVisible(true);
	}
}
