package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;

public class Theater extends Structure {

	public static final int TheaterID = 31;
	
	public Theater()
	{
		super(new Resources(), TheaterID, "Theater", BLUE_CARD, 1);
		effects.add(new VictoryPointBonus(2));
	}
	


}
