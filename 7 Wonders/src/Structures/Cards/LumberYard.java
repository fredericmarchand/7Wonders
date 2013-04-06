package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class LumberYard extends Structure {

	public static final int LumberYardID = 1;
	
	public LumberYard()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 0), LumberYardID, "Lumber Yard", BROWN_CARD, 1);
		effects.add(new ResourcesBonus(0, 0, 1, 0, 0, 0, 0));
	}



}
