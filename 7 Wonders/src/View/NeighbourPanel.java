package View;

import javax.swing.*;

import Player.Player;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class NeighbourPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Player player;
	
	public NeighbourPanel(Player p) {
		player = p;
		
		setLayout(null);
		setSize(478, 300);
		setBackground(Color.BLACK);
		
		JLabel2D lblBrown = new JLabel2D("11", SwingConstants.CENTER);
		lblBrown.setForeground(Color.WHITE);
		lblBrown.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBrown.setOutlineColor(Color.BLACK);
		lblBrown.setStroke(new BasicStroke(2f));
		lblBrown.setBounds(16, 115, 44, 47);
		add(lblBrown);
		
		JLabel2D lblGrey = new JLabel2D("11", SwingConstants.CENTER);
		lblGrey.setForeground(Color.WHITE);
		lblGrey.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGrey.setOutlineColor(Color.BLACK);
		lblGrey.setStroke(new BasicStroke(2f));
		lblGrey.setBounds(73, 115, 44, 47);
		add(lblGrey);
		
		JLabel2D lblPurple = new JLabel2D("11", SwingConstants.CENTER);
		lblPurple.setForeground(Color.WHITE);
		lblPurple.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPurple.setOutlineColor(Color.BLACK);
		lblPurple.setStroke(new BasicStroke(2f));
		lblPurple.setBounds(128, 115, 44, 47);
		add(lblPurple);
		
		JLabel2D lblBlue = new JLabel2D("11", SwingConstants.CENTER);
		lblBlue.setForeground(Color.WHITE);
		lblBlue.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBlue.setOutlineColor(Color.BLACK);
		lblBlue.setStroke(new BasicStroke(2f));
		lblBlue.setBounds(183, 115, 44, 47);
		add(lblBlue);
		
		JLabel2D lblRed = new JLabel2D("11", SwingConstants.CENTER);
		lblRed.setForeground(Color.WHITE);
		lblRed.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblRed.setOutlineColor(Color.BLACK);
		lblRed.setStroke(new BasicStroke(2f));
		lblRed.setBounds(236, 115, 44, 47);
		add(lblRed);
		
		JLabel2D lblYellow = new JLabel2D("11", SwingConstants.CENTER);
		lblYellow.setForeground(Color.WHITE);
		lblYellow.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblYellow.setOutlineColor(Color.BLACK);
		lblYellow.setStroke(new BasicStroke(2f));
		lblYellow.setBounds(292, 115, 44, 47);
		add(lblYellow);
		
		JLabel2D lblGreen = new JLabel2D("11", SwingConstants.CENTER);
		lblGreen.setForeground(Color.WHITE);
		lblGreen.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGreen.setOutlineColor(Color.BLACK);
		lblGreen.setStroke(new BasicStroke(2f));
		lblGreen.setBounds(346, 115, 44, 47);
		add(lblGreen);
		
		JLabel2D lblGears = new JLabel2D("11", SwingConstants.CENTER);
		lblGears.setForeground(Color.WHITE);
		lblGears.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGears.setOutlineColor(Color.BLACK);
		lblGears.setStroke(new BasicStroke(2f));
		lblGears.setBounds(19, 21, 37, 47);
		add(lblGears);
		
		JLabel2D lblTablets = new JLabel2D("11", SwingConstants.CENTER);
		lblTablets.setForeground(Color.WHITE);
		lblTablets.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTablets.setOutlineColor(Color.BLACK);
		lblTablets.setStroke(new BasicStroke(2f));
		lblTablets.setBounds(69, 21, 40, 47);
		add(lblTablets);
		
		JLabel2D lblCompass = new JLabel2D("11", SwingConstants.CENTER);
		lblCompass.setForeground(Color.WHITE);
		lblCompass.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblCompass.setOutlineColor(Color.BLACK);
		lblCompass.setStroke(new BasicStroke(2f));
		lblCompass.setBounds(115, 21, 44, 47);
		add(lblCompass);
		
		JLabel2D lblVictory = new JLabel2D("11", SwingConstants.CENTER);
		lblVictory.setForeground(Color.WHITE);
		lblVictory.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblVictory.setOutlineColor(Color.BLACK);
		lblVictory.setStroke(new BasicStroke(2f));
		lblVictory.setBounds(198, 21, 44, 47);
		add(lblVictory);
		
		JLabel2D lblMilitary = new JLabel2D("11", SwingConstants.CENTER);
		lblMilitary.setForeground(Color.WHITE);
		lblMilitary.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblMilitary.setOutlineColor(Color.BLACK);
		lblMilitary.setStroke(new BasicStroke(2f));
		lblMilitary.setBounds(288, 21, 44, 47);
		add(lblMilitary);
		
		JLabel2D lblNegConflict = new JLabel2D("-1", SwingConstants.CENTER);
		lblNegConflict.setForeground(Color.WHITE);
		lblNegConflict.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNegConflict.setOutlineColor(Color.BLACK);
		lblNegConflict.setStroke(new BasicStroke(2f));
		lblNegConflict.setBounds(365, 21, 44, 47);
		add(lblNegConflict);
		
		JLabel2D lblPosConflict = new JLabel2D("11", SwingConstants.CENTER);
		lblPosConflict.setForeground(Color.WHITE);
		lblPosConflict.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPosConflict.setOutlineColor(Color.BLACK);
		lblPosConflict.setStroke(new BasicStroke(2f));
		lblPosConflict.setBounds(422, 21, 44, 47);
		add(lblPosConflict);
		
		JLabel2D lblOre = new JLabel2D("11", 0);
		lblOre.setOutlineColor(Color.BLACK);
		lblOre.setForeground(Color.WHITE);
		lblOre.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblOre.setOutlineColor(Color.BLACK);
		lblOre.setStroke(new BasicStroke(2f));
		lblOre.setBounds(16, 177, 44, 47);
		add(lblOre);
		
		JLabel2D lblStone = new JLabel2D("11", 0);
		lblStone.setOutlineColor(Color.BLACK);
		lblStone.setForeground(Color.WHITE);
		lblStone.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblStone.setOutlineColor(Color.BLACK);
		lblStone.setStroke(new BasicStroke(2f));
		lblStone.setBounds(72, 177, 44, 47);
		add(lblStone);
		
		JLabel2D lblWood = new JLabel2D("11", 0);
		lblWood.setOutlineColor(Color.BLACK);
		lblWood.setForeground(Color.WHITE);
		lblWood.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblWood.setOutlineColor(Color.BLACK);
		lblWood.setStroke(new BasicStroke(2f));
		lblWood.setBounds(127, 177, 44, 47);
		add(lblWood);
		
		JLabel2D lblClay = new JLabel2D("11", 0);
		lblClay.setOutlineColor(Color.BLACK);
		lblClay.setForeground(Color.WHITE);
		lblClay.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblClay.setOutlineColor(Color.BLACK);
		lblClay.setStroke(new BasicStroke(2f));
		lblClay.setBounds(180, 177, 44, 47);
		add(lblClay);
		
		JLabel2D lblGlass = new JLabel2D("11", 0);
		lblGlass.setOutlineColor(Color.BLACK);
		lblGlass.setForeground(Color.WHITE);
		lblGlass.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGlass.setOutlineColor(Color.BLACK);
		lblGlass.setStroke(new BasicStroke(2f));
		lblGlass.setBounds(236, 177, 44, 47);
		add(lblGlass);
		
		JLabel2D lblLoom = new JLabel2D("11", 0);
		lblLoom.setOutlineColor(Color.BLACK);
		lblLoom.setForeground(Color.WHITE);
		lblLoom.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblLoom.setOutlineColor(Color.BLACK);
		lblLoom.setStroke(new BasicStroke(2f));
		lblLoom.setBounds(292, 177, 44, 47);
		add(lblLoom);
		
		JLabel2D lblPapyrus = new JLabel2D("11", 0);
		lblPapyrus.setOutlineColor(Color.BLACK);
		lblPapyrus.setForeground(Color.WHITE);
		lblPapyrus.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPapyrus.setOutlineColor(Color.BLACK);
		lblPapyrus.setStroke(new BasicStroke(2f));
		lblPapyrus.setBounds(346, 177, 44, 47);
		add(lblPapyrus);
		
		JLabel2D lblCoin = new JLabel2D("11", 0);
		lblCoin.setOutlineColor(Color.BLACK);
		lblCoin.setForeground(Color.WHITE);
		lblCoin.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblCoin.setOutlineColor(Color.BLACK);
		lblCoin.setStroke(new BasicStroke(2f));
		lblCoin.setBounds(413, 174, 44, 47);
		add(lblCoin);
		
		JLabel overlayLabel = new JLabel(new ImageIcon(NeighbourPanel.class.getResource("/Images/Icons/overlay.png")));
		overlayLabel.setBounds(0, 0, 478, 300);
		add( overlayLabel );
		JLabel boardLabel = new JLabel(new ImageIcon(NeighbourPanel.class.getResource("/Images/Boards/board"+player.getWonderBoard().getBoardID()+player.getWonderBoard().getSide()+".png")));
		boardLabel.setBounds(0, 75, 478, 225);
		add( boardLabel );
	}
}
