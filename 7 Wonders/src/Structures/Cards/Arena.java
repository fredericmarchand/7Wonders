package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Arena extends Structure {

	public static final int ArenaID = 63;
	
	public Arena()
	{
		super(new Resources(1, 2, 0, 0, 0, 0, 0, 0), ArenaID, "Arena", YELLOW_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Dispensary.DispensaryID);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
