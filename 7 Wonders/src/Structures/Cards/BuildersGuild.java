package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class BuildersGuild extends Structure {

	public static final int BuildersGuildID = 27;
	
	public BuildersGuild()
	{
		super(new Resources(0, 2, 0, 2, 1, 0, 0, 0), BuildersGuildID, "Builders Guild", PURPLE_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
