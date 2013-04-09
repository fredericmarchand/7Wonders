package view.game;


import image.Images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.resources.JLabel2D;

import Player.Player;
import WonderBoards.WonderBoardStage;

// The client player's board
public class LocalPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Player player;
	private JLabel2D lblClay, lblTablets, lblRed, lblBlue, lblGrey, lblPurple, lblBrown, lblYellow, lblGreen, 
					 lblGears, lblCompass, lblVictory, lblMilitary, lblNegConflict, lblPosConflict, 
					 lblOre, lblStone, lblWood, lblGlass, lblPapyrus, lblCoin, lblLoom,
					 lblPlayerName;
	
	private JLabel wonderStage1, wonderStage2, wonderStage3, wonderStage4;
	
	public LocalPanel(Player p) {
		player = p;
		setLayout(null);
		setOpaque(false);
		setSize(478, 300);

		wonderStage1 = new JLabel(Images.get("checkmarkBig"));
		wonderStage1.setSize(34, 34);
		wonderStage1.setVisible(false);
		add(wonderStage1);
		
		wonderStage2 = new JLabel(Images.get("checkmarkBig"));
		wonderStage2.setSize(34, 34);
		wonderStage2.setVisible(false);
		add(wonderStage2);
		
		wonderStage3 = new JLabel(Images.get("checkmarkBig"));
		wonderStage3.setSize(34, 34);
		wonderStage3.setVisible(false);
		add(wonderStage3);
		
		wonderStage4 = new JLabel(Images.get("checkmarkBig"));
		wonderStage4.setSize(34, 34);
		wonderStage4.setVisible(false);
		add(wonderStage4);
		
		lblPlayerName = new JLabel2D("11", SwingConstants.CENTER);
		lblPlayerName.setForeground(Color.WHITE);
		lblPlayerName.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblPlayerName.setOutlineColor(Color.BLACK);
		lblPlayerName.setStroke(new BasicStroke(2f));
		lblPlayerName.setBounds(29, 84, 428, 47);
		add(lblPlayerName);
		
		lblBrown = new JLabel2D("11", SwingConstants.CENTER);
		lblBrown.setForeground(Color.WHITE);
		lblBrown.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBrown.setOutlineColor(Color.BLACK);
		lblBrown.setStroke(new BasicStroke(2f));
		lblBrown.setBounds(17, 134, 44, 47);
		add(lblBrown);
		
		lblGrey = new JLabel2D("11", SwingConstants.CENTER);
		lblGrey.setForeground(Color.WHITE);
		lblGrey.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGrey.setOutlineColor(Color.BLACK);
		lblGrey.setStroke(new BasicStroke(2f));
		lblGrey.setBounds(74, 134, 44, 47);
		add(lblGrey);
		
		lblPurple = new JLabel2D("11", SwingConstants.CENTER);
		lblPurple.setForeground(Color.WHITE);
		lblPurple.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPurple.setOutlineColor(Color.BLACK);
		lblPurple.setStroke(new BasicStroke(2f));
		lblPurple.setBounds(129, 134, 44, 47);
		add(lblPurple);
		
		lblBlue = new JLabel2D("11", SwingConstants.CENTER);
		lblBlue.setForeground(Color.WHITE);
		lblBlue.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBlue.setOutlineColor(Color.BLACK);
		lblBlue.setStroke(new BasicStroke(2f));
		lblBlue.setBounds(184, 134, 44, 47);
		add(lblBlue);
		
		lblRed = new JLabel2D("11", SwingConstants.CENTER);
		lblRed.setForeground(Color.WHITE);
		lblRed.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblRed.setOutlineColor(Color.BLACK);
		lblRed.setStroke(new BasicStroke(2f));
		lblRed.setBounds(237, 134, 44, 47);
		add(lblRed);
		
		lblYellow = new JLabel2D("11", SwingConstants.CENTER);
		lblYellow.setForeground(Color.WHITE);
		lblYellow.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblYellow.setOutlineColor(Color.BLACK);
		lblYellow.setStroke(new BasicStroke(2f));
		lblYellow.setBounds(293, 134, 44, 47);
		add(lblYellow);
		
		lblGreen = new JLabel2D("11", SwingConstants.CENTER);
		lblGreen.setForeground(Color.WHITE);
		lblGreen.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGreen.setOutlineColor(Color.BLACK);
		lblGreen.setStroke(new BasicStroke(2f));
		lblGreen.setBounds(347, 134, 44, 47);
		add(lblGreen);
		
		lblGears = new JLabel2D("11", SwingConstants.CENTER);
		lblGears.setForeground(Color.WHITE);
		lblGears.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGears.setOutlineColor(Color.BLACK);
		lblGears.setStroke(new BasicStroke(2f));
		lblGears.setBounds(19, 21, 37, 47);
		add(lblGears);
		
		lblTablets = new JLabel2D("11", SwingConstants.CENTER);
		lblTablets.setForeground(Color.WHITE);
		lblTablets.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTablets.setOutlineColor(Color.BLACK);
		lblTablets.setStroke(new BasicStroke(2f));
		lblTablets.setBounds(69, 21, 40, 47);
		add(lblTablets);
		
		lblCompass = new JLabel2D("11", SwingConstants.CENTER);
		lblCompass.setForeground(Color.WHITE);
		lblCompass.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblCompass.setOutlineColor(Color.BLACK);
		lblCompass.setStroke(new BasicStroke(2f));
		lblCompass.setBounds(115, 21, 44, 47);
		add(lblCompass);
		
		lblVictory = new JLabel2D("11", SwingConstants.CENTER);
		lblVictory.setForeground(Color.WHITE);
		lblVictory.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblVictory.setOutlineColor(Color.BLACK);
		lblVictory.setStroke(new BasicStroke(2f));
		lblVictory.setBounds(198, 21, 44, 47);
		add(lblVictory);
		
		lblMilitary = new JLabel2D("11", SwingConstants.CENTER);
		lblMilitary.setForeground(Color.WHITE);
		lblMilitary.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblMilitary.setOutlineColor(Color.BLACK);
		lblMilitary.setStroke(new BasicStroke(2f));
		lblMilitary.setBounds(288, 21, 44, 47);
		add(lblMilitary);
		
		lblNegConflict = new JLabel2D("-1", SwingConstants.CENTER);
		lblNegConflict.setForeground(Color.WHITE);
		lblNegConflict.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNegConflict.setOutlineColor(Color.BLACK);
		lblNegConflict.setStroke(new BasicStroke(2f));
		lblNegConflict.setBounds(365, 21, 44, 47);
		add(lblNegConflict);
		
		lblPosConflict = new JLabel2D("11", SwingConstants.CENTER);
		lblPosConflict.setForeground(Color.WHITE);
		lblPosConflict.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPosConflict.setOutlineColor(Color.BLACK);
		lblPosConflict.setStroke(new BasicStroke(2f));
		lblPosConflict.setBounds(422, 21, 44, 47);
		add(lblPosConflict);
		
		lblOre = new JLabel2D("11", SwingConstants.CENTER);
		lblOre.setOutlineColor(Color.BLACK);
		lblOre.setForeground(Color.WHITE);
		lblOre.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblOre.setOutlineColor(Color.BLACK);
		lblOre.setStroke(new BasicStroke(2f));
		lblOre.setBounds(16, 192, 44, 47);
		add(lblOre);
		
		lblStone = new JLabel2D("11", SwingConstants.CENTER);
		lblStone.setOutlineColor(Color.BLACK);
		lblStone.setForeground(Color.WHITE);
		lblStone.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblStone.setOutlineColor(Color.BLACK);
		lblStone.setStroke(new BasicStroke(2f));
		lblStone.setBounds(72, 192, 44, 47);
		add(lblStone);
		
		lblWood = new JLabel2D("11", SwingConstants.CENTER);
		lblWood.setOutlineColor(Color.BLACK);
		lblWood.setForeground(Color.WHITE);
		lblWood.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblWood.setOutlineColor(Color.BLACK);
		lblWood.setStroke(new BasicStroke(2f));
		lblWood.setBounds(127, 192, 44, 47);
		add(lblWood);
		
		lblClay = new JLabel2D("11", SwingConstants.CENTER);
		lblClay.setOutlineColor(Color.BLACK);
		lblClay.setForeground(Color.WHITE);
		lblClay.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblClay.setOutlineColor(Color.BLACK);
		lblClay.setStroke(new BasicStroke(2f));
		lblClay.setBounds(180, 192, 44, 47);
		add(lblClay);
		
		lblGlass = new JLabel2D("11", SwingConstants.CENTER);
		lblGlass.setOutlineColor(Color.BLACK);
		lblGlass.setForeground(Color.WHITE);
		lblGlass.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGlass.setOutlineColor(Color.BLACK);
		lblGlass.setStroke(new BasicStroke(2f));
		lblGlass.setBounds(236, 192, 44, 47);
		add(lblGlass);
		
		lblLoom = new JLabel2D("11", SwingConstants.CENTER);
		lblLoom.setOutlineColor(Color.BLACK);
		lblLoom.setForeground(Color.WHITE);
		lblLoom.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblLoom.setOutlineColor(Color.BLACK);
		lblLoom.setStroke(new BasicStroke(2f));
		lblLoom.setBounds(292, 192, 44, 47);
		add(lblLoom);
		
		lblPapyrus = new JLabel2D("11", SwingConstants.CENTER);
		lblPapyrus.setOutlineColor(Color.BLACK);
		lblPapyrus.setForeground(Color.WHITE);
		lblPapyrus.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPapyrus.setOutlineColor(Color.BLACK);
		lblPapyrus.setStroke(new BasicStroke(2f));
		lblPapyrus.setBounds(346, 192, 44, 47);
		add(lblPapyrus);
		
		lblCoin = new JLabel2D("11", SwingConstants.CENTER);
		lblCoin.setOutlineColor(Color.BLACK);
		lblCoin.setForeground(Color.WHITE);
		lblCoin.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblCoin.setOutlineColor(Color.BLACK);
		lblCoin.setStroke(new BasicStroke(2f));
		lblCoin.setBounds(413, 189, 44, 47);
		add(lblCoin);
		
		JLabel overlayLabel = new JLabel(Images.get("overlayNear"));
		overlayLabel.setBounds(0, 0, 478, 300);
		add( overlayLabel );
		
		JLabel boardLabel = new JLabel(Images.get("boardBig"+(player.getWonderBoard().getBoardID()-1)+(player.getWonderBoard().getSide())));
		boardLabel.setBounds(0, 75, 478, 225);
		add( boardLabel );
		
		update();
	}
	
	public void update() {
		updateLabels();
	}
	
	public void updateLabels() {
		lblPlayerName.setText("" + player.getUsername());
		lblOre.setText("" + player.getTotalResources().getOre());
		lblStone.setText("" + player.getTotalResources().getStone());
		lblWood.setText("" + player.getTotalResources().getWood());
		lblClay.setText("" + player.getTotalResources().getClay());
		lblGlass.setText("" + player.getTotalResources().getGlass());
		lblLoom.setText("" + player.getTotalResources().getLoom());
		lblPapyrus.setText("" + player.getTotalResources().getPapyrus());
		lblCoin.setText("" + player.getTotalResources().getCoins());
		lblBrown.setText("" + player.getWonderBoard().getBrownCardAmount());
		lblGrey.setText("" + player.getWonderBoard().getGreyCardAmount());
		lblPurple.setText("" + player.getWonderBoard().getPurpleCardAmount());
		lblBlue.setText("" + player.getWonderBoard().getBlueCardAmount());
		lblRed.setText("" + player.getWonderBoard().getRedCardAmount());
		lblYellow.setText("" + player.getWonderBoard().getYellowCardAmount());
		lblGreen.setText("" + player.getWonderBoard().getGreenCardAmount());
		lblGears.setText("" + player.getScientificSymbols().getGears());
		lblTablets.setText("" + player.getScientificSymbols().getTablets());
		lblCompass.setText("" + player.getScientificSymbols().getCompass());
		lblVictory.setText("" + player.getVictoryPoints());
		lblMilitary.setText("" + player.getShields());
		lblNegConflict.setText(((player.getConflictTokens().getMinusOneTokens() > 0) ? "-" : "") + player.getConflictTokens().getMinusOneTokens());
		lblPosConflict.setText("" + player.getConflictTokens().getTotalPositive());
		ArrayList<WonderBoardStage> stages = player.getWonderBoard().getStages();
		if(stages.size() == 2) {
			wonderStage1.setLocation(168, 257);
			wonderStage1.setVisible(stages.get(0).isBuilt());
			wonderStage2.setLocation(318, 257);
			wonderStage2.setVisible(stages.get(1).isBuilt());
			wonderStage3.setLocation(0, 0);
			wonderStage3.setVisible(false);
			wonderStage4.setLocation(0, 0);
			wonderStage4.setVisible(false);
		} else if(stages.size() == 3) {
			wonderStage1.setLocation(30, 257);
			wonderStage1.setVisible(stages.get(0).isBuilt());
			wonderStage2.setLocation(170, 257);
			wonderStage2.setVisible(stages.get(1).isBuilt());
			wonderStage3.setLocation(314, 257);
			wonderStage3.setVisible(stages.get(2).isBuilt());
			wonderStage4.setLocation(0, 0);
			wonderStage4.setVisible(false);
		} else if(stages.size() == 4) {
			wonderStage1.setLocation(1, 257);
			wonderStage1.setVisible(stages.get(0).isBuilt());
			wonderStage2.setLocation(110, 257);
			wonderStage2.setVisible(stages.get(1).isBuilt());
			wonderStage3.setLocation(238, 257);
			wonderStage3.setVisible(stages.get(2).isBuilt());
			wonderStage4.setLocation(370, 257);
			wonderStage4.setVisible(stages.get(3).isBuilt());
		}
	}
}
