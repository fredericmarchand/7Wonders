
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardCoinBonus;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Haven extends Structure {

	public static final int HavenID = 60;
	
	public Haven()
	{
		super(new Resources(1, 0, 1, 0, 0, 1, 0, 0), HavenID, "Haven", YELLOW_CARD, 3);
		effects.add(new CardCoinBonus(1, BROWN_CARD, false));
		effects.add(new CardVictoryPointBonus(1, false, BROWN_CARD));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Forum.ForumID);
	}




}

