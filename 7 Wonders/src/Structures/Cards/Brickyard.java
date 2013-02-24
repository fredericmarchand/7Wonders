package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Brickyard extends Structure {

	public static final int BrickyardID = 0x3E;
	
	public Brickyard()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), BrickyardID, "Brickyard", BROWN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
