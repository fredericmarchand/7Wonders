
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Stables extends Structure {

	public static final int StablesID = 49;
	
	public Stables()
	{
		super(new Resources(1, 0, 1, 1, 0, 0, 0, 0), StablesID, "Stables", RED_CARD, 2);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Apothecary.ApothecaryID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

