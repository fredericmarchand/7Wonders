package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class ClayPool extends Structure {

	public static final int ClayPoolID = 3;
	
	public ClayPool()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 0), ClayPoolID, "Clay Pool", BROWN_CARD, 1);
		effects.add(new ResourcesBonus(0, 0, 0, 1, 0, 0, 0));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
