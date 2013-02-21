package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Aqueduct extends Structure {

	public static final int AqueductID = 0x27;
	
	public Aqueduct()
	{
		super(new Resources(0, 3, 0, 0, 0, 0, 0, 0), AqueductID, "Aqueduct", BLUE_CARD, 2);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
