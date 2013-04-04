package Player;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Structure;

public interface Strategy {
	
	public void strategicPick(AIPlayer p, ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor);
	
}

//Tries to build a random card with takes priority, if it cant it builds a stage, if it cant build a stage it discards the card.
class Simple implements Strategy{
	
	public void strategicPick(AIPlayer p, ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor){
		boolean did = false;
		for ( int index = 0; index < p.cards.size(); ++index )
		{
			int result;
			p.chooseCard(index);
			result = p.canBuild(leftNeighbor, rightNeighbor);
			switch ( result )
			{
			case 0: continue;
			
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

//Follows a specific structure strategy while also blocking neighbors
class Moderate implements Strategy{
	
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

/*
Follows a dynamic strategy that involves calculating neighbors victory points to determine the optimal choice of cards to 
gain victory points and deny the neighbor from gaining maximum points.
*/
class Intermediate implements Strategy{
	
	public void strategicPick(AIPlayer p, ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor){
		
	}
}