package WonderBoards;

import java.util.ArrayList;
import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Structures.Effects.SpecialEffect;
import Tokens.Resources;

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

	protected int boardID;
	protected int side;
	protected String boardName;
	protected ArrayList<Structure> redCards, blueCards, greenCards, yellowCards, purpleCards, brownGreyCards;
	protected ArrayList<WonderBoardStage> stages;
	protected ResourcesBonus startResource;
	
	//Constructors
	public WonderBoard()
	{
		boardID = NO_BOARD;
		side = NO_BOARD;
		boardName = BOARD0;
		redCards = new ArrayList<Structure>();
		blueCards = new ArrayList<Structure>();
		greenCards = new ArrayList<Structure>();
		yellowCards = new ArrayList<Structure>();
		purpleCards = new ArrayList<Structure>();
		brownGreyCards = new ArrayList<Structure>();
		stages = new ArrayList<WonderBoardStage>();
	}
	
	public WonderBoard(int boardID, int side)
	{
		this.boardID = boardID;
		this.side = side;
		setBoardName(boardID);
		redCards = new ArrayList<Structure>();
		blueCards = new ArrayList<Structure>();
		greenCards = new ArrayList<Structure>();
		yellowCards = new ArrayList<Structure>();
		purpleCards = new ArrayList<Structure>();
		brownGreyCards = new ArrayList<Structure>();
		stages = new ArrayList<WonderBoardStage>();
	}
	
	public ResourcesBonus getStartResource()
	{
		return startResource;
	}
	
	//getters
	public int getBoardID()
	{
		return boardID;
	}
	
	public int getSide()
	{
		return side;
	}
	
	public String getBoardName()
	{
		return boardName;
	}
	
	public ArrayList<WonderBoardStage> getStages()
	{
		return stages;
	}
	
	//setters
	//public void setBoardID(int id)
	//{
//		boardID = id;
//		setBoardName(id);
//	}
	
	public void setSide(int side)
	{
		this.side = side;
	}
	
	private void setBoardName(int boardID)
	{
		switch ( boardID )
		{
			case 1:
				boardName = BOARD1;
				break;
			case 2:
				boardName = BOARD2;
				break;
			case 3:
				boardName = BOARD3;
				break;
			case 4:
				boardName = BOARD4;
				break;
			case 5:
				boardName = BOARD5;
				break;
			case 6:
				boardName = BOARD6;
				break;
			case 7:
				boardName = BOARD7;
				break;
			default: boardName = BOARD0; break;
		}
	}
	
	public boolean buildStage(Structure card, Resources funds)
	{ 
		return false; 
	}
	
	//returns null if all stages are built otherwise returns the first stage that isnt built's resource cost
	public Resources getNextStageCost()
	{
		for ( WonderBoardStage s: stages )
		{
			if ( !s.isBuilt() )
				return s.getResourceCost();
		}
		return null;
	}
	
	//builds a structure on this wonderboard
	public void buildStructure(Structure s)
	{
		switch ( s.getColor() )
		{
			case Structure.RED_CARD:
				redCards.add(s);
			case Structure.BLUE_CARD:
				blueCards.add(s);
				break;
			case Structure.BROWN_CARD:
			case Structure.GREY_CARD:
				brownGreyCards.add(s);
				break;
			case Structure.YELLOW_CARD:
				yellowCards.add(s);
				break;
			case Structure.GREEN_CARD:
				greenCards.add(s);
				break;
			case Structure.PURPLE_CARD:
				purpleCards.add(s);
				break;
		}
	}
	
	//returns whether the structure is contained in any of the stacks of the wonderboard by the id of the card
	public boolean containsCard(int id)
	{
		boolean retValue = false;
		
		for( int i = 0; i < redCards.size(); ++i )
		{
			if ( redCards.get(i).getID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < blueCards.size(); ++i )
		{
			if ( blueCards.get(i).getID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < brownGreyCards.size(); ++i )
		{
			if ( brownGreyCards.get(i).getID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < yellowCards.size(); ++i )
		{
			if ( yellowCards.get(i).getID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < greenCards.size(); ++i )
		{
			if ( greenCards.get(i).getID() == id )
			{
				retValue = true;
				break;
			}
		}
		if ( retValue ) return retValue;
		for( int i = 0; i < purpleCards.size(); ++i )
		{
			if ( purpleCards.get(i).getID() == id )
			{
				retValue = true;
				break;
			}
		}

		return retValue;
	}
	
	
	public ArrayList<Structure> getRedCards()
	{
		return redCards;
	}
	
	public ArrayList<Structure> getBlueCards()
	{
		return blueCards;
	}
	
	public ArrayList<Structure> getGreenCards()
	{
		return greenCards;
	}
	
	public ArrayList<Structure> getBrownGreyCards()
	{
		return brownGreyCards;
	}
	
	public ArrayList<Structure> getYellowCards()
	{
		return yellowCards;
	}
	
	public ArrayList<Structure> getPurpleCards()
	{
		return purpleCards;
	}

	
	
	
	public int getRedCardAmount()
	{
		return redCards.size();
	}
	
	public int getBlueCardAmount()
	{
		return blueCards.size();
	}
	
	public int getBrownCardAmount()
	{
		int count = 0;
		for ( Structure s : brownGreyCards )
		{
			if ( s.getColor() == Structure.BROWN_CARD )
				++count;
		}
		return count;
	}
	
	public int getGreyCardAmount()
	{
		int count = 0;
		for ( Structure s : brownGreyCards )
		{
			if ( s.getColor() == Structure.GREY_CARD )
				++count;
		}
		return count;
	}
	
	public int getYellowCardAmount()
	{
		return yellowCards.size();
	}
	
	public int getPurpleCardAmount()
	{
		return purpleCards.size();
	}
	
	public int getGreenCardAmount()
	{
		return greenCards.size();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
