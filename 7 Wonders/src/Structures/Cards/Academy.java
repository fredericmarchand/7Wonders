package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Academy extends Structure {

	public static final int AcademyID = 0x68;
	
	public Academy()
	{
		super(new Resources(0, 3, 0, 0, 1, 0, 0, 0), AcademyID, "Academy", GREEN_CARD, 3);
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(School.SchoolID);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
