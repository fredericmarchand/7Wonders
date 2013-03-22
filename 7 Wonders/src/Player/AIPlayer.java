package Player;

import Structures.Structure;
import Tokens.Resources;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

	public static final int SCIENCE = 0;
	public static final int MILITARY = 1;
	public static final int BONUSPOINTS = 2;
	private int strategy;
	private Random rand = new Random();
	
	
	public AIPlayer(){
		super();
		isAI = true;
		strategy = rand.nextInt(3);
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
			if ( result != 0 ) break;
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
	
	//Looks for cards that could be used strategically by the next player
	public ArrayList<Integer> blockableIndexesFree(Player leftNeighbor, Player rightNeighbor)
	{
		ArrayList<Integer> blockIndexes = new ArrayList<Integer>();
		Player p = chooseNextPlayer(leftNeighbor, rightNeighbor);
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if (cards.get(index).canBuildForFree(p.getWonderBoard()))
			{
				blockIndexes.add(index);
			}
		}
		return blockIndexes;
	}
	
	//Returns a list of indexes that refer to science cards in the hands
	public ArrayList<Integer> getScienceIndexes()
	{
		ArrayList<Integer> science = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( cards.get(index).getColor() == Structure.GREEN_CARD )
			{
				science.add(index);
			}
		}
		return science;
	}
	
	//Returns a list of indexes that refer to military cards in the hand
	public ArrayList<Integer> getMilitaryIndexes()
	{
		ArrayList<Integer> military = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( cards.get(index).getColor() == Structure.RED_CARD )
			{
				military.add(index);
			}
		}
		return military;
	}
	
	//Returns a list of indexes that refer to military cards in the hand
	public ArrayList<Integer> getVictoryIndexes()
	{
		ArrayList<Integer> vPoint = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( cards.get(index).getColor() == Structure.BLUE_CARD )
			{
				vPoint.add(index);
			}
		}
		return vPoint;
	}	
	
	//Try to detect the next players basic strategy
	public int nextPlayerStrat(Player leftNeighbor, Player rightNeighbor)
	{
		Player p = chooseNextPlayer(leftNeighbor, rightNeighbor);
		
		int military = p.getWonderBoard().getRedCardAmount();
		int science = p.getWonderBoard().getGreenCardAmount();
		int vPoint = p.getWonderBoard().getBlueCardAmount();
		int totalStrat = military + science + vPoint;
		
		if ( totalStrat != 0 )
		{
			if ((((float)military / totalStrat) > ((float)science / totalStrat)) && (((float)military / totalStrat) > ((float)science / totalStrat)))
			{
				return MILITARY;
			}
			else if (((float)science / totalStrat) > ((float)vPoint / totalStrat))
			{
				return SCIENCE;
			}
			else
			{
				return BONUSPOINTS;
			}
		}
		return -1;
	}
	
	//Returns the player that the hand is being passed to next turn
	public Player chooseNextPlayer(Player leftNeighbor, Player rightNeighbor)
	{
		if (getAge() == 2)
			return rightNeighbor;
		else
			return leftNeighbor;
	}

}
