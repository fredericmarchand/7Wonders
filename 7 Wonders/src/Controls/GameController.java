package Controls;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Player.Player;
import Structures.Structure;
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
	public Resources needToChooseResources() {
		for ( Structure s: user.getWonderBoard().getPurpleCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
				{
					if ( ((ScientificSymbolBonus)se).canChoose() )
						return new Resources();
				}
			}
		}
		return null;
	}

	@Override
	public void resourceChosen(Resources r) {
		
	}

	@Override
	public void needToChooseTradingPref() {
		
	}

	@Override
	public void chosenTradingPref(int t) {
		
	}

	@Override
	public boolean chooseCard(Structure s) {
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
		user.discard(match.getDiscardedCards());
		user.getCards().remove(user.getChosenCard());
		user.chooseCard(null);
		frame.update();
	}

	@Override
	public ArrayList<Structure> needToChooseCopyGuild() {
		return null;
	}

	@Override
	public void chosenGuild(Structure g) {
		
	}

	@Override
	public ArrayList<Structure> needToChooseDiscarded() {
		return null;
	}

	@Override
	public void chosenDiscarded(Structure g) {
		
	}

	@Override
	public Structure needToChooseLastCard() {
		return null;
	}
	
	public static void main(String args[])
	{
		String name = JOptionPane.showInputDialog("What is your username? ");
		GameController gc = new GameController(new Player(name, 0), new Match());
	}
	
}
