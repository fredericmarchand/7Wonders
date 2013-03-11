package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class TownHall extends Structure {

	public static final int TownHallID = 37;
	
	public TownHall()
	{
		super(new Resources(1, 2, 0, 0, 1, 0, 0, 0), TownHallID, "Town Hall", BLUE_CARD, 3);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
