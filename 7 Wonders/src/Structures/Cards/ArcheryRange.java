package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class ArcheryRange extends Structure {

	
	public static final int ArcheryRangeID = 50;
	
	public ArcheryRange()
	{
		super(new Resources(1, 0, 2, 0, 0, 0, 0, 0), ArcheryRangeID, "Archery Range", RED_CARD, 2);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Workshop.WorkshopID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
