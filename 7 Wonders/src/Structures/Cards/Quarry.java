package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Quarry extends Structure {

	public static final int QuarryID = 13;
	
	public Quarry()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), QuarryID, "Quarry", BROWN_CARD, 2);
		effects.add(new ResourcesBonus(0, 0, 0, 2, 0, 0, 0));
	}
	


}
