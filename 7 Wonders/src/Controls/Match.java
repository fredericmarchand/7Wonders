package Controls;

import java.util.ArrayList;
import Player.Player;

public class Match {

	private ArrayList<Player> players;
	private int age, turn;
	
	
	public Match()
	{
		players = new ArrayList<Player>();
		age = 0;
		turn = 0;
	}
	
	public static void RunGame()
	{
		Match newMatch = new Match();
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunGame();
	}

}
