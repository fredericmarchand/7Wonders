package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Sawmill extends Structure {

	public static final int SawmillID = 11;
	
	public Sawmill()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), SawmillID, "Saw Mill", BROWN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
