package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Images.Images;
import Player.Player;
import WonderBoards.WonderBoardStage;

public class FarPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Player player;
	private JLabel2D lblPosConflict, lblNegConflict, lblTablets, lblMilitary, lblVictory, lblCompass, lblGears, lblCoin;
	private JLabel wonderStage1, wonderStage2, wonderStage3, wonderStage4;
	
	@SuppressWarnings("unused")
	private Controller controller;
	
	public FarPanel(Player p, Controller c) {
		player = p;
		controller = c;
		setLayout(null);
		setSize(320, 150);
		setBackground(Color.WHITE);

		wonderStage1 = new JLabel(Images.checkmarkSmall);
		wonderStage1.setSize(18, 18);
		wonderStage1.setVisible(false);
		add(wonderStage1);
		
		wonderStage2 = new JLabel(Images.checkmarkSmall);
		wonderStage2.setSize(18, 18);
		wonderStage2.setVisible(false);
		add(wonderStage2);
		
		wonderStage3 = new JLabel(Images.checkmarkSmall);
		wonderStage3.setSize(18, 18);
		wonderStage3.setVisible(false);
		add(wonderStage3);
		
		wonderStage4 = new JLabel(Images.checkmarkSmall);
		wonderStage4.setSize(18, 18);
		wonderStage4.setVisible(false);
		add(wonderStage4);
		
		lblGears = new JLabel2D("11", SwingConstants.CENTER);
		lblGears.setForeground(Color.WHITE);
		lblGears.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGears.setOutlineColor(Color.BLACK);
		lblGears.setStroke(new BasicStroke(2f));
		lblGears.setBounds(8, 8, 37, 47);
		add(lblGears);
		
		lblTablets = new JLabel2D("11", SwingConstants.CENTER);
		lblTablets.setForeground(Color.WHITE);
		lblTablets.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTablets.setOutlineColor(Color.BLACK);
		lblTablets.setStroke(new BasicStroke(2f));
		lblTablets.setBounds(43, 8, 40, 47);
		add(lblTablets);
		
		lblCompass = new JLabel2D("11", SwingConstants.CENTER);
		lblCompass.setForeground(Color.WHITE);
		lblCompass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCompass.setOutlineColor(Color.BLACK);
		lblCompass.setStroke(new BasicStroke(2f));
		lblCompass.setBounds(74, 8, 44, 47);
		add(lblCompass);
		
		lblVictory = new JLabel2D("11", SwingConstants.CENTER);
		lblVictory.setForeground(Color.WHITE);
		lblVictory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVictory.setOutlineColor(Color.BLACK);
		lblVictory.setStroke(new BasicStroke(2f));
		lblVictory.setBounds(131, 7, 44, 47);
		add(lblVictory);
		
		lblMilitary = new JLabel2D("11", SwingConstants.CENTER);
		lblMilitary.setForeground(Color.WHITE);
		lblMilitary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMilitary.setOutlineColor(Color.BLACK);
		lblMilitary.setStroke(new BasicStroke(2f));
		lblMilitary.setBounds(192, 8, 44, 47);
		add(lblMilitary);
		
		lblNegConflict = new JLabel2D("-1", SwingConstants.CENTER);
		lblNegConflict.setForeground(Color.WHITE);
		lblNegConflict.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNegConflict.setOutlineColor(Color.BLACK);
		lblNegConflict.setStroke(new BasicStroke(2f));
		lblNegConflict.setBounds(136, 66, 44, 47);
		add(lblNegConflict);
		
		lblPosConflict = new JLabel2D("11", SwingConstants.CENTER);
		lblPosConflict.setForeground(Color.WHITE);
		lblPosConflict.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPosConflict.setOutlineColor(Color.BLACK);
		lblPosConflict.setStroke(new BasicStroke(2f));
		lblPosConflict.setBounds(231, 67, 44, 47);
		add(lblPosConflict);
		
		lblCoin = new JLabel2D("11", SwingConstants.CENTER);
		lblCoin.setOutlineColor(Color.BLACK);
		lblCoin.setForeground(Color.WHITE);
		lblCoin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCoin.setOutlineColor(Color.BLACK);
		lblCoin.setStroke(new BasicStroke(2f));
		lblCoin.setBounds(42, 63, 44, 47);
		add(lblCoin);
		
		JLabel overlayLabel = new JLabel(Images.overlayFar);
		overlayLabel.setBounds(0, 0, 320, 150);
		add( overlayLabel );
		
		JLabel boardLabel = new JLabel(Images.boardsSmall[player.getWonderBoard().getBoardID()-1][player.getWonderBoard().getSide()]);
		boardLabel.setBounds(0, 0, 320, 150);
		add( boardLabel );
		
		update();
	}

	public void update() {
		updateLabels();
	}
	
	public void updateLabels() {
		updateOverviewLabels();
		updateWonderLabels();
	}
	
	public void updateOverviewLabels() {
		lblGears.setText("" + player.getScientificSymbols().getGears());
		lblTablets.setText("" + player.getScientificSymbols().getTablets());
		lblCompass.setText("" + player.getScientificSymbols().getCompass());
		lblVictory.setText("" + player.getVictoryPoints());
		lblMilitary.setText("" + player.getShields());
		lblNegConflict.setText(((player.getConflictTokens().getMinusOneTokens() > 0) ? "-" : "") + player.getConflictTokens().getMinusOneTokens());
		lblPosConflict.setText("" + player.getConflictTokens().getTotalPositive());
		lblCoin.setText("" + player.getResources().getCoins());
	}
	
	public void updateWonderLabels() {
		ArrayList<WonderBoardStage> stages = player.getWonderBoard().getStages();
		if(stages.size() == 2) {
			wonderStage1.setLocation(115, 122);
			wonderStage1.setVisible(stages.get(0).isBuilt());
			wonderStage2.setLocation(318, 122);
			wonderStage2.setVisible(stages.get(1).isBuilt());
			wonderStage3.setLocation(0, 0);
			wonderStage3.setVisible(false);
			wonderStage4.setLocation(0, 0);
			wonderStage4.setVisible(false);
		} else if(stages.size() == 3) {
			wonderStage1.setLocation(22, 122);
			wonderStage1.setVisible(stages.get(0).isBuilt());
			wonderStage2.setLocation(115, 122);
			wonderStage2.setVisible(stages.get(1).isBuilt());
			wonderStage3.setLocation(318, 122);
			wonderStage3.setVisible(stages.get(2).isBuilt());
			wonderStage4.setLocation(0, 0);
			wonderStage4.setVisible(false);
		} else if(stages.size() == 4) {
			wonderStage1.setLocation(0, 122);
			wonderStage1.setVisible(stages.get(0).isBuilt());
			wonderStage2.setLocation(76, 122);
			wonderStage2.setVisible(stages.get(1).isBuilt());
			wonderStage3.setLocation(161, 122);
			wonderStage3.setVisible(stages.get(2).isBuilt());
			wonderStage4.setLocation(250, 122);
			wonderStage4.setVisible(stages.get(3).isBuilt());
		}
	}
}
