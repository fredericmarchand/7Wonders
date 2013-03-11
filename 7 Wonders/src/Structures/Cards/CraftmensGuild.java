package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class CraftmensGuild extends Structure {

	public static final int CraftmensGuildID = 19;
	
	public CraftmensGuild()
	{
		super(new Resources(2, 2, 0, 0, 0, 0, 0, 0), CraftmensGuildID, "Craftmens Guild", PURPLE_CARD, 3);
		effects.add(new CardVictoryPointBonus(2, true, GREY_CARD));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
