package View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Player.Player;

public class ForeignPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Player player;
	
	public ForeignPanel(Player p) {
		setLayout(null);
		setSize(320, 150);
		setBackground(Color.BLACK);
		
		JLabel overlayLabel = new JLabel(new ImageIcon(NeighbourPanel.class.getResource("/Images/Icons/overlayforeign.png")));
		overlayLabel.setBounds(0, 0, 320, 150);
		add( overlayLabel );
		JLabel boardLabel = new JLabel(new ImageIcon(new ImageIcon(NeighbourPanel.class.getResource("/Images/Boards/board"+player.getWonderBoard().getBoardID()+player.getWonderBoard().getSide()+".png")).getImage().getScaledInstance(320, 150, java.awt.Image.SCALE_SMOOTH)));
		boardLabel.setBounds(0, 0, 320, 150);
		add( boardLabel );
	}

}
