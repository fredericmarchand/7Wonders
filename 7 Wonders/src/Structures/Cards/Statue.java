
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Statue extends Structure {

	public static final int StatueID = 0x25;
	
	public Statue()
	{
		super(new Resources(2, 0, 1, 0, 0, 0, 0, 0), StatueID, "Statue", BLUE_CARD, 2);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Theater.TheaterID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

