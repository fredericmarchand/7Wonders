package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;

public class Stockade extends Structure{

	public static final int StockadeID = 42;
	
	public Stockade()
	{
		super(new Resources(0, 0, 1, 0, 0, 0, 0, 0), StockadeID, "Stockade", RED_CARD, 1);
		effects.add(new ShieldBonus(1));
	}
	
	
	


}
