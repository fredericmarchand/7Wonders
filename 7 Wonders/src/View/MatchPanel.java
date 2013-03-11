package View;

import java.util.ArrayList;

import javax.swing.*;

import Player.Player;
import WonderBoards.WonderBoard;

public class MatchPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ArrayList<Player> players;
	
	public MatchPanel(int numPlayers) {
		setLayout(null);
		setSize(1296, 749);
		
		players = new ArrayList<Player>();
		
		for(int i = 0; i < numPlayers; i++) {
			players.add(new Player("", i));
		}
		
		players.get(1).assignWonderBoard(new WonderBoard(2, 1));
		players.get(2).assignWonderBoard(new WonderBoard(3, 2));
		players.get(3).assignWonderBoard(new WonderBoard(4, 1));
		players.get(4).assignWonderBoard(new WonderBoard(5, 2));
		players.get(5).assignWonderBoard(new WonderBoard(6, 1));
		players.get(6).assignWonderBoard(new WonderBoard(7, 2));
		
		NeighbourPanel n1 = new NeighbourPanel(players.get(1));
		n1.setLocation(0, 150);
		NeighbourPanel n2 = new NeighbourPanel(players.get(2));
		n2.setLocation(802, 150);
		
		ForeignPanel f1 = new ForeignPanel(players.get(3));
		f1.setLocation(0, 0);
		ForeignPanel f2 = new ForeignPanel(players.get(4));
		f2.setLocation(320, 0);
		ForeignPanel f3 = new ForeignPanel(players.get(5));
		f3.setLocation(640, 0);
		ForeignPanel f4 = new ForeignPanel(players.get(6));
		f4.setLocation(960, 0);
		
		add(n1);
		add(n2);
		add(f1);
		add(f2);
		add(f3);
		add(f4);
	}
	
	
}
