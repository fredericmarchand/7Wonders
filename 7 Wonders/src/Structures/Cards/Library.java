
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Library extends Structure {

	public static final int LibraryID = 69;
	
	public Library()
	{
		super(new Resources(0, 2, 0, 0, 0, 0, 1, 0), LibraryID, "Library", GREEN_CARD, 2);
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

