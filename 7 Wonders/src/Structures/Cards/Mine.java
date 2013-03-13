package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;

public class Mine extends Structure {

	public static final int MineID = 10;
	
	public Mine()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), MineID, "Mine", BROWN_CARD, 1);
		effects.add(new ResourceChoice(1, 1, 0, 0, 0, 0, 0, true));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
