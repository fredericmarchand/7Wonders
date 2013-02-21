package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class ClayPit extends Structure {

	public static final int ClayPitID = 0x32;
	
	public ClayPit()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), ClayPitID, "Clay Pit", BROWN_CARD, 1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
