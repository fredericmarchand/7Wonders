package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Haven extends Structure {

	public static final int HavenID = 0x59;
	
	public Haven()
	{
		super(new Resources(1, 0, 1, 0, 0, 1, 0, 0), HavenID, "Haven", YELLOW_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
