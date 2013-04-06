package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class StonePit extends Structure {

	public static final int StonePitID = 2;
	
	public StonePit()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 0), StonePitID, "Stone Pit", BROWN_CARD, 1);
		effects.add(new ResourcesBonus(0, 1, 0, 0, 0, 0, 0));
	}



}
