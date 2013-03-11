package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardCoinBonus;
import Tokens.Resources;

public class Bazar extends Structure {

	public static final int BazarID = 59;
	
	public Bazar()
	{
		super(new Resources(), BazarID, "Bazar", YELLOW_CARD, 2);
		effects.add(new CardCoinBonus(2, GREY_CARD, true));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
