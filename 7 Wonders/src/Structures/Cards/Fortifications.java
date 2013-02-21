package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Fortifications extends Structure {

	public static final int FortificationsID = 0x19;
	
	public Fortifications()
	{
		super(new Resources(3, 1, 0, 0, 0, 0, 0, 0), FortificationsID, "Fortifications", RED_CARD, 3);
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
