package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Loom extends Structure {

	public static final int LoomID = 15;
	
	public Loom()
	{
		super(new Resources(), LoomID, "Loom", GREY_CARD, 1);
		effects.add(new ResourcesBonus(0, 0, 0, 0, 0, 1, 0));
	}



}
