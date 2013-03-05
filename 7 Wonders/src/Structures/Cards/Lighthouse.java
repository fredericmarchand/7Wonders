
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Lighthouse extends Structure {

	public static final int LighthouseID = 0x5B;
	
	public Lighthouse()
	{
		super(new Resources(0, 1, 0, 0, 1, 0, 0, 0), LighthouseID, "Lighthouse", YELLOW_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Caravansery.CaravanseryID);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

