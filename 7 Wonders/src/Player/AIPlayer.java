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
	
	public void chooseCard()
	{
		for (Structure s : getCards()){
			if (s.getResourceCost().canAfford(getResources()) && !getWonderBoard().containsCard(s.getID())){
				setChosenCard(s);
				break;
			}
				
		}
		getCards().remove(getChosenCard());
	}
	
	
	
}
