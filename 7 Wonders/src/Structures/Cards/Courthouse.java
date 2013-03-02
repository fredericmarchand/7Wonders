package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Courthouse extends Structure {

	public static final int CourthouseID = 0x28;
	
	public Courthouse()
	{
		super(new Resources(0, 0, 0, 2, 0, 1, 0, 0), CourthouseID, "Courthouse", BLUE_CARD, 2);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Scriptorium.ScriptoriumID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
