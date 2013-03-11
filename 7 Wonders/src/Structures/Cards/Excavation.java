package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Excavation extends Structure {

	public static final int ExcavationID = 6;
	
	public Excavation()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), ExcavationID, "Excavation", BROWN_CARD, 1);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
