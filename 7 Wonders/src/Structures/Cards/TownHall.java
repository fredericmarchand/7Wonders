package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class TownHall extends Structure {

	public static final int TownHallID = 0x29;
	
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
