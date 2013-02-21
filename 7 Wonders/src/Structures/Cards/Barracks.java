package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Barracks extends Structure {

	public static final int BarrackID = 0x11;
	
	public Barracks()
	{
		super(new Resources(1, 0, 0, 0, 0, 0, 0, 0), BarrackID, "Barracks", RED_CARD, 1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
