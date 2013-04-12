package view.game;

import image.Images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.resources.JLabel2D;

import Player.Player;

public class GameOverPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Player> players;
	
	private ArrayList<JLabel> lblImg;
	private ArrayList<JLabel2D> lblVic, lblName;
	
	public GameOverPanel() {
		setLayout(null);
		setSize(1280, 860);
		setBackground(new Color(50, 50, 50, 200));
		
		players = new ArrayList<Player>();
		lblImg = new ArrayList<JLabel>();
		lblVic = new ArrayList<JLabel2D>();
		lblName = new ArrayList<JLabel2D>();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
	}
	
	public void showGUI(ArrayList<Player> l) {
		players = new ArrayList<Player>(l);
		Collections.sort(players, new Comparator<Player>(){
		    public int compare(Player o1, Player o2) {
		        return o2.getVictoryPoints() - o1.getVictoryPoints();
		    }
		});
		
		removeAll();
		lblImg.clear();
		lblVic.clear();
		lblName.clear();
		
		for(int i = 0; i < players.size(); i++) {
			lblImg.add(new JLabel(Images.get("victory")));
			lblImg.get(i).setSize(85, 73);
			lblImg.get(i).setLocation(416, 100 + i*80);
			
			lblVic.add(new JLabel2D(""+players.get(i).getVictoryPoints(), SwingConstants.CENTER));
			lblVic.get(i).setFont(new Font("Arial", Font.PLAIN, 28));
			lblVic.get(i).setForeground(Color.WHITE);
			lblVic.get(i).setOutlineColor(Color.BLACK);
			lblVic.get(i).setStroke(new BasicStroke(2f));
			lblVic.get(i).setSize(85, 73);
			lblVic.get(i).setLocation(416, 100 + i*80);
			
			lblName.add(new JLabel2D(""+players.get(i).getUsername(), SwingConstants.LEFT));
			lblName.get(i).setFont(new Font("Arial", Font.PLAIN, 28));
			lblName.get(i).setForeground((i==0) ? Color.GREEN : Color.WHITE);
			lblName.get(i).setOutlineColor(Color.BLACK);
			lblName.get(i).setStroke(new BasicStroke(2f));
			lblName.get(i).setSize(800, 73);
			lblName.get(i).setLocation(516, 100 + i*80);
			
			add(lblVic.get(i));
			add(lblImg.get(i));
			add(lblName.get(i));
		}
		setVisible(true);
	}
	
//	public static void main(String args[]) {
//		Images.run();
//		JFrame window = new JFrame("GameOver");
//		window.setSize(1280, 860);
//		GameOverPanel gameover = new GameOverPanel();
//		window.add(gameover);
//		ArrayList<Player> l = new ArrayList<Player>();
//		for(int i = 0; i < 7; i++) {
//			l.add(new Player("I AM SO COOL OMGOMGOMGOMGOMG", i));
//			l.get(i).addVictoryPoints((int)(Math.random()*100));
//		}
//		gameover.showGUI(l);
//		window.setVisible(true);
//	}
}