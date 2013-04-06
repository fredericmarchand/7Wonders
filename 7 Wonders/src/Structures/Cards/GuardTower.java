package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;

public class GuardTower extends Structure {

	public static final int GuardTowerID = 43;
	
	public GuardTower()
	{
		super(new Resources(0, 0, 0, 1, 0, 0, 0, 0), GuardTowerID, "Guard Tower", RED_CARD, 1);
		effects.add(new ShieldBonus(1));
	}
	


}
