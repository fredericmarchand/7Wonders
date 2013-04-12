package Controls;

import java.util.ArrayList;
import Player.Player;

public class PlayerInteraction {
	
	public PlayerInteraction()
	{
	}
	
	//handles the conflict between two players in the given age
	public static void SettleMilitaryConflict(Player p1, Player p2, int age)
	{
			if ( p1.getShields() > p2.getShields() )
			{
				if ( age == 1 ) p1.getConflictTokens().addOneTokens(1);
				if ( age == 2 ) p1.getConflictTokens().addThreeTokens(1);
				if ( age == 3 ) p1.getConflictTokens().addFiveTokens(1);
				System.out.println(p1.getUsername() + " " + p1.getConflictTokens().getFiveTokens());
				p2.getConflictTokens().addMinusOneTokens(1);
			}
			else if ( p1.getShields() < p2.getShields() )
			{
				if ( age == 1 ) p2.getConflictTokens().addOneTokens(1);
				if ( age == 2 ) p2.getConflictTokens().addThreeTokens(1);
				if ( age == 3 ) p2.getConflictTokens().addFiveTokens(1);
				System.out.println(p2.getUsername() + " " + p2.getConflictTokens().getFiveTokens());
				p1.getConflictTokens().addMinusOneTokens(1);
			}
	}
	
	//handles the military conflicts for every player in the match for the given age
	public static void SettleMilitaryConflicts(ArrayList<Player> players, int age)
	{
		for ( int i = 0; i < players.size()-1; ++i )
		{
			SettleMilitaryConflict(players.get(i), players.get(i+1), age);
		}
		SettleMilitaryConflict(players.get(0), players.get(players.size()-1), age);
	}

	
	public static void main (String args[])
	{
		java.util.Random rand = new java.util.Random();
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		
		for ( int i = 0; i < players.size(); ++i )
		{
			players.get(i).addShields(rand.nextInt());
		}
		SettleMilitaryConflicts(players, 1);
		SettleMilitaryConflicts(players, 2);
		SettleMilitaryConflicts(players, 3);
		for ( int i = 0; i < players.size(); ++i )
		{
			System.out.println(players.get(i).getConflictTokens().getTotal());
		}
	}
}
