package View;

import java.util.ArrayList;

import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;

public interface Controller {
	
	// When card is clicked
	boolean chooseCard(Structure s);
	
	// int 0 = no, 1 = with trading, 2 = yes
	int canBuildStructure(Structure s);
	void buildStructure(Structure s);
	
	// int 0 = no, 1 = with trading, 2 = yes
	int canBuildWonderStage();
	void buildWonderStage();
	
	void discardChosen();
	
	// Special effect for guild and wonder
	boolean needToChooseScienceSymbol();
	void scienceChosen(ScientificSymbols s);
	
	// Pass one of each resource that can be selected
	Resources needToChooseResources();
	void resourceChosen(Resources r);
	
	// int 0 = left, 1 = right, 2 = random
	void needToChooseTradingPref();
	void chosenTradingPref(int t);
	
	// Pass list of all guilds that can be selected
	ArrayList<Structure> needToChooseCopyGuild();
	void chosenGuild(Structure g);
	
	// Pass list of all discarded cards
	ArrayList<Structure> needToChooseDiscarded();
	void chosenDiscarded(Structure g);
	
	// Pass the last card 
	Structure needToChooseLastCard();
	// buildStructure, buildWonderStage or discard will be chosen
	
}
