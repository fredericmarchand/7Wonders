
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Aqueduct extends Structure {

	public static final int AqueductID = 32;
	
	public Aqueduct()
	{
		super(new Resources(0, 3, 0, 0, 0, 0, 0, 0), AqueductID, "Aqueduct", BLUE_CARD, 2);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Baths.BathsID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
