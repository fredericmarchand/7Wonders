package Player;

import WonderBoards.WonderBoard;
import Structures.Structure;
import java.util.ArrayList;
import Tokens.*;
import java.util.Random;

public class AIPlayer extends Player {

	
	public AIPlayer(){
		super();
	}
	
	public void pickCard()
	{
		int chosenIndex = -1;
		for (Structure s : getCards()){
			if (s.getResourceCost().canAfford(getResources()) && !getWonderBoard().containsCard(s.getID())){
				chosenIndex = getCards().indexOf(s);
				break;
			}
				
		}
		if (chosenIndex != -1)
			chooseCard(chosenIndex);
	}
	
	
	
}
