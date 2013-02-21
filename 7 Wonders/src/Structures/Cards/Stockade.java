package Structures.Cards;

import Resources.Resources;
import Structures.Structure;

public class Stockade extends Structure{

	public static final int StockadeID = 0x13;
	
	public Stockade()
	{
		super(new Resources(0, 0, 1, 0, 0, 0, 0, 0), StockadeID, "Stockade", RED_CARD, 1);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
