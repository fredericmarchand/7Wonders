package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Statue extends Structure {

	public static final int StatueID = 0x25;
	
	public Statue()
	{
		super(new Resources(2, 0, 1, 0, 0, 0, 0, 0), StatueID, "Statue", BLUE_CARD, 2);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
