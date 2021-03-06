package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class TradersGuild extends Structure {

	public static final int TradersGuildID = 20;
	
	public TradersGuild()
	{
		super(new Resources(0, 0, 0, 0, 1, 1, 1, 0), TradersGuildID, "Traders Guild", PURPLE_CARD, 3);
		effects.add(new CardVictoryPointBonus(1, true, YELLOW_CARD));
	}



}
