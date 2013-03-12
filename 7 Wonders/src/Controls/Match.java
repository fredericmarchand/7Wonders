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
					 //choose resource
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
				  endOfTurnSpecialEffects(players);
			}
			PlayerInteraction.SettleMilitaryConflicts(players, age);
		}
		endOfGameSpecialEffects(players);
		//count victory points
		
		in.close();
	}
	
	
	public void resourceChoiceActivation(Structure s, Player p)
	{
		Scanner in = new Scanner(System.in);
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getType() == ResourceChoice.ResourceChoiceID )
			{
				System.out.println("The card " + s.getName() + " allows you to select one of the following resources to be available for the turn:");
				
				String cmd = in.nextLine();
				((ResourceChoice)se).chooseResource(Integer.parseInt(cmd));
				//add resource to players resources
			}
		}
		in.close();
	}
	
	public void beginningOfTurnEffects(ArrayList<Player> plyrs)
	{
		for ( Player p: plyrs )
		{
			for ( Structure s : p.getWonderBoard().getYellowCards() )
				resourceChoiceActivation(s, p);
						
			for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
				resourceChoiceActivation(s, p);
		}
	}
	
	public void cardCoinBonusActivation(Structure s, Player p)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getType() == CardCoinBonus.CardCoinBonusID )
				((CardCoinBonus)se).acquireCoins(p, getLeftNeighbor(p), getRightNeighbor(p));
		}
	}
	
	public void endOfTurnSpecialEffects(ArrayList<Player> plyrs)
	{
		for ( Player p: plyrs )
		{
			for ( Structure s : p.getWonderBoard().getRedCards() )
				cardCoinBonusActivation(s, p);
			
			for ( Structure s : p.getWonderBoard().getBlueCards() )
				cardCoinBonusActivation(s, p);
			
			for ( Structure s : p.getWonderBoard().getYellowCards() )
				cardCoinBonusActivation(s, p);
			
			for ( Structure s : p.getWonderBoard().getPurpleCards() )
				cardCoinBonusActivation(s, p);
			
			for ( Structure s : p.getWonderBoard().getGreenCards() )
				cardCoinBonusActivation(s, p);
			
			for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
				cardCoinBonusActivation(s, p);
		}
	}

	public void addPointActivate(Structure s, Player p)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			switch ( se.getType() )
			{
				case CardVictoryPointBonus.CardVictoryPointBonusID:
				((CardVictoryPointBonus)se).acquireVictoryPoints(p, getLeftNeighbor(p), getRightNeighbor(p));
				break;
				
				case WonderStageVictoryPointBonus.WonderStageVictoryPointBonusID:
				((WonderStageVictoryPointBonus)se).acquirePoints(p, getLeftNeighbor(p), getRightNeighbor(p));
				break;
				
				case MilitaryDefeatBonus.MilitaryDefeatBonusID:
				((MilitaryDefeatBonus)se).acquireVictoryPoints(p, getLeftNeighbor(p), getRightNeighbor(p));
				break;
			}
		}
	}
	
	public void endOfGameSpecialEffects(ArrayList<Player> plyrs)
	{
		for ( Player p : plyrs )
		{
			for ( Structure s : p.getWonderBoard().getRedCards() )
				addPointActivate(s, p);
			
			for ( Structure s : p.getWonderBoard().getBlueCards() )
				addPointActivate(s, p);
			
			for ( Structure s : p.getWonderBoard().getYellowCards() )
				addPointActivate(s, p);
			
			for ( Structure s : p.getWonderBoard().getPurpleCards() )
				addPointActivate(s, p);
			
			for ( Structure s : p.getWonderBoard().getGreenCards() )
				addPointActivate(s, p);
			
			for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
				addPointActivate(s, p);
			
		}
	}
	
	public Player getPlayerByID(int id)
	{
		for ( Player p : players )
		{
			if ( p.getID() == id )
				return p;
		}
		return null;
	}
	
	public Player getLeftNeighbor(Player p)
	{
		for ( int i = 0; i < players.size(); ++i )
		{
			if ( players.get(i).getID() == p.getID() )
			{
				if ( i == 0 )
					return players.get(players.size()-1);
				else return players.get(i-1);
			}
		}
		return null;
	}
	
	public Player getRightNeighbor(Player p)
	{
		for ( int i = 0; i < players.size(); ++i )
		{
			if ( players.get(i).getID() == p.getID() )
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
