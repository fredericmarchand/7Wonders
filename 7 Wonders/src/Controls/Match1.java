package Controls;

import java.util.ArrayList;

import Player.AIPlayer;
import Player.Player;
import Structures.Effects.*;
import Structures.Structure;
import WonderBoards.WonderBoardStage;

import java.util.Scanner;
import java.util.Random;

public class Match1 {
	
	public static final int START_OF_TURN = 0;
	public static final int TURN = 1;
	public static final int END_OF_GAME = 2;
	
	private ArrayList<Player> players;
	private int age, turn, numPlayers;
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
		
	
	public Match1(int numPlayers)
	{
		players = new ArrayList<Player>();
		age = 1;
		turn = 1;
		this.numPlayers = numPlayers;
		//in = new Scanner(System.in);
		age1Deck = CardHandler.BuildAge1Deck(numPlayers);
		age2Deck = CardHandler.BuildAge2Deck(numPlayers);
		age3Deck = CardHandler.BuildAge3Deck(numPlayers);
		discarded = new ArrayList<Structure>();
		
		//this will be removed
		for ( int i = 0; i < numPlayers; ++i )
		{
			players.add(new Player("Player " + i, i));
		}
		CardHandler.DistributeRandomWonderBoards(players);
		for ( Player p: players )
		{
			p.getOwnedResources().addCoins(3);
		}
		CardHandler.DistributeCards(players, age1Deck);
		
	}
	
	public Match1()
	{
		players = new ArrayList<Player>();
		age = 1;
		turn = 1;
		
		
	}
	
	public void init() {
		numPlayers = players.size();
		age1Deck = CardHandler.BuildAge1Deck(numPlayers);
		age2Deck = CardHandler.BuildAge2Deck(numPlayers);
		age3Deck = CardHandler.BuildAge3Deck(numPlayers);
		discarded = new ArrayList<Structure>();
		CardHandler.DistributeRandomWonderBoards(players);
		for ( Player p: players ) p.getOwnedResources().addCoins(3);
		CardHandler.DistributeCards(players, age1Deck);
		addInitialResources(players);
		//Added to give players knowledge of the current age for AI choices
		for (Player p : players)
		{
			p.initAge();
		}
	}
	
	public void addLocalPlayer(Player p) {
		players.add(p);
	}
	
	public void fillWithAI() {
		for (int i = players.size(); i < 7; i++)
			players.add(new AIPlayer());
	}
	
	public ArrayList<Structure> getDiscardedCards()
	{
		return discarded;
	}
	
	//public int getState()
	//{
	//	return state;
	//}
	
	//public void setState(int newstate)
	//{
	//	state = newstate;
	//}
	
	public int getAge()
	{
		return age;
	}
	
	public int getTurn()
	{
		return turn;
	}
	
	public void setAge(int a)
	{
		age = a;
	}
	
	public void setTurn(int t)
	{
		turn = t;
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public ArrayList<Structure> getDeck()
	{
		switch ( age )
		{
			case 1: return age1Deck;
				
			case 2: return age2Deck;
				
			case 3: return age3Deck;
		}
		return null;
	}
	
	/*public void runGame()
	{
		players = new ArrayList<Player>();
		Random r = new Random();
		
		for ( int i = 0; i < numPlayers; ++i )
		{
			System.out.println("Please input a username for player " + (i + 1) + ": ");
			String un = in.nextLine();
			players.add(new Player(un, (i * r.nextInt())));
		}
		CardHandler.DistributeRandomWonderBoards(players);
		for ( Player p: players ) p.getOwnedResources().addCoins(3);
		
		for ( age = 1; age < 4; ++age )
		{
			System.out.println("\nAge " + age + "\n");
			if ( age == 1 )      CardHandler.DistributeCards(players, age1Deck);
			else if ( age == 2 ) CardHandler.DistributeCards(players, age2Deck);
			else if ( age == 3 ) CardHandler.DistributeCards(players, age3Deck);
			
			for ( turn = 1; turn < 7; ++turn )
			{
				 //each player makes their moves
				 for ( Player p: players )
				 {
					 beginningOfTurnEffects(p);
					 System.out.println("Its your turn " + p.getUsername() + ", please input the ID of the card you want to play: ");
					 int l = 0;
					 for ( Structure s: p.getCards() ) 
					 {
					 	System.out.println(l++ + " " + s.getName());
					 }
					 while ( !in.hasNext() );
					 int move = in.nextInt();
					 p.chooseCard(move);
					 if ( p.getCards().size() == 1 ) p.discardHand(discarded);
					 System.out.println("Please select your move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
					 while ( !in.hasNext() );
					 move = in.nextInt();
					 //action phase
					 actionPhase(p, move);
				  }
				 for ( Player p : players ) p.resetResources();
				 CardHandler.PassCardsToNeighbors(players, age);
				 endOfTurnSpecialEffects(players);
			}
			PlayerInteraction.SettleMilitaryConflicts(players, age);
		}
		endOfGameSpecialEffects(players);
		//count victory points
		
		in.close();
	}
	
	public int actionPhase(Player p, int move)
	{
		switch ( move )
		 {
		 	case 1:
		 		int retv = p.buildStructure();
		 		if ( retv == 1 )
		 		{
		 			if ( p.neighborsHaveResources(getLeftNeighbor(p), getRightNeighbor(p), p.getChosenCard().getResourceCost()) )
		  			{
		  				System.out.println("You have buy resources from neighbors to be able to build this structure. Which neighbor do you prefer to do business with?\n0 - left\n1 - right\n2 - doesn't matter");
		  				while ( !in.hasNext() );
		  				int neib = in.nextInt();
		  				p.buildStage(getLeftNeighbor(p), getRightNeighbor(p), neib);
		  			}
		 			else
		 			{
		 				int ans = move;
		  				while ( ans == move )
		  				{
		  					System.out.println("You are not able to build this sturcture, please choose a different move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
		  					while ( !in.hasNext() );
		  					ans = in.nextInt();
		  				}
		  				actionPhase(p, ans);
		 			}
		 		}
		 		else if ( retv == 0 )
		 		{
		 			int ans = move;
	  				while ( ans == move )
	  				{
	  					System.out.println("You are not able to build this sturcture, please choose a different move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
	  					while ( !in.hasNext() );
	  					ans = in.nextInt();
	  				}
	  				actionPhase(p, ans);
		 		}
		  		break;
		  	case 2:
		  		if ( !p.buildStage() )
		  		{
		  			if ( p.neighborsHaveResources(getLeftNeighbor(p), getRightNeighbor(p), p.getWonderBoard().getNextStageCost()) )
		  			{
		  				System.out.println("You have buy resources from neighbors to be able to build this stage. Which neighbor do you prefer to do business with?\n0 - left\n1 - right\n2 - doesn't matter");
		  				while ( !in.hasNext() );
		  				int neib = in.nextInt();
		  				p.buildStage(getLeftNeighbor(p), getRightNeighbor(p), neib);
		  			}
		  			else
		  			{
		  				int ans = move;
		  				while ( ans == move )
		  				{
		  					System.out.println("You are not able to build this stage, please choose a different move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
		  					while ( !in.hasNext() );
		  					ans = in.nextInt();
		  				}
		  				actionPhase(p, ans);
		  			}
		  		}
		  		else
	  			{
	  				int ans = move;
	  				while ( ans == move )
	  				{
	  					System.out.println("You are not able to build this sturcture, please choose a different move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
	  					while ( !in.hasNext() );
	  					ans = in.nextInt();
	  				}
	  				actionPhase(p, ans);
	  			}
		  		break;
		  	case 3:
		  		p.discard(discarded);
		  		break;
		  	default: System.out.println("Invalid... Please try again: ");
		 }
		return move;
	}*/
	
	public void addInitialResources(ArrayList<Player> plyrs)
	{
		for ( Player p: plyrs )
		{
			p.getWonderBoard().getStartResource().acquireResources(p);
		}
	}
	
	public void resourceChoiceActivation(Structure s, Player p)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getID() == ResourceChoice.ResourceChoiceID )
			{
				ResourceChoice rc = ((ResourceChoice)se);
				System.out.println("The card " + s.getName() + " allows you to select one of the following resources to be available for the turn:\n"+
									( rc.getPossibilities().getOre() != 0 ? "1 - Ore\n" : "" ) +
									( rc.getPossibilities().getStone() != 0 ? "2 - Stone\n" : "" ) +
									( rc.getPossibilities().getWood() != 0 ? "3 - Wood\n" : "" ) +
									( rc.getPossibilities().getClay() != 0 ? "4 - Clay\n" : "" ) +
									( rc.getPossibilities().getGlass() != 0 ? "5 - Glass\n" : "" ) +
									( rc.getPossibilities().getLoom() != 0 ? "6 - Loom\n" : "" ) +
									( rc.getPossibilities().getPapyrus() != 0 ? "7 - Papyrus\n" : "" ));
				
				//while ( !in.hasNext() );
				//int cmd = in.nextInt();
				//((ResourceChoice)se).chooseResource(cmd, p);
			}
		}
	}
	
	public void beginningOfTurnEffects(Player p)
	{
		for ( Structure s : p.getWonderBoard().getYellowCards() )
			resourceChoiceActivation(s, p);
						
		for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
			resourceChoiceActivation(s, p);
	}
	
	public void cardCoinBonusActivation(Structure s, Player p)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getID() == CardCoinBonus.CardCoinBonusID )
				((CardCoinBonus)se).acquireCoins(p, getLeftNeighbor(p), getRightNeighbor(p));
		}
	}
	
	//used in gui controller
	public void playerEndOfTurnSpecialEffects(Player user)
	{
		for ( Structure s : user.getWonderBoard().getRedCards() )
			cardCoinBonusActivation(s, user);
		
		for ( Structure s : user.getWonderBoard().getBlueCards() )
			cardCoinBonusActivation(s, user);
		
		for ( Structure s : user.getWonderBoard().getYellowCards() )
			cardCoinBonusActivation(s, user);
		
		for ( Structure s : user.getWonderBoard().getPurpleCards() )
			cardCoinBonusActivation(s, user);
		
		for ( Structure s : user.getWonderBoard().getGreenCards() )
			cardCoinBonusActivation(s, user);
		
		for ( Structure s : user.getWonderBoard().getBrownGreyCards() )
			cardCoinBonusActivation(s, user);
	}
	//
	
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
			switch ( se.getID() )
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
			
				case ScientificSymbolBonus.ScientificSymbolBonusID:
					if ( ((ScientificSymbolBonus)se).canChoose() )
					{
						//System.out.println("You now can select which scientific symbol you want your guild to represent:\n1 - Compass\n2 - Gear\n3 - Tablet");
						//while ( !in.hasNext() );
						//String ans = in.nextLine();
						//((ScientificSymbolBonus)se).chooseSymbol(p, Integer.parseInt(ans));
					}
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
		for ( int i = 0; i < players.size(); --i )
		{
			if ( players.get(i).getID() == p.getID() )
			{
				if ( i == players.size()-1 )
					return players.get(0);
				else return players.get(i+1);
			}
		}
		return null;
	}
	
	public void countPlayersVictoryPoints()
	{	
		for (Player p : players){
			
			//Conflict Tokens
			p.addVictoryPoints(p.getConflictTokens().getVictoryPoints());
			
			//Coins
			p.addVictoryPoints((int)Math.floor(p.getResources().getCoins() / 3));
			
			//Scientific
			p.addVictoryPoints(p.getScientificSymbols().victoryPointsValue());
		}
		
	}
	
	
	
	
	public void endOfTurn()
	{
		CardHandler.PassCardsToNeighbors(getPlayers(), getAge());
		for ( Player p : players ) p.resetResources();
		if ( getTurn() == 6 )
		{
			for ( Player p: players )
			{
				for ( WonderBoardStage stg: p.getWonderBoard().getStages() )
				{
					if ( stg.isBuilt() )
					{
						for ( SpecialEffect se: stg.getEffects() )
						{
							if ( se.getID() == FreeConstruction.FreeConstructionID )
							{
								se.reset();
							}
						}
					}
				}
			}
			
			
			PlayerInteraction.SettleMilitaryConflicts(getPlayers(), getAge());
			setAge(getAge()+1);
			incPlayerAges();
			setTurn(1);
			if ( getAge() == 2 ) CardHandler.DistributeCards(getPlayers(), getDeck());
			if ( getAge() == 3 ) CardHandler.DistributeCards(getPlayers(), getDeck());
			if ( getAge() == 4 ) 
			{
				endOfGameSpecialEffects(players);
				countPlayersVictoryPoints();
			}
		}
		else 
		{
			turn += 1;
		}
	}
	
	
	public void incPlayerAges()
	{
		for (Player p : players)
		{
			p.nextAge();
		}
	}
	
	public void handleAIPlayerMoves()
	{
		for ( Player p : players )
		{
			if ( p.ai() )
			{
				((AIPlayer)p).pickCard(discarded, getLeftNeighbor(p), getRightNeighbor(p));
			}

		}
	}
	
	
	public void discardAllPlayersCards()
	{
		for ( Player p : players )
		{
			p.discardHand(discarded);
		}
	}
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Match1 m = new Match1(3);
		//m.runGame();
	}

}
