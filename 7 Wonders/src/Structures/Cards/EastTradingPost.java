package Structures.Cards;

import Structures.Structure;
import Structures.Effects.TradingPerks;
import Tokens.Resources;

public class EastTradingPost extends Structure {

	public static final int EastTradingPostID = 53;
	
	public EastTradingPost()
	{
		super(new Resources(), EastTradingPostID, "East Trading Post", YELLOW_CARD, 1);
		effects.add(new TradingPerks(2, 1));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
