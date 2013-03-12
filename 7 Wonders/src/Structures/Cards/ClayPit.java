package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import Structures.Effects.*;

public class ClayPit extends Structure {

	public static final int ClayPitID = 7;
	
	public ClayPit()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), ClayPitID, "Clay Pit", BROWN_CARD, 1);
		effects.add(new ResourceChoice(1, 0, 0, 1, 0, 0, 0, true));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
