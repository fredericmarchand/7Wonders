package Player;

import java.util.ArrayList;

import Structures.Structure;

public class Simple implements Strategy{
	//Tries to build a random card with takes priority, if it cant it builds a stage, if it cant build a stage it discards the card.
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
			//System.out.print("Printing AI Player cards: [");
			//for ( Structure s: p.getCards() )
			//{
			//	System.out.print(s.getName() + ", ");
			//}
			//System.out.println("]");
			if (!p.cards.isEmpty())
				p.chooseCard(0);
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
