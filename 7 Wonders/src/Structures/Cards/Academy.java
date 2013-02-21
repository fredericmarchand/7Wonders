package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Academy extends Structure {

	public static final int AcademyID = 0x68;
	
	public Academy()
	{
		super(new Resources(0, 3, 0, 0, 1, 0, 0, 0), AcademyID, "Academy", GREEN_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
