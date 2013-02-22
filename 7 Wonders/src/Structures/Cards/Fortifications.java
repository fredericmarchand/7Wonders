
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Fortifications extends Structure {

	public static final int FortificationsID = 0x19;
	
	public Fortifications()
	{
		super(new Resources(3, 1, 0, 0, 0, 0, 0, 0), FortificationsID, "Fortifications", RED_CARD, 3);
	}

	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Walls.WallsID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

