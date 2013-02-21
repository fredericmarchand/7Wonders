
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class University extends Structure {

	public static final int UniversityID = 0x6C;
	
	public University()
	{
		super(new Resources(0, 0, 2, 0, 1, 0, 1, 0), UniversityID, "University", GREEN_CARD, 3);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Library.LibraryID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

