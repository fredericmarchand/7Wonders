package Player;

import Structures.Structure;
import Tokens.Resources;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

	public static final int SCIENCE = 0;
	public static final int MILITARY = 1;
	public static final int VPOINTS = 2;
	private int favor;
	private Strategy strategy;
	private Random rand = new Random();
	
	//Used to generate a AI with a random difficulty
	public AIPlayer(){
		super();
		isAI = true;
		favor = rand.nextInt(3);
		int randomStrat = rand.nextInt(3);
		if (randomStrat == 0)
			strategy = new Simple();
		else if (randomStrat == 1)
			strategy = new Moderate();
		else 
			strategy = new Intermediate();
			
	}
	
	//Used to generate AI with difficulty corresponding to strat passed
	public AIPlayer(Strategy strat){
		super();
		isAI = true;
		strategy = strat;
		favor = rand.nextInt(3);
	}
	
	public void pickCard(ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor)
	{
		strategy.strategicPick(this, discarded, leftNeighbor, rightNeighbor);
	}
	
	//Looks for cards that could be used strategically by the next player
	public ArrayList<Integer> blockableIndexes(Player leftNeighbor, Player rightNeighbor)
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
	
	//Returns a list of indexes that refer to resource cards in the hand
	public ArrayList<Integer> getResourceIndexes(){
		
		ArrayList<Integer> resource = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( (cards.get(index).getColor() == Structure.GREY_CARD) || (cards.get(index).getColor() == Structure.BROWN_CARD))
			{
				resource.add(index);
			}
		} 
		return resource;
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
				return VPOINTS;
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
