package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Apothecary extends Structure {

	public static final int ApothecaryID = 64;
	
	public Apothecary()
	{
		super(new Resources(0, 0, 0, 0, 0, 1, 0, 0), ApothecaryID, "Apothecary", GREEN_CARD, 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
