package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class ChamberOfCommerce extends Structure {

	public static final int ChamberOfCommerceID = 0x5A;
	
	public ChamberOfCommerce()
	{
		super(new Resources(0, 0, 0, 2, 0, 0, 1, 0), ChamberOfCommerceID, "Chamber Of Commerce", YELLOW_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
