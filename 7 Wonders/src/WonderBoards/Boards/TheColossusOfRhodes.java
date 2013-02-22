package WonderBoards.Boards;

import Structures.Structure;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;
import Resources.Resources;

public class TheColossusOfRhodes extends WonderBoard {

	private WonderBoardStage stage1, stage2, stage3;
	
	public TheColossusOfRhodes()
	{
		super(1, 1);
		stage1 = new WonderBoardStage(new Resources(0, 0, 2, 0, 0, 0, 0, 0));
		stage2 = new WonderBoardStage(new Resources(0, 0, 0, 3, 0, 0, 0, 0));
		stage3 = new WonderBoardStage(new Resources(4, 0, 0, 0, 0, 0, 0, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public TheColossusOfRhodes(int side)
	{
		super(1, side);
		stage1 = new WonderBoardStage((side == SIDE_A? new Resources(0, 0, 2, 0, 0, 0, 0, 0) : new Resources(0, 3, 0, 0, 0, 0, 0, 0)));
		stage2 = new WonderBoardStage((side == SIDE_A? new Resources(0, 0, 0, 3, 0, 0, 0, 0) : new Resources(4, 0, 0, 0, 0, 0, 0, 0)));
		if ( side == SIDE_A ) stage3 = new WonderBoardStage(new Resources(4, 0, 0, 0, 0, 0, 0, 0));
		else stage3 = null;
		stages.add(stage1);
		stages.add(stage2);
		if ( side == SIDE_A ) stages.add(stage3);
	}
	
	public boolean isBuilt()
	{
		if ( side == SIDE_A )
			return (stage1.isBuilt() & stage2.isBuilt() & stage3.isBuilt());
		return (stage1.isBuilt() & stage2.isBuilt());
	}
	
	public boolean BuildStage1(Structure card, Resources funds)
	{
		if ( !stage1.isBuilt() )
			return stage1.Build(card, funds);
		return false;
	}
	
	public boolean BuildStage2(Structure card, Resources funds)
	{
		if ( stage1.isBuilt() && !stage2.isBuilt() )
			return stage2.Build(card, funds);
		return false;
	}
	
	public boolean BuildStage3(Structure card, Resources funds)
	{
		if ( (side == SIDE_A) && stage1.isBuilt() && stage2.isBuilt() && !stage3.isBuilt() )
			return stage3.Build(card, funds);
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
