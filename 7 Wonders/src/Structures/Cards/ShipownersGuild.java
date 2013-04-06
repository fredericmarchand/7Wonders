package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class ShipownersGuild extends Structure {

	public static final int ShipownersGuildID = 24;
	
	public ShipownersGuild()
	{
		super(new Resources(0, 0, 3, 0, 1, 0, 1, 0), ShipownersGuildID, "Shipowners Guild", PURPLE_CARD, 3);
		effects.add(new CardVictoryPointBonus(1, false, BROWN_CARD));
		effects.add(new CardVictoryPointBonus(1, false, GREY_CARD));
		effects.add(new CardVictoryPointBonus(1, false, PURPLE_CARD));
	}



}
