package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Foundry extends Structure {

	public static final int FoundryID = 0x3C;
	
	public Foundry()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), FoundryID, "Foundry", BROWN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
