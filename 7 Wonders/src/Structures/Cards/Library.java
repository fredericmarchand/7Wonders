package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Library extends Structure {

	public static final int LibraryID = 0x66;
	
	public Library()
	{
		super(new Resources(0, 2, 0, 0, 0, 0, 1, 0), LibraryID, "Library", GREEN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
