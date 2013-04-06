
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Observatory extends Structure {

	public static final int ObservatoryID = 72;
	
	public Observatory()
	{
		super(new Resources(2, 0, 0, 0, 1, 1, 0, 0), ObservatoryID, "Observatory", GREEN_CARD, 3);
		effects.add(new ScientificSymbolBonus(2, 2, false));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Laboratory.LaboratoryID);
	}



}

