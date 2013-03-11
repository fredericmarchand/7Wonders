package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CoinBonus;
import Tokens.Resources;

public class Tavern extends Structure {

	public static final int TavernID = 52;
	
	public Tavern()
	{
		super(new Resources(), TavernID, "Tavern", YELLOW_CARD, 1);
		effects.add(new CoinBonus(5));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
