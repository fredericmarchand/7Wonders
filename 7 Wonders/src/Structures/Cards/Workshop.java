package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Workshop extends Structure {

	public static final int WorkshopID = 65;
	
	public Workshop()
	{
		super(new Resources(0, 0, 0, 0, 1, 0, 0, 0), WorkshopID, "Workshop", GREEN_CARD, 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
