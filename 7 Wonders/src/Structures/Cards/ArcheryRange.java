package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class ArcheryRange extends Structure {

	
	public static final int ArcheryRangeID = 0x14;
	
	public ArcheryRange()
	{
		super(new Resources(1, 0, 2, 0, 0, 0, 0, 0), ArcheryRangeID, "Archery Range", RED_CARD, 2);
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
