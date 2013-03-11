package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class MagistratesGuild extends Structure {

	public static final int MagistratesGuildID = 26;
	
	public MagistratesGuild()
	{
		super(new Resources(0, 1, 3, 0, 0, 1, 0, 0), MagistratesGuildID, "Magistrates Guild", PURPLE_CARD, 3);
		effects.add(new CardVictoryPointBonus(1, true, BLUE_CARD));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
