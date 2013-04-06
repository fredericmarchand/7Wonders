package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Press extends Structure {

	public static final int PressID = 17;
	
	public Press()
	{
		super(new Resources(), PressID, "Press", GREY_CARD, 1);
		effects.add(new ResourcesBonus(0, 0, 0, 0, 0, 0, 1));
	}
	


}
