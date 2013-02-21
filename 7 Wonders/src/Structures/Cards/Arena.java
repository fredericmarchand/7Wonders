<<<<<<< ef5494ce7084e41394288e7084e3ea4669f8181b
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Arena extends Structure {

	public static final int ArenaID = 0x5B;
	
	public Arena()
	{
		super(new Resources(1, 2, 0, 0, 0, 0, 0, 0), ArenaID, "Arena", YELLOW_CARD, 3);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
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
=======
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Arena extends Structure {

	public static final int ArenaID = 0x5B;
	
	public Arena()
	{
		super(new Resources(1, 2, 0, 0, 0, 0, 0, 0), ArenaID, "Arena", YELLOW_CARD, 3);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
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
>>>>>>> local
