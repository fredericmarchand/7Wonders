package Controls;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
import View.MainFrame;
import WonderBoards.WonderBoardStage;

public class NetworkGameController extends java.lang.Thread implements Controller, Runnable {

	public static final int BEGINNINGTURN = 0;
	public static final int MOVE = 1;
	public static final int ENDOFGAME = 2;


	private Player user;
	private Match2 match;
	private MainFrame frame;
	
	public NetworkGameController(Player p, Match2 m)
	{
		user = p;
		match = m;
		
		match.init();
		
		run();
	}
	
	public void run()
	{
		frame = new MainFrame(this);
		frame.startMatch(match);
		frame.setVisible(true);
	}
	

	@Override
	public int canBuildStructure(Structure s) 
	{
		return user.canBuild(match.getLeftNeighbor(user), match.getRightNeighbor(user));
	}

	@Override
	public void buildStructure() 
	{
		int ans = user.canBuild(match.getLeftNeighbor(user), match.getRightNeighbor(user));
		switch ( ans )
		{
		case 1:
			match.initMove(user, 1, -1);
			break;
		case 2:
			match.initMove(user, 1, 2);
		}
		frame.update();
	}

	@Override
	public int canBuildWonderStage() 
	{
		return user.canBuildStage(match.getLeftNeighbor(user), match.getRightNeighbor(user));
	}

	@Override
	public void buildWonderStage() 
	{
		int ans = user.canBuildStage(match.getLeftNeighbor(user), match.getRightNeighbor(user));
		switch ( ans )
		{
		case 1:
			match.initMove(user, 2, -1);
			break;
		case 2:
			match.initMove(user, 2, 2);
		}
		frame.update();
	}

	@Override
	public ArrayList<ScientificSymbols> needToChooseScienceSymbol() 
	{
		ArrayList<ScientificSymbols> symbs = new ArrayList<ScientificSymbols>();
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
//		for ( Structure st: user.getWonderBoard().getPurpleCards() )
//		{
//			for ( SpecialEffect se: st.getEffects() )
//			{
//				if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID && se.activateTime() == SpecialEffect.END_OF_GAME )
//					((ScientificSymbolBonus)se).chooseSymbol(user, s);		
//			}
//		
//		}
//		for ( ScientificSymbols sy : symbs )
//		{
//			user.getScientificSymbols().addScientifcSymbols(sy);
//		}
		match.initScienceChoice(user, symbs);
		
	}

	@Override
	public ArrayList<Resources> needToChooseResources() 
	{
		ArrayList<Resources> resources = new ArrayList<Resources>();
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
		System.out.println(user.getCards());
		for ( int i = 0; i < user.getCards().size(); ++i )
		{
			if ( user.getCards().get(i).getID() == s.getID() )
			{
				user.chooseCard(i);
				break;
			}
		}
		return true;
	}

	@Override
	public void discardChosen() 
	{
		match.initMove(user, 3, 2);
		frame.update();
	}

	@Override//have to check if not null
	public ArrayList<Structure> needToChooseCopyGuild() 
	{
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
		user.getWonderBoard().buildStructure(g);
		for ( SpecialEffect se: g.getEffects() )
			user.activateBuildEffect(se);
	}

	@Override //have to check if empty
	public ArrayList<Structure> needToChooseDiscarded() 
	{
		for ( WonderBoardStage stg: user.getWonderBoard().getStages() )
		{
			if ( stg.isBuilt() )
			{
				for ( SpecialEffect se: stg.getEffects() )
				{
					if ( se.getID() == BuildDiscardedCard.BuildDiscardedCardID )
					{
						if ( !se.isUsedUp() )
						{
							se.use();
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
		user.getWonderBoard().buildStructure(g);
		match.getDiscardedCards().remove(g);
		for ( SpecialEffect se: g.getEffects() )
			user.activateBuildEffect(se);
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
		/*int i = 0;
		for ( Structure s: user.getWonderBoard().getYellowCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ResourceChoice.ResourceChoiceID )
				{
					user.getUnvResources().addResources(resources.get(i++));
				}
			}
		}
		for ( Structure s: user.getWonderBoard().getBrownGreyCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ResourceChoice.ResourceChoiceID )
				{
					user.getExtraResources().addResources(resources.get(i++));
				}
			}
		}
		//System.out.println(user.getTotalResources().toString());*/
		//if ( !(match.getAge() == 1 && match.getTurn() == 1) && i != 0 )
		//	frame.update();
		if ( resources == null )
			resources = new ArrayList<Resources>();
		match.initResourceChoice(user, resources);
		
	}
	
	public void callGarbageTruck()
	{
		frame.dispose();
	}
	
	
	public static void main(String args[])
	{
		String name = JOptionPane.showInputDialog("What is your username? ");
		@SuppressWarnings("unused")
		NetworkGameController gc = new NetworkGameController(new Player(name, 0), new Match2());
	}
	
}
