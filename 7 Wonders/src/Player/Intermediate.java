package Player;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Structure;


public class Intermediate implements Strategy{
	
	/*
	Follows a more dynamic strategy where the ai can calculate the other player's victory points and increase blocking selection numbers 
	based on the the difference of victory points. Military differences are used so the ai has a greater chance of keeping up with military. 
	*/
	public void strategicPick(AIPlayer p, ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor)
	{
		boolean did = false;
		float aiCoef = (float) p.calculateVictoryPoints();
		float neighCoef = (float) p.getNextPlayer(leftNeighbor, rightNeighbor).calculateVictoryPoints();
		float choiceCoef = aiCoef / neighCoef;
		
		ArrayList<Integer> selectionNumbers = p.cardSelectionNumbers(leftNeighbor, rightNeighbor, choiceCoef);
		
		for ( int index = 0; index < p.cards.size(); ++index )
		{
			if (Collections.max(selectionNumbers) <= 0)
				break;
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
			ArrayList<Integer> blockNumbers = p.blockSelectionNumbers(leftNeighbor, rightNeighbor);
			if (!p.cards.isEmpty())
				p.chooseCard(blockNumbers.indexOf(Collections.max(blockNumbers)));
			else{
				System.out.println("*****Deck Empty!*****");
				return;
			}
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
			p.discard(discarded);
		}
		else if (!p.cards.isEmpty())
			p.cards.remove(p.chosenCardIndex);
		else
			System.out.println("*****Deck Empty!*****");	
	}
}
