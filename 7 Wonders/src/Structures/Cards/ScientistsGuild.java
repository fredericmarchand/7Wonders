package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;

public class ScientistsGuild extends Structure {

	public static final int ScientistsGuildID = 25;
	
	public ScientistsGuild()
	{
		super(new Resources(2, 0, 2, 0, 0, 0, 1, 0), ScientistsGuildID, "Scientists Guild", PURPLE_CARD, 3);
		effects.add(new ScientificSymbolBonus(1, 3, true));
	}



}
