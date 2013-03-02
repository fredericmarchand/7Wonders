package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Baths extends Structure {

	public static final int BathsID = 0x21;
	
	public Baths()
	{
		super(new Resources(0, 1, 0, 0, 0, 0, 0, 0), BathsID, "Baths", BLUE_CARD, 1);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
