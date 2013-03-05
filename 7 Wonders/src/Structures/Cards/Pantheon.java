
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Pantheon extends Structure {

	public static final int PantheonID = 0x2B;
	
	public Pantheon()
	{
		super(new Resources(1, 0, 0, 2, 1, 1, 1, 0), PantheonID, "Pantheon", BLUE_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Temple.TempleID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

