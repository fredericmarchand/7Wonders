package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;

public class TownHall extends Structure {

	public static final int TownHallID = 37;
	
	public TownHall()
	{
		super(new Resources(1, 2, 0, 0, 1, 0, 0, 0), TownHallID, "Town Hall", BLUE_CARD, 3);
		effects.add(new VictoryPointBonus(6));
	}
	


}
