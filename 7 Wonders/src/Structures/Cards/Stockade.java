package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class Stockade extends Structure{

	public static final int StockadeID = 42;
	
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
