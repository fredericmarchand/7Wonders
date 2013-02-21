<<<<<<< ef5494ce7084e41394288e7084e3ea4669f8181b
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Academy extends Structure {

	public static final int AcademyID = 0x68;
	
	public Academy()
	{
		super(new Resources(0, 3, 0, 0, 1, 0, 0, 0), AcademyID, "Academy", GREEN_CARD, 3);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(School.SchoolID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
=======
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Academy extends Structure {

	public static final int AcademyID = 0x68;
	
	public Academy()
	{
		super(new Resources(0, 3, 0, 0, 1, 0, 0, 0), AcademyID, "Academy", GREEN_CARD, 3);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(School.SchoolID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
>>>>>>> local
