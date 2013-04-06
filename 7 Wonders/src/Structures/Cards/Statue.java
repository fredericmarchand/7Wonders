
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Statue extends Structure {

	public static final int StatueID = 34;
	
	public Statue()
	{
		super(new Resources(2, 0, 1, 0, 0, 0, 0, 0), StatueID, "Statue", BLUE_CARD, 2);
		effects.add(new VictoryPointBonus(4));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Theater.TheaterID);
	}
	


}

