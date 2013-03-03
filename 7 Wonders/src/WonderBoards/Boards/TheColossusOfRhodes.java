package WonderBoards.Boards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class TheColossusOfRhodes extends WonderBoard {

	
	public TheColossusOfRhodes()
	{
		super(1, 1);
		WonderBoardStage stage1 = new WonderBoardStage(new Resources(0, 0, 2, 0, 0, 0, 0, 0));
		WonderBoardStage stage2 = new WonderBoardStage(new Resources(0, 0, 0, 3, 0, 0, 0, 0));
		WonderBoardStage stage3 = new WonderBoardStage(new Resources(4, 0, 0, 0, 0, 0, 0, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public TheColossusOfRhodes(int side)
	{
		super(1, side);
		WonderBoardStage stage1 = new WonderBoardStage((side == SIDE_A? new Resources(0, 0, 2, 0, 0, 0, 0, 0) : new Resources(0, 3, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage2 = new WonderBoardStage((side == SIDE_A? new Resources(0, 0, 0, 3, 0, 0, 0, 0) : new Resources(4, 0, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage3;
		stages.add(stage1);
		stages.add(stage2);
		
		if ( side == SIDE_A ) 
		{
			stage3 = new WonderBoardStage(new Resources(4, 0, 0, 0, 0, 0, 0, 0));
			stages.add(stage3);
		}
	}
	
	public boolean isBuilt()
	{
		if ( side == SIDE_A )
			return (stages.get(1).isBuilt() & stages.get(2).isBuilt() & stages.get(3).isBuilt());
		return (stages.get(1).isBuilt() & stages.get(2).isBuilt());
	}
	
	public boolean buildStage(Structure card, Resources funds)
	{
		for (WonderBoardStage s : stages)
		{
			if (!s.isBuilt())
			{
				return s.build(card, funds);
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
