
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Observatory extends Structure {

	public static final int ObservatoryID = 0x69;
	
	public Observatory()
	{
		super(new Resources(2, 0, 0, 0, 1, 1, 0, 0), ObservatoryID, "Observatory", GREEN_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Laboratory.LaboratoryID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

