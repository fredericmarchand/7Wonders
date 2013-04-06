package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class SpiesGuild extends Structure {

	public static final int SpiesGuildID = 22;
	
	public SpiesGuild()
	{
		super(new Resources(0, 0, 0, 3, 1, 0, 0, 0), SpiesGuildID, "Spies Guild", PURPLE_CARD, 3);
		effects.add(new CardVictoryPointBonus(1, true, RED_CARD));
	}



}
