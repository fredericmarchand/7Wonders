package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Apothecary extends Structure {

	public static final int ApothecaryID = 0x63;
	
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
