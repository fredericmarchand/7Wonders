package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class BuildersGuild extends Structure {

	public static final int BuildersGuildID = 0x73;
	
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
