package Controls;

import java.util.ArrayList;

import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import View.Controller;

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

	@Override
	public int canBuildStructure(Structure s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buildStructure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int canBuildWonderStage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buildWonderStage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needToChooseScienceSymbol() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void scienceChosen(ScientificSymbols s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resources needToChooseResources() {
		// TODO Auto-generated method stub
		return null;
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
		return false;
	}

	@Override
	public void discardChosen() {
		// TODO Auto-generated method stub
		
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
	
}
