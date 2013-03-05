
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Fortifications extends Structure {

	public static final int FortificationsID = 0x19;
	
	public Fortifications()
	{
		super(new Resources(3, 1, 0, 0, 0, 0, 0, 0), FortificationsID, "Fortifications", RED_CARD, 3);
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Walls.WallsID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

