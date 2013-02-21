<<<<<<< ef5494ce7084e41394288e7084e3ea4669f8181b
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

	public boolean CanBuildForFree(WonderBoard wb)
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
=======
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

	public boolean CanBuildForFree(WonderBoard wb)
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
>>>>>>> local
