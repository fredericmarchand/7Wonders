package Structures.Cards;

import Structures.Structure;
import Structures.Effects.CardVictoryPointBonus;
import Tokens.Resources;

public class WorkersGuild extends Structure {

	public static final int WorkersGuildID = 18;
	
	public WorkersGuild()
	{
		super(new Resources(2, 1, 1, 1, 0, 0, 0, 0), WorkersGuildID, "Workers Guild", PURPLE_CARD, 3);
		effects.add(new CardVictoryPointBonus(1, true, BROWN_CARD));
	}



}
