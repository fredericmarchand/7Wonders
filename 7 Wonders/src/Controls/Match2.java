package Controls;

import java.util.ArrayList;
import java.util.Random;

import Structures.Cards.ScientistsGuild;
import Structures.Effects.*;
import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import WonderBoards.WonderBoardStage;
import Player.*;
public class Match2 {
	
	private ArrayList<Player> players;
	private int age, turn, numPlayers;
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
	private long localPlayerID;
	private boolean calcCalled;

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
		//numPlayers = players.size();
		//age1Deck = CardHandler.BuildAge1Deck(numPlayers);
		//age2Deck = CardHandler.BuildAge2Deck(numPlayers);
		//age3Deck = CardHandler.BuildAge3Deck(numPlayers);
		//discarded = new ArrayList<Structure>();
		
		//CardHandler.DistributeRandomWonderBoards(players);
		//for ( Player p: players )
		//	p.getOwnedResources().addCoins(3);
		//addInitialResources(players);
		//CardHandler.DistributeCards(players, age1Deck);
	}
	
	//client side constructor
	public Match2()
	{
		players = new ArrayList<Player>();
		age = 1;
		turn = 1;
		age1Deck = null;
		age2Deck = null;
		age3Deck = null;
		discarded = new ArrayList<Structure>();
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
		calcCalled = false;
		//for ( Player p: players ) p.getWonderBoard().buildStructure(new ScientistsGuild());
		//Added to give players knowledge of the current age for AI choices
//		for (Player p : players)
//		{
//			p.initAge();
//		}
	}
	
	public ArrayList<Object> getParameters()
	{
		ArrayList<Object> params = new ArrayList<Object>();
		//params.add(players);
		params.add(age);
		params.add(turn);
		params.add(numPlayers);
		//params.add(discarded);
		return params;
	}
	 
	public ArrayList<Integer> getDiscardedCardIDs()
	{
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(discarded.size());
		for ( Structure s: discarded )
		{
			ids.add(s.getID());
		}
		return ids;
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
	
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	public void setAge(int a)
	{
		age = a;
	}
	
	public void setTurn(int t)
	{
		turn = t;
	}
	
	public void setAge1Deck(ArrayList<Structure> deck)
	{
		age1Deck = deck;
	}
	
	public void setAge2Deck(ArrayList<Structure> deck)
	{
		age2Deck = deck;
	}
	
	public void setAge3Deck(ArrayList<Structure> deck)
	{
		age3Deck = deck;
	}
	
	public void setDiscardedDeck(ArrayList<Structure> deck)
	{
		discarded = deck;
	}
	
	public void setPlayers(ArrayList<Player> plrs)
	{
		players = plrs;
	}
	
	public void setNumPlayers(int plrs)
	{
		numPlayers = plrs;
	}
	
	public long getLocalPlayerID()
	{
		return localPlayerID;
	}
	public void setLocalPlayerID(long id)
	{
		localPlayerID = id;
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public void addPlayer(long id, String s)
	{
		players.add(new Player(s, id));
	}
	
	public void addPlayer(Player p)
	{
		players.add(p);
	}
	
	public void addPlayers(ArrayList<User> plyrs)
	{
		for ( User u: plyrs )
		{
			Player p = new Player(u.getUsername(), u.getID());
			players.add(p);
		}
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
	
	public void addAIPlayer(long id, String name, int strategy)
	{
		if (strategy == 0)
			players.add(new AIPlayer(id, name, new Simple()));
		else if (strategy == 1)
			players.add(new AIPlayer(id, name, new Moderate()));
		else
			players.add(new AIPlayer(id, name, new Intermediate()));
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
			ArrayList<Boolean> list = new ArrayList<Boolean>();
			for ( Structure s : p.getWonderBoard().getYellowCards() )
			{
				for ( SpecialEffect se: s.getEffects() )
				{
					if ( se.getID() == ResourceChoice.ResourceChoiceID )
					{
						list.add(true);
					}
				}
			}
								
			for ( Structure s : p.getWonderBoard().getBrownGreyCards() )
			{
				for ( SpecialEffect se: s.getEffects() )
				{
					if ( se.getID() == ResourceChoice.ResourceChoiceID )
					{
						list.add(false);
					}
				}
			}
			
			for ( WonderBoardStage stg: p.getWonderBoard().getStages() )
			{
				if ( stg.isBuilt() )
				{
					for ( SpecialEffect se: stg.getEffects() )
					{
						if ( se.getID() == ResourceChoice.ResourceChoiceID )
						{
							list.add(true);
						}
					}
				}
			}
			
			int index = 0;
			for ( Resources r : msg.getResourceChoices() )
			{
				if ( list.get(index++) )
					p.getUnvResources().addResources(r);
				else
					p.getExtraResources().addResources(r);
			}
		}		
	}
	
	//server side run turns if message typeid == move
	public void runTurns(ArrayList<CommandMessage> messages)
	{		

		for ( CommandMessage msg: messages )
		{
			Player p = getPlayerByID(msg.getPlayerID());
			p.chooseCardByID(msg.getCardID());
		}

		handleAIPlayerMoves();
		
		/*for ( Player p: getPlayers() )
		{
			 System.out.print("player i cards: [");
			 System.out.print(p.getChosenCard().getName());
			 //for (Structure s: p.getCards())
			 //{
			//	 System.out.print(s.getName() + ", ");
			 //}
			 System.out.println("]");
		}*/
		for ( CommandMessage msg: messages )
		{
			Player p = getPlayerByID(msg.getPlayerID());
			p.setFreePermission(msg.getFree() == 1 ? true : false);
			//p.chooseCardByID(msg.getCardID());
			//System.out.println("=================================================== " + i++ + "================================");
			switch ( msg.getAction() )
			{
				case 1:
					if ( msg.getPreference() == -1 ) p.buildStructure();
					else p.buildStructure(getLeftNeighbor(p), getRightNeighbor(p), msg.getPreference());
					break;
				case 2:
					if ( msg.getPreference() == -1 ) p.buildStage();
					else p.buildStage(getLeftNeighbor(p), getRightNeighbor(p), msg.getPreference());
					break;
				case 3:
					p.discard(discarded);
					break;
			}
		}
		
		endOfTurn();			
	}
	
	public void cardCoinBonusActivation(Structure s, Player p)
	{
		for ( SpecialEffect se: s.getEffects() )
		{
			if ( se.getID() == CardCoinBonus.CardCoinBonusID && !se.isUsedUp() )
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
	
	//Server side end of game effects
	public void endOfGameSpecialEffects(ArrayList<CommandMessage> messages)
	{
		for ( Player p : players )
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
			
			for ( WonderBoardStage stg: p.getWonderBoard().getStages() )
				addPointActivate(stg, p, messages);
			
			for ( CommandMessage msg: messages )
			{
				if ( msg.getPlayerID() == p.getID() )
				{
					for ( ScientificSymbols sc: msg.getScientificSymbols() )
					{
						p.getScientificSymbols().addScientifcSymbols(sc);
					}
				}
			}
		}
		
		countPlayersVictoryPoints();
	}

	public void addPointActivate(WonderBoardStage s, Player p, ArrayList<CommandMessage> messages)
	{
		Random r = new Random();
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
						if ( p.ai() )
						{
							int ch = r.nextInt(3);
							switch ( ch )
							{
							case 0:
								p.getScientificSymbols().addScientifcSymbols(new ScientificSymbols(1, 0, 0));
								break;
							case 1:
								p.getScientificSymbols().addScientifcSymbols(new ScientificSymbols(0, 1, 0));
								break;
							case 2:
								p.getScientificSymbols().addScientifcSymbols(new ScientificSymbols(0, 0, 1));
								break;
							}
						}
					}
					break;
			}
		}
	}
	
	public void addPointActivate(Structure s, Player p, ArrayList<CommandMessage> messages)
	{
		Random r = new Random();
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
						if ( p.ai() )
						{
							int ch = r.nextInt(3);
							switch ( ch )
							{
							case 0:
								p.getScientificSymbols().addScientifcSymbols(new ScientificSymbols(1, 0, 0));
								break;
							case 1:
								p.getScientificSymbols().addScientifcSymbols(new ScientificSymbols(0, 1, 0));
								break;
							case 2:
								p.getScientificSymbols().addScientifcSymbols(new ScientificSymbols(0, 0, 1));
								break;
							}
						}
					}
					break;
			}
		}
	}
	
	public void serverHandleChosenGuids(ArrayList<CommandMessage> messages)
	{
		for ( CommandMessage msg: messages )
		{
			Structure s;
			Player p = getPlayerByID(msg.getPlayerID());
			if ( msg.getCardID() != 0 )
			{
				p.getWonderBoard().buildStructure(s = CardHandler.getCardByID(msg.getCardID()));
				for ( SpecialEffect se : s.getEffects() ) p.activateBuildEffect(se);
			}
		}
	}
	
	
	//server side endof game effects
	//server methods
	
		
		
	//client moves
		
	public void initMove(Player p, int move, int neib)
	{
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(p.getID());
		if ( age > 3 ) 
			msg.setMsgType(CommandMessage.SCIENTIFIC_SYMBOL_TYPE+1);
		else msg.setMsgType(CommandMessage.MOVE_TYPE);
		msg.setAction(move);
		msg.setCardID(p.getChosenCard().getID());
		msg.setPreference(neib);	
		msg.setFree(p.getFreePermission() ? 1 : 0);
		p.setCommand(msg);
		p.sendCommandMessage();
		p.pause();
	}
	
	public void initResourceChoice(Player p, ArrayList<Resources> resChoices)
	{
		if ( age > 3 ) return;//resChoices.clear();
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(p.getID());
		msg.setMsgType(CommandMessage.RESOURCE_CHOICE_TYPE);
		msg.setResourceChoices(resChoices);
		p.setCommand(msg);
		p.sendCommandMessage();
		p.pause();
	}
		
	public void initScienceChoice(Player p, ArrayList<ScientificSymbols> symbs)
	{
		if ( age == 5 )
		{
			System.out.println("========================================Im IN!");
			CommandMessage msg = new CommandMessage();
			msg.setPlayerID(p.getID());
			msg.setMsgType(CommandMessage.SCIENTIFIC_SYMBOL_TYPE);
			msg.setScientificSymbol(symbs);
			p.setCommand(msg);
			p.sendCommandMessage();
			p.pause();
		}
	}
	
	public void initGuildChoice(Player p, Structure s)
	{
		if ( age == 4 )
		{
			CommandMessage msg = new CommandMessage();
			msg.setPlayerID(p.getID());
			msg.setMsgType(CommandMessage.CHOSEN_GUILD_TYPE);
			msg.setCardID(s.getID());
			p.setCommand(msg);
			p.sendCommandMessage();
			p.pause();
		}
	}
	//client moves
	
	
	public Player getLocalPlayer()
	{
		for ( Player p : players )
		{
			if ( p.getID() == localPlayerID )
			{
				return p;
			}
		}
		return null;
	}
	
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
				if ( i == players.size()-1 )
					return players.get(0);
				else return players.get(i+1);
			}
		}
		return null;
	}
	
	//handles all the moves received by the server
	public Match2 dispatch(ArrayList<CommandMessage> messages)
	{
		if ( messages == null || age == 6 ) return this;

		int type = messages.get(0).getMsgType();
		switch ( type )
		{
			case CommandMessage.RESOURCE_CHOICE_TYPE:
				beginningOfTurnEffects(messages);
				break;
					
			case CommandMessage.MOVE_TYPE:
				runTurns(messages);
				break;
				
			case CommandMessage.CHOSEN_GUILD_TYPE:
				System.out.println("=======================GUILD");
				if ( age == 4 )
				{
					serverHandleChosenGuids(messages);
					age += 1;
				}
				break;
					
			case CommandMessage.SCIENTIFIC_SYMBOL_TYPE:
				System.out.println("=======================SCIENCE");
				if ( age == 5 )
				{
					endOfGameSpecialEffects(messages);
					countPlayersVictoryPoints();
					age += 1;
				}
				break;
		}
		
		return this;
	}
	
	public void countPlayersVictoryPoints()
	{	
		if ( !calcCalled )
		{
			calcCalled = true;
			for (Player p : players){
				
				//Conflict Tokens
				p.addVictoryPoints(p.getConflictTokens().getVictoryPoints());
				
				//Coins
				p.addVictoryPoints((int)Math.floor(p.getResources().getCoins() / 3));
				
				//Scientific
				p.addVictoryPoints(p.getScientificSymbols().victoryPointsValue());
				System.out.println("======================Science vicpts: " + p.getScientificSymbols().victoryPointsValue());
			}
		}
	}
	
	public void endOfTurn()
	{
		endOfTurnSpecialEffects(players);
		CardHandler.PassCardsToNeighbors(getPlayers(), getAge());
		if ( turn == 6 )
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
			age += 1;
			incPlayerAges();
			turn = 1;
			if ( getAge() == 2 ) CardHandler.DistributeCards(getPlayers(), getDeck());
			if ( getAge() == 3 ) CardHandler.DistributeCards(getPlayers(), getDeck());
			if ( getAge() == 4 ) 
			{
				discardAllPlayersCards();
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
	
	
	



}
