package Structures;

import Resources.Resources;
import WonderBoards.WonderBoard;

public class Gardens extends Structure {

	public static final int GardensID = 0x2C;
	
	public Gardens()
	{
		super(new Resources(0, 0, 1, 2, 0, 0, 0, 0), GardensID, "Gardens", BLUE_CARD, 3);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Statue.StatueID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
