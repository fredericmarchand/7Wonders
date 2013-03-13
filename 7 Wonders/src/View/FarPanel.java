package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Player.Player;

public class FarPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Player player;
	private JLabel2D lblPosConflict, lblNegConflict, lblTablets, lblMilitary, lblVictory, lblCompass, lblGears, lblCoin;
	
	private Controller controller;
	
	public FarPanel(Player p, Controller c) {
		player = p;
		controller = c;
		setLayout(null);
		setSize(320, 150);
		setBackground(Color.BLACK);
		
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
		
		JLabel overlayLabel = new JLabel(new ImageIcon(NearPanel.class.getResource("/Images/Icons/overlayforeign.png")));
		overlayLabel.setBounds(0, 0, 320, 150);
		add( overlayLabel );
		
		JLabel boardLabel = new JLabel(new ImageIcon(new ImageIcon(NearPanel.class.getResource("/Images/Boards/board"+player.getWonderBoard().getBoardID()+(player.getWonderBoard().getSide()+1)+".png")).getImage().getScaledInstance(320, 150, java.awt.Image.SCALE_SMOOTH)));
		boardLabel.setBounds(0, 0, 320, 150);
		add( boardLabel );
		
		update();
	}

	public void update() {
		updateLabels();
	}
	
	public void updateLabels() {
		updateOverviewLabels();
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
}
