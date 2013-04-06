
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Lodge extends Structure {

	public static final int LodgeID = 71;
	
	public Lodge()
	{
		super(new Resources(0, 0, 0, 2, 0, 1, 1, 0), LodgeID, "Lodge", GREEN_CARD, 3);
		effects.add(new ScientificSymbolBonus(1, 2, false));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Dispensary.DispensaryID);
	}



}

