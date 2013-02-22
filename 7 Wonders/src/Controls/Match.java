package Controls;

import java.util.ArrayList;
import Player.Player;

public class Match {

	
	@SuppressWarnings("unused")
	private ArrayList<Player> players;
	@SuppressWarnings("unused")
	private int age, turn;
	
	
	public Match()
	{
		players = new ArrayList<Player>();
		age = 0;
		turn = 0;
	}
	
	public static void RunGame()
	{
		@SuppressWarnings("unused")
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
