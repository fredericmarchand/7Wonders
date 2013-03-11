package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class School extends Structure {

	public static final int SchoolID = 70;
	
	public School()
	{
		super(new Resources(0, 0, 1, 0, 0, 0, 1, 0), SchoolID, "School", GREEN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
