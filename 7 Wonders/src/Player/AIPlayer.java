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
		boolean did = false;
		for ( int index = 0; index < cards.size(); ++index )
		{
			chooseCard(index);
			if ( !wonderBoard.containsCard(chosenCard.getID()) )
			{
				if ( ((chosenCard.getResourceCost().canAfford(Resources.addResources(extraResources, resources, unavailableResources)) 
						|| chosenCard.canBuildForFree(wonderBoard)))  )
				{
					buildStructure();
					did = true;
					break;
				}
			}
		}
		if ( !did )
		{
			getOwnedResources().addCoins(3);
		}
		/*chooseCard(0);
		if ( !wonderBoard.containsCard(chosenCard.getID()) )
		{
			if ( ((chosenCard.getResourceCost().canAfford(Resources.addResources(extraResources, resources, unavailableResources)) 
					|| chosenCard.canBuildForFree(wonderBoard)))  )
				buildStructure();
		}
		else
		{
			getOwnedResources().addCoins(3);
		}*/
		cards.remove(chosenCardIndex);
	}
	

}
