package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class StonePit extends Structure {

	public static final int StonePitID = 0x36;
	
	public StonePit()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 0), StonePitID, "Stone Pit", BROWN_CARD, 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
