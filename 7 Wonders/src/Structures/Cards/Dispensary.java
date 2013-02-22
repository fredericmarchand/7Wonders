package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Dispensary extends Structure {

	public static final int DispensaryID = 0x64;
	
	public Dispensary()
	{
		super(new Resources(2, 0, 0, 0, 1, 0, 0, 0), DispensaryID, "Dispensary", GREEN_CARD, 2);
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Apothecary.ApothecaryID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
