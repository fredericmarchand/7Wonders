package Controls;

import java.util.ArrayList;

import view.menu.MainFrame;

import client.MClient;

import Player.Player;
import Structures.Structure;
import Structures.Effects.BuildDiscardedCard;
import Structures.Effects.CopyGuild;
import Structures.Effects.PlayLastCard;
import Structures.Effects.ResourceChoice;
import Structures.Effects.ScientificSymbolBonus;
import Structures.Effects.SpecialEffect;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import WonderBoards.WonderBoardStage;

public class NetworkGameController extends java.lang.Thread implements Controller, Runnable {

	public static final int BEGINNINGTURN = 0;
	public static final int MOVE = 1;
	public static final int ENDOFGAME = 2;

	private Player user;
	private Match2 match;
	private MainFrame frame;
	
	public NetworkGameController(MClient cl, Match2 m)
	{
		user = m.getLocalPlayer();
		user.setClient(cl);
		match = m;
	}
	
	public void setMainFrame(MainFrame m) 
	{
		frame = m;
	}
		
	public void run()
	{
		frame.startMatch(match);
	}
	
	@Override
	public int canBuildStructure(Structure s) 
	{
		user.chooseCard(s);
		return user.canBuild(match.getLeftNeighbor(user), match.getRightNeighbor(user));
	}

	@Override
	public void buildStructure() 
	{
		int ans = user.canBuild(match.getLeftNeighbor(user), match.getRightNeighbor(user));
		switch ( ans )
		{
		case 1:
			match.initMove(user, 1, 2);
			break;
		case 2:
			match.initMove(user, 1, -1);
			break;
		}
		frame.updateValues();
	}

	@Override
	public int canBuildWonderStage(Structure s) 
	{
		user.chooseCard(s);
		return user.canBuildStage(match.getLeftNeighbor(user), match.getRightNeighbor(user));
	}

	@Override
	public void buildWonderStage() 
	{
		int ans = user.canBuildStage(match.getLeftNeighbor(user), match.getRightNeighbor(user));
		switch ( ans )
		{
		case 1:
			match.initMove(user, 2, 2);
			break;
		case 2:
			match.initMove(user, 2, -1);
			break;
		}
		frame.updateValues();
	}

	@Override
	public ArrayList<ScientificSymbols> needToChooseScienceSymbol() 
	{
		ArrayList<ScientificSymbols> symbs = new ArrayList<ScientificSymbols>();
		if ( match.getAge() != 5 ) return symbs;
		for ( Structure s: user.getWonderBoard().getPurpleCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
				{
					if ( ((ScientificSymbolBonus)se).canChoose() )
						symbs.add(new ScientificSymbols());
				}
			}
		}
		for ( WonderBoardStage stg: user.getWonderBoard().getStages() )
		{
			if ( stg.isBuilt() )
			{
				for ( SpecialEffect se: stg.getEffects() )
				{
					if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
					{
						if ( ((ScientificSymbolBonus)se).canChoose() )
							symbs.add(new ScientificSymbols());
					}
				}
			}
		}
		
		return symbs;
	}

	@Override
	public void scienceChosen(ArrayList<ScientificSymbols> symbs) 
	{		
		if ( match.getAge() == 5 )
			match.initScienceChoice(user, symbs);

	}

	@Override
	public ArrayList<Resources> needToChooseResources() 
	{
		ArrayList<Resources> resources = new ArrayList<Resources>();
		if ( match.getAge() > 3 || 
				(match.getTurn() == 7 && user.getCards().isEmpty()) || 
				user.getLastMsgID() == CommandMessage.RESOURCE_CHOICE_TYPE ) return resources;
		for ( Structure s: user.getWonderBoard().getYellowCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ResourceChoice.ResourceChoiceID )
				{
					resources.add(((ResourceChoice)se).getPossibilities());
				}
			}
		}
		for ( Structure s: user.getWonderBoard().getBrownGreyCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ResourceChoice.ResourceChoiceID )
				{
					resources.add(((ResourceChoice)se).getPossibilities());
				}
			}
		}
		for ( WonderBoardStage stg: user.getWonderBoard().getStages() )
		{
			if ( stg.isBuilt() )
			{
				for ( SpecialEffect se: stg.getEffects() )
				{
					if ( se.getID() == ResourceChoice.ResourceChoiceID )
					{
						resources.add(((ResourceChoice)se).getPossibilities());
					}
				}
			}
		}
		return resources;
	}

	@Override
	public void needToChooseTradingPref() 
	{
		
	}

	@Override
	public void chosenTradingPref(int t) 
	{
		
	}

	@Override
	public boolean chooseCard(Structure s) 
	{
		user.chooseCard(s);
		return true;
	}

	@Override
	public void discardChosen() 
	{
		match.initMove(user, 3, 2);
		frame.updateValues();
	}

	@Override//have to check if not null
	public ArrayList<Structure> needToChooseCopyGuild() 
	{
		if ( match.getAge() != 4 ) return new ArrayList<Structure>();
		//System.out.println("==============================needToChooseCopyGuild");
		for ( WonderBoardStage stg: user.getWonderBoard().getStages() )
		{
			if ( stg.isBuilt() )
			{
				for ( SpecialEffect se: stg.getEffects() )
				{
					if ( se.getID() == CopyGuild.CopyGuildID )
					{
						return ((CopyGuild)se).getGuilds(match.getLeftNeighbor(user), match.getRightNeighbor(user));
					}
				}
			}
		}
		return new ArrayList<Structure>();
	}

	@Override
	public void chosenGuild(Structure g) 
	{
		match.initGuildChoice(user, g);
		//System.out.println("==============================chosenGuild");
	}

	@Override //have to check if empty
	public ArrayList<Structure> needToChooseDiscarded() 
	{
		for ( WonderBoardStage stg: user.getWonderBoard().getStages() )
		{
			//System.out.println("===for each stage");
			if ( stg.isBuilt() )
			{
				//System.out.println("===if its built");
				for ( SpecialEffect se: stg.getEffects() )
				{
					//System.out.println("===effect id " + se.getID());
					if ( se.getID() == BuildDiscardedCard.BuildDiscardedCardID )
					{
						//System.out.println("===" + se.isUsedUp());
						if ( !se.isUsedUp() )
						{
							return match.getDiscardedCards();
						}
					}
				}
			}
		}
		return new ArrayList<Structure>();
	}

	@Override
	public void chosenDiscarded(Structure g) 
	{
		//System.out.println("================="+ user.getLastMsgID());
		if ( user.getLastMsgID() == CommandMessage.MOVE_TYPE )
			match.initChosenDiscarded(user, g);
	}	

	@Override//have to check if null 
	public Structure needToChooseLastCard() 
	{
		for ( WonderBoardStage stg: user.getWonderBoard().getStages() )
		{
			for ( SpecialEffect se: stg.getEffects() )
			{
				if ( se.getID() == PlayLastCard.PlayLastCardID )
				{
					return user.getCards().get(0);
				}
			}
		}
		return null;
	}

	@Override
	public void resourceChosen(ArrayList<Resources> resources) 
	{
		if ( user.getLastMsgID() == CommandMessage.CHOSEN_DISCARDED_TYPE )
			match.initResourceChoice(user, resources);
	}
	
	public void callGarbageTruck()
	{
		frame.dispose();
	}
	
	@Override
	public boolean canBuildForFree() 
	{
		//System.out.println("============Can Build For Free==============");
		if ( match.getAge() < 4 )
			return user.canFreeBuild();
		return false;
	}

	@Override
	public void buildForFree(boolean b) 
	{
		//System.out.println("=============================================" + b);
		user.setFreePermission(b);
	}
	
	public static void main(String args[])
	{
		
	}
	
	
}
