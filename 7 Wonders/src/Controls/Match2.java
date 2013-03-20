package Controls;

import java.util.ArrayList;
import Player.Player;
import Player.User;
import Structures.Effects.*;
import Structures.Structure;
import Tokens.Resources;

import java.util.Scanner;

public class Match2 {
	
	private ArrayList<Player> players;
	private int age, turn, numPlayers;
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
	private Scanner in;
		
	//server side constructor
	public Match2(ArrayList<User> users)
	{
		players = new ArrayList<Player>();
		for ( User u: users )
		{
			players.add(new Player(u.getUsername(), u.getID()));
		}
		age = 1;
		turn = 1;
		this.numPlayers = players.size();
		in = new Scanner(System.in);
		age1Deck = CardHandler.BuildAge1Deck(numPlayers);
		age2Deck = CardHandler.BuildAge2Deck(numPlayers);
		age3Deck = CardHandler.BuildAge3Deck(numPlayers);
		discarded = new ArrayList<Structure>();
		
		CardHandler.DistributeRandomWonderBoards(players);
		for ( Player p: players )
		{
			p.getOwnedResources().addCoins(3);
			addInitialResources(players);
		}
		CardHandler.DistributeCards(players, age1Deck);
	}
	
	//client side constructor
	public Match2()
	{
		players = new ArrayList<Player>();
		age = 1;
		turn = 1;
		in = new Scanner(System.in);
		age1Deck = null;
		age2Deck = null;
		age3Deck = null;
		discarded = new ArrayList<Structure>();
	}
	
	public ArrayList<Structure> getDiscardedCards()
	{
		return discarded;
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
	
	//server side
	public void addInitialResources(ArrayList<Player> plyrs)
	{
		for ( Player p: plyrs )
		{
			p.getWonderBoard().getStartResource().acquireResources(p);
		}
	}
	
	//beginning of turn effects
	public void beginningOfTurnEffects(ArrayList<CommandMessage> messages)
	{
		for ( CommandMessage msg: messages)
		{
			Player p = getPlayerByID(msg.getPlayerID());
			for ( Structure s : p.getWonderBoard().getYellowCards() )
			{
				serverHandleBeginningOfTurnEffects(p.getUnvResources(), s, msg);
			}
							
			for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
			{
				serverHandleBeginningOfTurnEffects(p.getExtraResources(), s, msg);
			}
		}		
	}
	
	public void serverHandleBeginningOfTurnEffects(Resources r, Structure s, CommandMessage msg)
	{
		int index = 0;
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getID() == ResourceChoice.ResourceChoiceID )
			{
				ResourceChoice rc = ((ResourceChoice)se);
				if ( msg.getResourceChoices().get(index) == 1 )
					r.addResources(new Resources(1, 0, 0, 0, 0, 0, 0, 0));
				else if ( msg.getResourceChoices().get(index) == 2 )
					r.addResources(new Resources(0, 1, 0, 0, 0, 0, 0, 0));
				else if ( msg.getResourceChoices().get(index) == 3 )
					r.addResources(new Resources(0, 0, 1, 0, 0, 0, 0, 0));
				else if ( msg.getResourceChoices().get(index) == 4 )
					r.addResources(new Resources(0, 0, 0, 1, 0, 0, 0, 0));
				else if ( msg.getResourceChoices().get(index) == 5 )
					r.addResources(new Resources(0, 0, 0, 0, 1, 0, 0, 0));
				else if ( msg.getResourceChoices().get(index) == 6 )
					r.addResources(new Resources(0, 0, 0, 0, 0, 1, 0, 0));
				else if ( msg.getResourceChoices().get(index) == 7 )
					r.addResources(new Resources(0, 0, 0, 0, 0, 0, 1, 0));
				++index;
			}
		}
	}
	
	
	//server side run turns if message typeid == move
	public void runTurns(ArrayList<CommandMessage> messages)
	{		
		for ( CommandMessage msg: messages )
		{
			Player p = getPlayerByID(msg.getPlayerID());
			switch ( msg.getAction() )
			{
				case 1:
					if (msg.getPreference() == -1 ) p.buildStructure();
					else p.buildStructure(getLeftNeighbor(p), getRightNeighbor(p), msg.getPreference());
					break;
				case 2:
					if (msg.getPreference() == -1 ) p.buildStage();
					else p.buildStage(getLeftNeighbor(p), getRightNeighbor(p), msg.getPreference());
					break;
				case 3:
					p.discard(discarded);
					break;
			}
		}
		
		for ( Player p : players ) p.resetResources();
		CardHandler.PassCardsToNeighbors(players, age);
		endOfTurnSpecialEffects(players);
		
		if ( turn == 6 )
		{
			PlayerInteraction.SettleMilitaryConflicts(players, age);
			++age;
			turn = 1;
			if ( age == 2 ) CardHandler.DistributeCards(players, age2Deck);
			if ( age == 3 ) CardHandler.DistributeCards(players, age3Deck);
		}
		else ++turn;

		/*endOfGameSpecialEffects(players);
		countPlayersVictoryPoints();*/
	}
	
	public void cardCoinBonusActivation(Structure s, Player p)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getID() == CardCoinBonus.CardCoinBonusID )
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
	
	public void countPlayersVictoryPoints()
	{	
		for (Player p : players){
			//Conflict Tokens
			p.addVictoryPoints(p.getConflictTokens().getVictoryPoints());
			
			//Coins
			p.addVictoryPoints(p.getResources().getCoins() / 3);
			
			//Scientific
			p.addVictoryPoints(p.getScientificSymbols().victoryPointsValue());
		}
	}
	
	//Server side end of game effects
		public void endOfGameSpecialEffects(ArrayList<Player> plyrs, ArrayList<CommandMessage> messages)
		{
			for ( Player p : plyrs )
			{
				for ( Structure s : p.getWonderBoard().getRedCards() )
					addPointActivate(s, p, messages);
				
				for ( Structure s : p.getWonderBoard().getBlueCards() )
					addPointActivate(s, p, messages);
				
				for ( Structure s : p.getWonderBoard().getYellowCards() )
					addPointActivate(s, p, messages);
				
				for ( Structure s : p.getWonderBoard().getPurpleCards() )
					addPointActivate(s, p, messages);
				
				for ( Structure s : p.getWonderBoard().getGreenCards() )
					addPointActivate(s, p, messages);
				
				for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
					addPointActivate(s, p, messages);
			}
		}

		public void addPointActivate(Structure s, Player p, ArrayList<CommandMessage> messages)
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
							for ( CommandMessage msg: messages )
							{
								if ( p.getID() == msg.getPlayerID() )
									((ScientificSymbolBonus)se).chooseSymbol(p, msg.getScientificSymbol());
							}
						}
						break;
				}
			}
		}
		//server side endof game effects
	//server methods
	
		
		
	//client moves
		
	public ArrayList<Integer> resourceChoiceActivation(Structure s, Player p)
	{
		ArrayList<Integer> choices = new ArrayList<Integer>();
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
				
				while ( !in.hasNext() );
				int cmd = in.nextInt();
				choices.add(cmd);
			}
		}
		return choices;
	}
	
	public CommandMessage handleResourceChoices(Player p)
	{
		ArrayList<Integer> choices = new ArrayList<Integer>();
		for ( Structure s : p.getWonderBoard().getYellowCards() )
			choices.addAll(resourceChoiceActivation(s, p));
						
		for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
			choices.addAll(resourceChoiceActivation(s, p));
		CommandMessage msg = new CommandMessage(p.getID(), CommandMessage.RESOURCE_CHOICE_TYPE, -1, -1, -1, -1, choices);
		return msg;
	}
	
	public CommandMessage initMove(Player p)
	{
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(p.getID());
		msg.setMsgType(CommandMessage.MOVE_TYPE);
		System.out.println("Please input the ID of the card you want to play: ");
		int l = 0;
		for ( Structure s: p.getCards() ) 
		{
			System.out.println(l++ + " " + s.getName());
		}
		while ( !in.hasNext() );
		int move = in.nextInt();
		msg.setCardID(move);
		p.chooseCard(move);
		msg.setCardID(move);
		return clientActionPhase(p, msg);
	}
	
	public CommandMessage clientActionPhase(Player p, CommandMessage msg)
	{
		System.out.println("Please select your move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
		while ( !in.hasNext() );
		int move = in.nextInt();
		msg.setAction(move);
		int ans = move;
		int retv = -1;
		switch ( move )
		 {
		 	case 1:
		 		retv = p.canBuild(getLeftNeighbor(p), getRightNeighbor(p));
		 		
		 		if ( retv == 1 )
		 		{
		  			System.out.println("You have buy resources from neighbors to be able to build this structure. Which neighbor do you prefer to do business with?\n0 - left\n1 - right\n2 - doesn't matter");
		  			while ( !in.hasNext() );
		  			int neib = in.nextInt();
		  			msg.setPreference(neib);	
		 		}
		 		else if ( retv == 0 )
		 		{
	  				while ( ans == move )
	  				{
	  					System.out.println("You are not able to build this sturcture, please choose a different move:\n");
	  					while ( !in.hasNext() );
	  					ans = in.nextInt();
	  				}
	  				msg.setAction(-1);
	  				msg = clientActionPhase(p, msg);
		 		}

		  		break;
		  	case 2:
		  		retv = p.canBuildStage(getLeftNeighbor(p), getRightNeighbor(p));
		  		if ( retv == 1 )
		  		{
		  			System.out.println("You have buy resources from neighbors to be able to build this stage. Which neighbor do you prefer to do business with?\n0 - left\n1 - right\n2 - doesn't matter");
		  			while ( !in.hasNext() );
		  			int neib = in.nextInt();
		  			msg.setPreference(neib);
		  		}
		  		else if ( retv == 0 )
	  			{
	  				while ( ans == move )
	  				{
	  					System.out.println("You are not able to build this sturcture, please choose a different move:");
	  					while ( !in.hasNext() );
	  					ans = in.nextInt();
	  				}
	  				msg.setAction(-1);
	  				msg = clientActionPhase(p, msg);
	  			}
		  		break;
		  	case 3:
		  		msg.setAction(3);
		  		break;
		  	default: 
		  		System.out.println("Invalid... Please try again: ");
		  		msg = clientActionPhase(p, msg);
		 }
		return msg;
	}
	
	
	//client side end of game special effects
	public CommandMessage endOfGameSpecialEffects(Player p)
	{
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(p.getID());
		for ( Structure s : p.getWonderBoard().getPurpleCards() )
			addPointActivate(s, p, msg);
		return msg;
	}

	public void addPointActivate(Structure s, Player p, CommandMessage msg)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
			{
				if ( ((ScientificSymbolBonus)se).canChoose() )
				{
					System.out.println("You now can select which scientific symbol you want your guild will provide:\n1 - Compass\n2 - Gear\n3 - Tablet");
					while ( !in.hasNext() );
					int ans = in.nextInt();
					msg.setScientificSymbol(ans);
				}
			}
		}
	}
	//client side end of game special effects
	
	//client moves
	
	
	
	
	//get specific players functions
	public Player getPlayerByID(long id)
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

	}

}
