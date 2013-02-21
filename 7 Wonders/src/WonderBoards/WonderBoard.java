package WonderBoards;

import java.util.ArrayList;
import Structures.Structure;

public class WonderBoard {

	protected static final int NO_BOARD = 0;
	
	public static final String BOARD0 = "Invalid";
	public static final String BOARD1 = "The Colossus of Rhodes";
	public static final String BOARD2 = "The Lighthouse of Alexandria";
	public static final String BOARD3 = "The Temple of Artemis in Ephesus";
	public static final String BOARD4 = "The Hanging Gardens of Babylon";
	public static final String BOARD5 = "The Statue of Zeus in Olympia";
	public static final String BOARD6 = "The Mausoleum of Halicarnassus";
	public static final String BOARD7 = "The Pyramids of Giza";
	
	protected static final int SIDE_A = 1;
	protected static final int SIDE_B = 2;

	protected int BoardID;
	protected int Side;
	protected String BoardName;
	protected ArrayList<Structure> RedCards, BlueCards, GreenCards, YellowCards, PurpleCards, BrownGreyCards;
	
	
	//Constructors
	public WonderBoard()
	{
		BoardID = NO_BOARD;
		Side = NO_BOARD;
		BoardName = BOARD0;
	}
	
	public WonderBoard(int boardID, int side)
	{
		BoardID = boardID;
		Side = side;
		SetBoardName(boardID);
	}
	
	//getters
	public int GetBoardID()
	{
		return BoardID;
	}
	
	public int GetSide()
	{
		return Side;
	}
	
	public String GetBoardName()
	{
		return BoardName;
	}
	
	//setters
	public void SetBoardID(int id)
	{
		BoardID = id;
		SetBoardName(id);
	}
	
	public void SetSide(int side)
	{
		Side = side;
	}
	
	private void SetBoardName(int boardID)
	{
		switch ( boardID )
		{
			case 1:
				BoardName = BOARD1;
				break;
			case 2:
				BoardName = BOARD2;
				break;
			case 3:
				BoardName = BOARD3;
				break;
			case 4:
				BoardName = BOARD4;
				break;
			case 5:
				BoardName = BOARD5;
				break;
			case 6:
				BoardName = BOARD6;
				break;
			case 7:
				BoardName = BOARD7;
				break;
			default: BoardName = BOARD0;
		}
	}
	
	//builds a structure on this wonderboard
	public void BuildStructure(Structure s)
	{
		switch ( s.GetColor() )
		{
			case Structure.RED_CARD:
				RedCards.add(s);
			case Structure.BLUE_CARD:
				BlueCards.add(s);
				break;
			case Structure.BROWN_CARD:
			case Structure.GREY_CARD:
				BrownGreyCards.add(s);
				break;
			case Structure.YELLOW_CARD:
				YellowCards.add(s);
				break;
			case Structure.GREEN_CARD:
				GreenCards.add(s);
				break;
			case Structure.PURPLE_CARD:
				PurpleCards.add(s);
				break;
		}
	}
	
	//returns whether the structure is contained in any of the stacks of the wonderboard by the id of the card
	public boolean ContainsCard(int id)
	{
		boolean retValue = false;
		
		for( int i = 0; i < RedCards.size(); ++i )
		{
			if ( RedCards.get(i).GetID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < BlueCards.size(); ++i )
		{
			if ( BlueCards.get(i).GetID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < BrownGreyCards.size(); ++i )
		{
			if ( BrownGreyCards.get(i).GetID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < YellowCards.size(); ++i )
		{
			if ( YellowCards.get(i).GetID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < GreenCards.size(); ++i )
		{
			if ( GreenCards.get(i).GetID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < PurpleCards.size(); ++i )
		{
			if ( PurpleCards.get(i).GetID() == id )
			{
				retValue = true;
				break;
			}
		}

		return retValue;
	}
	
	public int GetRedCardAmount()
	{
		return RedCards.size();
	}
	
	public int GetBlueCardAmount()
	{
		return BlueCards.size();
	}
	
	public int GetBrownCardAmount()
	{
		int count = 0;
		for ( Structure s : BrownGreyCards )
		{
			if ( s.GetColor() == Structure.BROWN_CARD )
				++count;
		}
		return count;
	}
	
	public int GetGreyCardAmount()
	{
		int count = 0;
		for ( Structure s : BrownGreyCards )
		{
			if ( s.GetColor() == Structure.GREY_CARD )
				++count;
		}
		return count;
	}
	
	public int GetYellowCardAmount()
	{
		return YellowCards.size();
	}
	
	public int GetPurpleCardAmount()
	{
		return PurpleCards.size();
	}
	
	public int GetGreenCardAmount()
	{
		return GreenCards.size();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
