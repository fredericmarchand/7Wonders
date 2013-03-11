package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardCoinBonus;
import Tokens.Resources;

public class Vineyard extends Structure {

	public static final int VineyardID = 58;
	
	public Vineyard()
	{
		super(new Resources(), VineyardID, "Vineyard", YELLOW_CARD, 2);
		effects.add(new CardCoinBonus(1, BROWN_CARD, true));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
