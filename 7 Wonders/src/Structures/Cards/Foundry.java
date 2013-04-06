package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Foundry extends Structure {

	public static final int FoundryID = 14;
	
	public Foundry()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), FoundryID, "Foundry", BROWN_CARD, 2);
		effects.add(new ResourcesBonus(2, 0, 0, 0, 0, 0, 0));
	}



}
