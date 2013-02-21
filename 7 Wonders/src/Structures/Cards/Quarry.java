package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Quarry extends Structure {

	public static final int QuarryID = 0x3B;
	
	public Quarry()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), QuarryID, "Quarry", BROWN_CARD, 2);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
