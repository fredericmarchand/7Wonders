package Player;

import Structures.Structure;
import Tokens.Resources;

import java.util.ArrayList;

public class AIPlayer extends Player {

	
	public AIPlayer(){
		super();
		isAI = true;
	}
	
	public void pickCard(ArrayList<Structure> disca)
	{
		chooseCard(0);
		if ( !wonderBoard.containsCard(
				chosenCard.getID()) )
		{
			if ( ((chosenCard.getResourceCost().canAfford(Resources.addResources(extraResources, resources, unavailableResources)) 
					|| chosenCard.canBuildForFree(wonderBoard)))  )
				buildStructure();
				
		}
		else
		{
			getResources().addCoins(3);
		}
		cards.remove(chosenCardIndex);
		
		/*int chosenIndex = -1;
		for (Structure s : getCards())
		{
			chosenIndex = getCards().indexOf(s);
			break;				
		}
		if (chosenIndex != -1)
		{
			chooseCard(chosenIndex);
			discard(disca);
		}*/
	}
	

}
