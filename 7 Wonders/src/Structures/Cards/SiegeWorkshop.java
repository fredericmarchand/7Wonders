
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class SiegeWorkshop extends Structure {

	public static final int SiegeWorkshopID = 0x1A;
	
	public SiegeWorkshop()
	{
		super(new Resources(0, 0, 1, 3, 0, 0, 0, 0), SiegeWorkshopID, "Siege Workshop", RED_CARD, 3);
	}

	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Laboratory.LaboratoryID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

