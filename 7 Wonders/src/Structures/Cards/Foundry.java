package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Foundry extends Structure {

	public static final int FoundryID = 14;
	
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
