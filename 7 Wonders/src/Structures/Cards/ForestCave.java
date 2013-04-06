package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;

public class ForestCave extends Structure {

	public static final int ForestCaveID = 9;
	
	public ForestCave()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), ForestCaveID, "Forest Cave", BROWN_CARD, 1);
		effects.add(new ResourceChoice(1, 0, 1, 0, 0, 0, 0, true));
	}



}
