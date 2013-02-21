<<<<<<< ef5494ce7084e41394288e7084e3ea4669f8181b
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
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return (wb.ContainsCard(EastTradingPost.EastTradingPostID) | wb.ContainsCard(WestTradingPost.WestTradingPostID));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
=======
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
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return (wb.ContainsCard(EastTradingPost.EastTradingPostID) | wb.ContainsCard(WestTradingPost.WestTradingPostID));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
>>>>>>> local
