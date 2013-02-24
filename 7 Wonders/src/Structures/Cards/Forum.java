
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Forum extends Structure {

	public static final int ForumID = 0x57;
	
	public Forum()
	{
		super(new Resources(0, 0, 0, 2, 0, 0, 0, 0), ForumID, "Forum", YELLOW_CARD, 2);
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

