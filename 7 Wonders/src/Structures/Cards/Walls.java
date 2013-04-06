package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;

public class Walls extends Structure {

	public static final int WallsID = 44;
	
	public Walls()
	{
		super(new Resources(0, 3, 0, 0, 0, 0, 0, 0), WallsID, "Walls", RED_CARD, 2);
		effects.add(new ShieldBonus(2));
	}

	


}
