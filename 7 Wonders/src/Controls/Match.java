package Controls;

import java.util.ArrayList;
import Player.Player;
import Structures.Effects.*;
import Structures.Structure;
import java.util.Scanner;
import java.util.Random;

public class Match {
	
	private ArrayList<Player> players;
	private int age, turn, numPlayers;
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
	
	
	public Match(int numPlayers)
	{
		players = new ArrayList<Player>();
		age = 0;
		turn = 0;
		this.numPlayers = numPlayers;
		age1Deck = CardHandler.BuildAge1Deck(numPlayers);
		age2Deck = CardHandler.BuildAge2Deck(numPlayers);
		age3Deck = CardHandler.BuildAge3Deck(numPlayers);
		discarded = new ArrayList<Structure>();
		
		//this will be removed
		for ( int i = 0; i < numPlayers; ++i )
		{
			players.add(new Player("Player " + i, i));
		}
		CardHandler.DistributeRandomWonderBoards(players, 0);
		for ( Player p: players )
		{
			p.getResources().addCoins(3);
		}
		CardHandler.DistributeCards(players, age1Deck);
		
	}
	
	public int getAge()
	{
		return age;
	}
	
	public int getTurn()
	{
		return turn;
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public void runGame()
	{
		players = new ArrayList<Player>();
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		
		//System.out.println("Please input how many players will play: ");
		//int numPlayers = (int)Integer.parseInt(in.nextLine());
		
		for ( int i = 0; i < numPlayers; ++i )
		{
			System.out.println("Please input a username for player " + (i + 1) + ": ");
			String un = in.nextLine();
			players.add(new Player(un, (i * r.nextInt())));
		}
		CardHandler.DistributeRandomWonderBoards(players, 0);
		for ( Player p: players )
		{
			p.getResources().addCoins(3);
		}
		
		for ( age = 1; age < 4; ++age )
		{
			System.out.println("\nAge " + age + "\n");
			if ( age == 1 )      CardHandler.DistributeCards(players, age1Deck);
			else if ( age == 2 ) CardHandler.DistributeCards(players, age2Deck);
			else if ( age == 3 ) CardHandler.DistributeCards(players, age3Deck);
			
			for ( turn = 0; turn < 6; ++turn )
			{
				 //each player makes their moves
				 for ( Player p: players )
				 {
						
					 System.out.println("Its your turn " + p.getUsername() + ", please input the ID of the card you want to play: ");
					 int l = 0;
					 for ( Structure s: p.getCards() ) 
					 {
					 	System.out.println(l++ + " " + s.getName());
					 }
					 int move = Integer.parseInt(in.nextLine());
					 p.chooseCard(move);
					 if ( p.getCards().size() == 1 ) p.discardHand(discarded);
					 System.out.println("Please select your move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
					 move = Integer.parseInt(in.nextLine());
					 boolean valid = true;
					 switch ( move )
					 {
					 	case 1:
					 		valid = p.buildStructure();
					  		break;
					  	case 2:
					  		valid = p.buildStage();
					  		break;
					  	case 3:
					  		p.discard(discarded);
					  		break;
					  	default: System.out.println("Invalid... Please try again: ");
					 }
				  }
				  CardHandler.PassCardsToNeighbors(players, age);
			}
			PlayerInteraction.SettleMilitaryConflicts(players, age);
		}
		//count victory points
		
		
		in.close();
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
		
					  	//push match to server
					  }
					  pass cards to neighbor
				}
				settlemilitaryconflicts
			}
			countvictorypoints
		}
				  
		*/	  
		
	}
	
	/*void speffect()
	{
		Player p = new Player();
		for ( Structure s : p.getWonderBoard().getRedCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				switch ( se.getType() )
				{
				case CoinBonus.CoinBonusID:
					(CoinBonus)
					break;
				}
			}
		}
		for ( Structure s : p.getWonderBoard().getBlueCards() )
		{
			
		}
		for ( Structure s : p.getWonderBoard().getYellowCards() )
		{
			
		}
		for ( Structure s : p.getWonderBoard().getPurpleCards() )
		{
			
		}
		for ( Structure s : p.getWonderBoard().getGreenCards() )
		{
			
		}
		for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
		{
			
		}
	}*/
	
	public Player getPlayerByID(int id)
	{
		for ( Player p : players )
		{
			if ( p.getID() == id )
				return p;
		}
		return null;
	}
	
	public Player getLeftNeighbor(int localPlayerID)
	{
		for ( int i = 0; i < players.size(); ++i )
		{
			if ( players.get(i).getID() == localPlayerID )
			{
				if ( i == 0 )
					return players.get(players.size()-1);
				else return players.get(i-1);
			}
		}
		return null;
	}
	
	public Player getRightNeighbor(int localPlayerID)
	{
		for ( int i = 0; i < players.size(); ++i )
		{
			if ( players.get(i).getID() == localPlayerID )
			{
				if ( i == 0 )
					return players.get(players.size()-1);
				else return players.get(i-1);
			}
		}
		return null;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Match m = new Match(3);
		m.runGame();
	}

}
