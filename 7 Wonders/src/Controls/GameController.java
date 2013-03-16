package Controls;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Player.Player;
import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Structures.Effects.ScientificSymbolBonus;
import Structures.Effects.SpecialEffect;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import View.Controller;
import View.MainFrame;

public class GameController implements Controller {

	public static final int BEGINNINGOFGAME = 0;
	public static final int BEGINNINGOFAGE = 1;
	public static final int BEGINNINGTURN = 2;
	public static final int MOVECHOICE = 3;
	public static final int ENDOFTURN = 4;
	public static final int ENDOFAGE = 5;
	public static final int ENDOFGAME = 6;

	public static final int CHOOSERESOURCE = 10;
	public static final int CHOOSESCIENTIFICSYMBOL = 11;
	public static final int CHOOSETRADINGPREFERENCE = 12;
	public static final int CHOOSEFROMDISCSARDED = 13;
	public static final int PLAYLASTCARD = 14;

	private Player user;
	private Match match;
	private MainFrame frame;
	
	public GameController(Player p, Match m)
	{
		user = p;
		match = m;
		
		match.addLocalPlayer(user);
		match.fillWithAI();
		match.init();
		
		frame = new MainFrame(this);
		frame.startMatch(match);
		frame.setVisible(true);
	}
	

	@Override
	public int canBuildStructure(Structure s) {
		return user.canBuild(match.getLeftNeighbor(user), match.getRightNeighbor(user));
	}

	@Override
	public void buildStructure() {
		// TODO Auto-generated method stub
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
		CardHandler.PassCardsToNeighbors(match.getPlayers(), match.getAge());
		match.handleAIPlayerMoves();
		if ( match.getTurn() == 6 )
		{
			PlayerInteraction.SettleMilitaryConflicts(match.getPlayers(), match.getAge());
			match.setAge(match.getAge()+1);
			match.setTurn(1);
			if ( match.getAge() == 2 ) CardHandler.DistributeCards(match.getPlayers(), match.getDeck());
			if ( match.getAge() == 3 ) CardHandler.DistributeCards(match.getPlayers(), match.getDeck());
			if ( match.getAge() == 4 ) 
			{
				match.setAge(1); 
				System.out.println("Game Over");
			}
		}
		else match.setTurn(match.getTurn()+1);
		
		frame.update();
	}

	@Override
	public int canBuildWonderStage() {
		return user.canBuildStage(match.getLeftNeighbor(user), match.getRightNeighbor(user));
	}

	@Override
	public void buildWonderStage() {
		int ans = user.canBuild(match.getLeftNeighbor(user), match.getRightNeighbor(user));
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
		CardHandler.PassCardsToNeighbors(match.getPlayers(), match.getAge());
		match.handleAIPlayerMoves();
		if ( match.getTurn() == 6 )
		{
			PlayerInteraction.SettleMilitaryConflicts(match.getPlayers(), match.getAge());
			match.setAge(match.getAge()+1);
			match.setTurn(1);
			if ( match.getAge() == 2 ) CardHandler.DistributeCards(match.getPlayers(), match.getDeck());
			if ( match.getAge() == 3 ) CardHandler.DistributeCards(match.getPlayers(), match.getDeck());
			if ( match.getAge() == 4 ) 
			{
				match.setAge(1); 
				System.out.println("Game Over");
			}
		}
		else match.setTurn(match.getTurn()+1);
		frame.update();
	}

	@Override
	public boolean needToChooseScienceSymbol() {
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
		return false;
	}

	@Override
	public void scienceChosen(ScientificSymbols s) {
		
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
	public ArrayList<Resources> needToChooseResources() {
		// TODO Auto-generated method stub
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
		return resources;
	}

	@Override
	public void resourceChosen(Resources r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void needToChooseTradingPref() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chosenTradingPref(int t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean chooseCard(Structure s) {
		// TODO Auto-generated method stub
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
	public void discardChosen() {
		// TODO Auto-generated method stub
		user.discard(match.getDiscardedCards());
		//user.getCards().remove(user.getChosenCard());
		user.chooseCard(null);
		CardHandler.PassCardsToNeighbors(match.getPlayers(), match.getAge());
		match.handleAIPlayerMoves();
		if ( match.getTurn() == 6 )
		{
			PlayerInteraction.SettleMilitaryConflicts(match.getPlayers(), match.getAge());
			match.setAge(match.getAge()+1);
			match.setTurn(1);
			if ( match.getAge() == 2 ) CardHandler.DistributeCards(match.getPlayers(), match.getDeck());
			if ( match.getAge() == 3 ) CardHandler.DistributeCards(match.getPlayers(), match.getDeck());
			if ( match.getAge() == 4 ) 
			{
				match.setAge(1); 
				System.out.println("Game Over");
			}
		}
		else match.setTurn(match.getTurn()+1);
		//for ( Player pap: match.getPlayers() )
		//{
		//	for ( Structure s: pap.getCards() ) 
		//	{
		//	 	System.out.println(s.getName());
		//	}
		//	System.out.println(pap.getCards().size());
		//}
		frame.update();
	}

	@Override
	public ArrayList<Structure> needToChooseCopyGuild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chosenGuild(Structure g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Structure> needToChooseDiscarded() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chosenDiscarded(Structure g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Structure needToChooseLastCard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[])
	{
		String name = JOptionPane.showInputDialog("What is your username? ");
		GameController gc = new GameController(new Player(name, 0), new Match());
	}
	
}
