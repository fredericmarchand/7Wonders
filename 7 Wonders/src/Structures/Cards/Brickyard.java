package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Brickyard extends Structure {

	public static final int BrickyardID = 13;
	
	public Brickyard()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), BrickyardID, "Brick Yard", BROWN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
