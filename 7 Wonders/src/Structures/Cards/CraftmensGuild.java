package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class CraftmensGuild extends Structure {

	public static final int CraftmensGuildID = 0x71;
	
	public CraftmensGuild()
	{
		super(new Resources(2, 2, 0, 0, 0, 0, 0, 0), CraftmensGuildID, "Craftmens Guild", PURPLE_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
