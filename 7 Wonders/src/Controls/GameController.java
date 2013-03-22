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
import View.Controller;
import View.Images;
import View.MainFrame;
import WonderBoards.WonderBoardStage;

public class GameController extends java.lang.Thread implements Controller, Runnable {

	public static final int BEGINNINGTURN = 0;
	public static final int MOVE = 1;
	public static final int ENDOFGAME = 2;


	private Player user;
	private Match1 match;
	private MainFrame frame;
	
	public GameController(Player p, Match1 m)
	{
		user = p;
		match = m;
		
		match.addLocalPlayer(user);
		match.fillWithAI();
		match.init();
		
		run();
		//frame = new MainFrame(this);
		//frame.startMatch(match);
		//frame.setVisible(true);
	}
	
	public void run()
	{
		try 
		{
			Images.run();
			frame = new MainFrame(this);
			frame.startMatch(match);
			frame.setVisible(true);
		} 
		catch (Exception e){}
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
		if ( ans == 2 )
		{
			user.buildStructure();
		}
		else if ( ans == 1 )
		{
			user.buildStructure(match.getLeftNeighbor(user), match.getRightNeighbor(user), 2);
		}
		user.getCards().remove(user.getChosenCard());
		user.chooseCard(null);
		match.handleAIPlayerMoves();
		match.playerEndOfTurnSpecialEffects(user);
		match.endOfTurn();

		if ( match.getAge() == 4 ) 
		{
			match.discardAllPlayersCards();
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
		if ( ans == 2 )
		{
			user.buildStage();
		}
		else if ( ans == 1 )
		{
			user.buildStage(match.getLeftNeighbor(user), match.getRightNeighbor(user), 2);
		}
		user.getCards().remove(user.getChosenCard());
		user.chooseCard(null);
		match.handleAIPlayerMoves();
		match.playerEndOfTurnSpecialEffects(user);
		match.endOfTurn();

		if ( match.getAge() == 4 ) 
		{
			match.discardAllPlayersCards();
		}
		
		frame.update();
	}

	@Override
	public boolean needToChooseScienceSymbol() 
	{
		// TODO Auto-generated method stub
		for ( Structure s: user.getWonderBoard().getPurpleCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
				{
					if ( ((ScientificSymbolBonus)se).canChoose() )
						return true;
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
							return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void scienceChosen(ScientificSymbols s) 
	{
		
		for ( Structure st: user.getWonderBoard().getPurpleCards() )
		{
			for ( SpecialEffect se: st.getEffects() )
			{
				if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID && se.activateTime() == SpecialEffect.END_OF_GAME )
					((ScientificSymbolBonus)se).chooseSymbol(user, s);		
			}
		}
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
		user.discard(match.getDiscardedCards());
		user.chooseCard(null);
		match.handleAIPlayerMoves();
		match.playerEndOfTurnSpecialEffects(user);
		match.endOfTurn();

		for ( Structure s: user.getWonderBoard().getRedCards() )
		{
			System.out.println(s.getName());
		}
		System.out.println("");

		if ( match.getAge() == 4 ) 
		{
			match.discardAllPlayersCards();
		}
		
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
		return null;
	}

	@Override
	public void chosenGuild(Structure g) 
	{
		
	}

	@Override//have to check if null
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
							return match.getDiscardedCards();
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public void chosenDiscarded(Structure g) 
	{
		user.getWonderBoard().buildStructure(g);
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
		int i = 0;
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
		//System.out.println(user.getTotalResources().toString());
		if ( !(match.getAge() == 1 && match.getTurn() == 1) && i != 0 )
			frame.update();
	}
	
	public void callGarbageTruck()
	{
		frame.dispose();
	}
	
	
	public static void main(String args[])
	{
		String name = JOptionPane.showInputDialog("What is your username? ");
		@SuppressWarnings("unused")
		GameController gc = new GameController(new Player(name, 0), new Match1());
	}
	
}
