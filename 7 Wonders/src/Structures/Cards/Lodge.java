
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Lodge extends Structure {

	public static final int LodgeID = 0x6A;
	
	public Lodge()
	{
		super(new Resources(0, 0, 0, 2, 0, 1, 1, 0), LodgeID, "Lodge", GREEN_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Dispensary.DispensaryID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

