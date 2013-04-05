package Player;

import java.util.ArrayList;

import Structures.Structure;

public interface Strategy {
	
	public void strategicPick(AIPlayer p, ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor);
	
}

