package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class WorkersGuild extends Structure {

	public static final int WorkersGuildID = 0x76;
	
	public WorkersGuild()
	{
		super(new Resources(2, 1, 1, 1, 0, 0, 0, 0), WorkersGuildID, "Workers Guild", PURPLE_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
