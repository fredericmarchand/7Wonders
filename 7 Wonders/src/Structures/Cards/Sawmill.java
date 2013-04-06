package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Sawmill extends Structure {

	public static final int SawmillID = 11;
	
	public Sawmill()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), SawmillID, "Saw Mill", BROWN_CARD, 2);
		effects.add(new ResourcesBonus(0, 0, 2, 0, 0, 0, 0));
	}



}
