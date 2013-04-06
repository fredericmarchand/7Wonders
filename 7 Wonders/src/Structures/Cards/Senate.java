
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Senate extends Structure {
	
	public static final int SenateID = 40;
	
	public Senate()
	{
		super(new Resources(1, 1, 2, 0, 0, 0, 0, 0), SenateID, "Senate", BLUE_CARD, 3);
		effects.add(new VictoryPointBonus(6));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Library.LibraryID);
	}
	


}

