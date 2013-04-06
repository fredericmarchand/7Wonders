package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Dispensary extends Structure {

	public static final int DispensaryID = 67;
	
	public Dispensary()
	{
		super(new Resources(2, 0, 0, 0, 1, 0, 0, 0), DispensaryID, "Dispensary", GREEN_CARD, 2);
		effects.add(new ScientificSymbolBonus(1, 2, false));
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Apothecary.ApothecaryID);
	}
	


}
