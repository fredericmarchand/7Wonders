package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardCoinBonus;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class ChamberOfCommerce extends Structure {

	public static final int ChamberOfCommerceID = 62;
	
	public ChamberOfCommerce()
	{
		super(new Resources(0, 0, 0, 2, 0, 0, 1, 0), ChamberOfCommerceID, "Chamber Of Commerce", YELLOW_CARD, 3);
		effects.add(new CardCoinBonus(2, GREY_CARD, false));
		effects.add(new CardVictoryPointBonus(2, false, GREY_CARD));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
