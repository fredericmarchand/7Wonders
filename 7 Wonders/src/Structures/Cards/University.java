
package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class University extends Structure {

	public static final int UniversityID = 0x6C;
	
	public University()
	{
		super(new Resources(0, 0, 2, 0, 1, 0, 1, 0), UniversityID, "University", GREEN_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Library.LibraryID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

