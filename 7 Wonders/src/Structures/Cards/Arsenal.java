package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;

public class Arsenal extends Structure {

	public static final int ArsenalID = 48;
	
	public Arsenal()
	{
		super(new Resources(1, 0, 2, 0, 0, 1, 0, 0), ArsenalID, "Arsenal", RED_CARD, 3);
		effects.add(new ShieldBonus(3));
	}

	


}
