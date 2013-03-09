package Controls;

import java.util.ArrayList;
import Player.Player;
import Structures.Structure;

public class Match {
	
	
	@SuppressWarnings("unused")
	private ArrayList<Player> players;
	@SuppressWarnings("unused")
	private int age, turn;
	@SuppressWarnings("unused")
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
	
	
	public Match()
	{
		players = new ArrayList<Player>();
		age = 0;
		turn = 0;
		age1Deck = null;
		age2Deck = null;
		age3Deck = null;
		discarded = null;
	}
	
	public static void runGame()
	{
		@SuppressWarnings("unused")
		Match newMatch = new Match();
		
		/*
		void runMatch()
		{
			add players to match
			random wonderboard assignment
			//3 ages
			for ( int age = 0; age < 3; ++age )
			{
				distributeCards(age)
				//6 turns
				for ( int i = 0; i < 6; ++i )
				{
					 //each player makes their moves
					 for ( Player p: players )
					 {
					 	select card
					  	if ( i == 5 ) discard remaining card
					  	action phase
					  	pass cards to neighbor
					  	
					  	//push match to server
					  }
				}
				settlemilitaryconflicts
			}
			countvictorypoints
		}
				  
		*/	  
		
	}
	
	public void addCardToDiscardedPile(Structure card)
	{
		discarded.add(card);
		card = null;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runGame();
	}

}
