package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;

public class TimberYard extends Structure {

	public static final int TimberYardID = 8;
	
	public TimberYard()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), TimberYardID, "Timber Yard", BROWN_CARD, 1);
		effects.add(new ResourceChoice(0, 1, 1, 0, 0, 0, 0, true));
	}



}
