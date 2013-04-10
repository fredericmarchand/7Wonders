package Controls;

import java.util.ArrayList;

import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;

public interface Controller {
	
	// When card is clicked, save this chosen card for the upcoming action
	boolean chooseCard(Structure s);
	
	// int 0 = no, 1 = with trading, 2 = yes
	int canBuildStructure(Structure s);
	void buildStructure();
	
	// int 0 = no, 1 = with trading, 2 = yes
	int canBuildWonderStage(Structure s);
	void buildWonderStage();
	
	// Discard to get 3 coins back
	void discardChosen();
	
	// Special effect for guild and wonder
	ArrayList<ScientificSymbols> needToChooseScienceSymbol();
	void scienceChosen(ArrayList<ScientificSymbols> symbs);
	
	// Pass one of each resource that can be selected
	ArrayList<Resources> needToChooseResources();
	void resourceChosen(ArrayList<Resources> r);
	
	// int 0 = left, 1 = right, 2 = random
	void needToChooseTradingPref();
	void chosenTradingPref(int t);
	
	// Pass list of all guilds that can be selected
	ArrayList<Structure> needToChooseCopyGuild();
	void chosenGuild(Structure g);
	
	// Pass list of all discarded cards
	ArrayList<Structure> needToChooseDiscarded();
	void chosenDiscarded(Structure g);
	
	// Can build for free
	boolean canBuildForFree();
	void buildForFree(boolean b);
	
	// Pass the last card 
	Structure needToChooseLastCard();
	// buildStructure, buildWonderStage or discard will be chosen
	
}
