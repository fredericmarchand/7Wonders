package Structures;

import Resources.Resources;
import WonderBoards.WonderBoard;

public class Study extends Structure {

	public static final int StudyID = 0x6B;
	
	public Study()
	{
		super(new Resources(0, 0, 1, 0, 0, 1, 1, 0), StudyID, "Study", GREEN_CARD, 3);
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
