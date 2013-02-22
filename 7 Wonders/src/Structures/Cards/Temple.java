
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Temple extends Structure {

	public static final int TempleID = 0x26;
	
	public Temple()
	{
		super(new Resources(0, 0, 1, 1, 1, 0, 0, 0), TempleID, "Temple", BLUE_CARD, 2);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Altar.AltarID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

