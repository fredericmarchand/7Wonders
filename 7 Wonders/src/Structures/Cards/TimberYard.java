package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class TimberYard extends Structure {

	public static final int TimberYardID = 0x38;
	
	public TimberYard()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), TimberYardID, "Timber Yard", BROWN_CARD, 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
