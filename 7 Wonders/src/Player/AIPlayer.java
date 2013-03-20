package Player;

import Structures.Structure;
import Tokens.Resources;

import java.util.ArrayList;

public class AIPlayer extends Player {

	
	public AIPlayer(){
		super();
		isAI = true;
	}
	
	public void pickCard(ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor)
	{
		boolean did = false;
		for ( int index = 0; index < cards.size(); ++index )
		{
			int result;
			chooseCard(index);
			result = canBuild(leftNeighbor, rightNeighbor);
			switch ( result )
			{
			case 0: continue;
			
			case 1: 
				buildStructure();
				did = true;
				break;
			case 2:
				did = true;
				buildStructure(leftNeighbor, rightNeighbor, 2);
				break;
			}
		}
		if ( !did )
		{
			chooseCard(0);
			int result = canBuildStage(leftNeighbor, rightNeighbor);
			switch ( result )
			{
			case 0: break;
			
			case 1: 
				did = true;
				buildStage(leftNeighbor, rightNeighbor, 2);
				break;
				
			case 2:
				did = true;
				buildStage(leftNeighbor, rightNeighbor, 2);
				break;
			}
		}
		if ( !did )
		{
			getOwnedResources().addCoins(3);
		}
		cards.remove(chosenCardIndex);
	}
	

}
