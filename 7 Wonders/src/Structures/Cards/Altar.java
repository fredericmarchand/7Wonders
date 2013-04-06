package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;



public class Altar extends Structure {

	public static final int AltarID = 30;
	
	public Altar()
	{
		super(new Resources(), AltarID, "Altar", BLUE_CARD, 1);
		effects.add(new VictoryPointBonus(2));
		
	}




}
