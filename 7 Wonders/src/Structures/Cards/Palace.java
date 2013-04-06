package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;

public class Palace extends Structure {

	public static final int PalaceID = 38;
	
	public Palace()
	{
		super(new Resources(1, 1, 1, 1, 1, 1, 1, 0), PalaceID, "Palace", BLUE_CARD, 3);
		effects.add(new VictoryPointBonus(8));
	}
	


}
