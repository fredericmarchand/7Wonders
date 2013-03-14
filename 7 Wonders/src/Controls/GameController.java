package Controls;

import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import View.Controller;

public class GameController implements Controller {

	@Override
	public boolean handleCardClicked(Structure s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int canBuildStructure(Structure s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buildStructure(Structure s) {
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
	
}
