
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Laboratory extends Structure {

	public static final int LaboratoryID = 0x65;
	
	public Laboratory()
	{
		super(new Resources(0, 0, 0, 2, 0, 0, 1, 0), LaboratoryID, "Laboratory", GREEN_CARD, 2);
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Workshop.WorkshopID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

