package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;

public class PawnShop extends Structure {
	
	public static final int PawnShopID = 28;
	
	public PawnShop()
	{
		super(new Resources(), PawnShopID, "Pawnshop", BLUE_CARD, 1);
		effects.add(new VictoryPointBonus(3));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
