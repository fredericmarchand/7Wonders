package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Brickyard extends Structure {

	public static final int BrickyardID = 13;
	
	public Brickyard()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), BrickyardID, "Brick Yard", BROWN_CARD, 2);
		effects.add(new ResourcesBonus(0, 0, 0, 2, 0, 0, 0));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
