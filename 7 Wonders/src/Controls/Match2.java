package Controls;

import java.util.ArrayList;

import Structures.Effects.*;
import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import WonderBoards.WonderBoardStage;
import Player.*;
import java.util.Scanner;

public class Match2 {
	
	private ArrayList<Player> players;
	private int age, turn, numPlayers;
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
	private long localPlayerID;

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
		//Added to give players knowledge of the current age for AI choices
		for (Player p : players)
		{
			p.initAge();
		}
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
	
	public void setAge(int a)
	{
		age = a;
	}
	
	public void setTurn(int t)
	{
		turn = t;
	}
	
	public void setLocalPlayerID(long id)
	{
		localPlayerID = id;
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
	
	public Player addAIPlayer(long id, String name)
	{
		players.add(new AIPlayer(id, name, new Simple()));
		return players.get(players.size()-1);
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
				r.addResources(msg.getResourceChoices().get(index));
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
		
		handleAIPlayerMoves();
		endOfTurn();			
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
		}
		
		countPlayersVictoryPoints();
		discardAllPlayersCards();
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
							{
								for ( ScientificSymbols sc: msg.getScientificSymbols() )
								{
									p.getScientificSymbols().addScientifcSymbols(sc);
								}
							}
						}
					}
					break;
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
		msg.setMsgType(CommandMessage.MOVE_TYPE);
		msg.setAction(move);
		msg.setCardID(p.getChosenCard().getID());
		msg.setPreference(neib);	
		p.setCommand(msg);
		p.sendCommandMessage();
	}
	
	public void initResourceChoice(Player p, ArrayList<Resources> resChoices)
	{
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(p.getID());
		msg.setMsgType(CommandMessage.RESOURCE_CHOICE_TYPE);
		msg.setResourceChoices(resChoices);
		p.setCommand(msg);
		p.sendCommandMessage();
	}
		
	public void initScienceChoice(Player p, ArrayList<ScientificSymbols> symbs)
	{
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(p.getID());
		msg.setMsgType(CommandMessage.SCIENTIFIC_SYMBOL_TYPE);
		msg.setScientificSymbol(symbs);
		p.setCommand(msg);
		p.sendCommandMessage();
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
		for ( CommandMessage msg: messages )
		{
			switch ( msg.getMsgType() )
			{
				case CommandMessage.RESOURCE_CHOICE_TYPE:
					beginningOfTurnEffects(messages);
					break;
					
				case CommandMessage.MOVE_TYPE:
					runTurns(messages);
					break;
					
				case CommandMessage.SCIENTIFIC_SYMBOL_TYPE:
					endOfGameSpecialEffects(messages);
					break;
			}
		}
		return this;
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
		for ( Player p: players )
		{
			p.getCards().remove(p.getChosenCard());
			p.chooseCard(null);
		}
		endOfTurnSpecialEffects(players);
		CardHandler.PassCardsToNeighbors(getPlayers(), getAge());
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
			turn = 1;
			if ( getAge() == 2 ) CardHandler.DistributeCards(getPlayers(), getDeck());
			if ( getAge() == 3 ) CardHandler.DistributeCards(getPlayers(), getDeck());
			if ( getAge() == 4 ) 
			{
				//endOfGameSpecialEffects(players, messages);
				//countPlayersVictoryPoints();
				//discardAllPlayersCards();
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

	}

}
