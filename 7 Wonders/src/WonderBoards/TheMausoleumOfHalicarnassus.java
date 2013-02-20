package WonderBoards;

import Structures.Structure;
import Resources.Resources;

public class TheMausoleumOfHalicarnassus extends WonderBoard {

	private WonderBoardStage stage1, stage2, stage3;
	
	public TheMausoleumOfHalicarnassus()
	{
		super(6, 1);
		stage1 = new WonderBoardStage(new Resources(0, 0, 0, 2, 0, 0, 0, 0));
		stage2 = new WonderBoardStage(new Resources(3, 0, 0, 0, 0, 0, 0, 0));
		stage3 = new WonderBoardStage(new Resources(0, 0, 0, 0, 0, 2, 0, 0));
	}
	
	public TheMausoleumOfHalicarnassus(int side)
	{
		super(6, side);
		stage1 = new WonderBoardStage((Side == 1 ? new Resources(0, 0, 0, 2, 0, 0, 0, 0) : new Resources(2, 0, 0, 0, 0, 0, 0, 0)));
		stage2 = new WonderBoardStage((Side == 1 ? new Resources(3, 0, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 0, 3, 0, 0, 0, 0)));
		stage3 = new WonderBoardStage((Side == 1 ? new Resources(0, 0, 0, 0, 0, 2, 0, 0) : new Resources(0, 0, 0, 0, 1, 1, 1, 0)));
	}
	
	public boolean isBuilt()
	{
		return (stage1.isBuilt() & stage2.isBuilt() & stage3.isBuilt());
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
		if ( stage1.isBuilt() && stage2.isBuilt() && !stage3.isBuilt() )
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
