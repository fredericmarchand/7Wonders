package Structures;

import Resources.Resources;
import WonderBoards.WonderBoard;

public class Aqueduct extends Structure {

	public static final int AqueductID = 0x27;
	
	public Aqueduct()
	{
		super(new Resources(0, 3, 0, 0, 0, 0, 0, 0), AqueductID, "Aqueduct", BLUE_CARD, 2);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Baths.BathsID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
