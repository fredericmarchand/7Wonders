
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Laboratory extends Structure {

	public static final int LaboratoryID = 68;
	
	public Laboratory()
	{
		super(new Resources(0, 0, 0, 2, 0, 0, 1, 0), LaboratoryID, "Laboratory", GREEN_CARD, 2);
		effects.add(new ScientificSymbolBonus(2, 2, false));
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

