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
		setSize(1280, 720);
		
		players = new ArrayList<Player>();
		
		for(int i = 0; i < numPlayers; i++) {
			players.add(new Player("", i));
		}
		
		players.get(1).assignWonderBoard(new WonderBoard(1, 1));
		players.get(2).assignWonderBoard(new WonderBoard(3, 2));
		
		NeighbourPanel n1 = new NeighbourPanel(players.get(1));
		n1.setLocation(0, 186);
		NeighbourPanel n2 = new NeighbourPanel(players.get(2));
		n2.setLocation(getWidth() - 478 - 16, 186);
		
		add(n1);
		add(n2);
	}
	
	
}
