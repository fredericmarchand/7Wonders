
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Pantheon extends Structure {

	public static final int PantheonID = 35;
	
	public Pantheon()
	{
		super(new Resources(1, 0, 0, 2, 1, 1, 1, 0), PantheonID, "Pantheon", BLUE_CARD, 3);
		effects.add(new VictoryPointBonus(7));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Temple.TempleID);
	}
	


}

