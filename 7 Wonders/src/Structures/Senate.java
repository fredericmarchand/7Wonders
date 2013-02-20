package Structures;

import Resources.Resources;
import WonderBoards.WonderBoard;

public class Senate extends Structure {
	
	public static final int SenateID = 0x2D;
	
	public Senate()
	{
		super(new Resources(1, 1, 2, 0, 0, 0, 0, 0), SenateID, "Senate", BLUE_CARD, 3);
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
