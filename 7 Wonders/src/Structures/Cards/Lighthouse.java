
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardCoinBonus;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Lighthouse extends Structure {

	public static final int LighthouseID = 61;
	
	public Lighthouse()
	{
		super(new Resources(0, 1, 0, 0, 1, 0, 0, 0), LighthouseID, "Lighthouse", YELLOW_CARD, 3);
		effects.add(new CardVictoryPointBonus(1, false, YELLOW_CARD));
		effects.add(new CardCoinBonus(1, YELLOW_CARD, false));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Caravansery.CaravanseryID);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

