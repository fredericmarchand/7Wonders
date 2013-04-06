package Structures.Cards;

import Structures.Structure;
import Structures.Effects.TradingPerks;
import Tokens.Resources;

public class WestTradingPost extends Structure {

	public static final int WestTradingPostID = 54;
	
	public WestTradingPost()
	{
		super(new Resources(), WestTradingPostID, "West Trading Post", YELLOW_CARD, 1);
		effects.add(new TradingPerks(1, 0));
	}



}
