
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Forum extends Structure {

	public static final int ForumID = 56;
	
	public Forum()
	{
		super(new Resources(0, 0, 0, 2, 0, 0, 0, 0), ForumID, "Forum", YELLOW_CARD, 2);
		effects.add(new ResourceChoice(0, 0, 0, 0, 1, 1, 1, false));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return (wb.containsCard(EastTradingPost.EastTradingPostID) | wb.containsCard(WestTradingPost.WestTradingPostID));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

