package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;

public class ScientistsGuild extends Structure {

	public static final int ScientistsGuildID = 25;
	
	public ScientistsGuild()
	{
		super(new Resources(2, 0, 2, 0, 0, 0, 1, 0), ScientistsGuildID, "Scientists Guild", PURPLE_CARD, 3);
		effects.add(new ResourceChoice(0, 0, 0, 0, 1, 1, 1, true));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
