
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Stables extends Structure {

	public static final int StablesID = 49;
	
	public Stables()
	{
		super(new Resources(1, 0, 1, 1, 0, 0, 0, 0), StablesID, "Stables", RED_CARD, 2);
		effects.add(new ShieldBonus(2));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Apothecary.ApothecaryID);
	}
	


}

