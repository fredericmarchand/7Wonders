package Structures.Cards;

import Structures.Structure;
import Structures.Effects.MilitaryDefeatBonus;
import Tokens.Resources;

public class StrategistsGuild extends Structure {

	public static final int StrategistsGuildID = 23;
	
	public StrategistsGuild()
	{
		super(new Resources(2, 1, 0, 0, 0, 1, 0, 0), StrategistsGuildID, "Strategists Guild", PURPLE_CARD, 3);
		effects.add(new MilitaryDefeatBonus());
	}



}
