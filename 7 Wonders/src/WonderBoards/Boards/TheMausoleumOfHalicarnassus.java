package WonderBoards.Boards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class TheMausoleumOfHalicarnassus extends WonderBoard {

	
	public TheMausoleumOfHalicarnassus()
	{
		super(6, 1);
		WonderBoardStage stage1 = new WonderBoardStage(new Resources(0, 0, 0, 2, 0, 0, 0, 0));
		WonderBoardStage stage2 = new WonderBoardStage(new Resources(3, 0, 0, 0, 0, 0, 0, 0));
		WonderBoardStage stage3 = new WonderBoardStage(new Resources(0, 0, 0, 0, 0, 2, 0, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public TheMausoleumOfHalicarnassus(int side)
	{
		super(6, side);
		WonderBoardStage stage1 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 0, 2, 0, 0, 0, 0) : new Resources(2, 0, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage2 = new WonderBoardStage((side == SIDE_A ? new Resources(3, 0, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 0, 3, 0, 0, 0, 0)));
		WonderBoardStage stage3 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 0, 0, 0, 2, 0, 0) : new Resources(0, 0, 0, 0, 1, 1, 1, 0)));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public boolean isBuilt()
	{
		return (stages.get(1).isBuilt() & stages.get(2).isBuilt() & stages.get(3).isBuilt());
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
