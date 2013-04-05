package Player;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Structure;


public class Moderate implements Strategy{
	//Follows a specific structure strategy while also blocking neighbors
	public void strategicPick(AIPlayer p, ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor){
		boolean did = false;
		ArrayList<Integer> selectionNumbers = p.cardSelectionNumbers(leftNeighbor, rightNeighbor);
		
		for ( int i = 0; i < p.cards.size(); ++i )
		{
			int result;
			p.chooseCard(selectionNumbers.indexOf(Collections.max(selectionNumbers)));
			result = p.canBuild(leftNeighbor, rightNeighbor);
			switch ( result )
			{
			case 0: 
				selectionNumbers.remove(Collections.max(selectionNumbers));
				continue;
			
			case 1: 
				p.buildStructure();
				did = true;
				break;
			case 2:
				did = true;
				p.buildStructure(leftNeighbor, rightNeighbor, 2);
				break;
			}
			if ( result != 0 ) break;
		}
		if ( !did )
		{
			p.chooseCard(0);
			int result = p.canBuildStage(leftNeighbor, rightNeighbor);
			switch ( result )
			{
			case 0: break;
			
			case 1: 
				did = true;
				p.buildStage(leftNeighbor, rightNeighbor, 2);
				break;
				
			case 2:
				did = true;
				p.buildStage(leftNeighbor, rightNeighbor, 2);
				break;
			}
		}
		if ( !did )
		{
			p.getOwnedResources().addCoins(3);
		}
		p.cards.remove(p.chosenCardIndex);
	}
}
