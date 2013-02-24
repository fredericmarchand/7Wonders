package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Mine extends Structure {

	public static final int MineID = 0x39;
	
	public Mine()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), MineID, "Mine", BROWN_CARD, 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
