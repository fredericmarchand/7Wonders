package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Walls extends Structure {

	public static final int WallsID = 44;
	
	public Walls()
	{
		super(new Resources(0, 3, 0, 0, 0, 0, 0, 0), WallsID, "Walls", RED_CARD, 2);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
