package WonderBoards.Boards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class TheTempleOfArtemisInEphesus extends WonderBoard {

	private WonderBoardStage stage1, stage2, stage3;
	
	public TheTempleOfArtemisInEphesus()
	{
		super(3, 1);
		stage1 = new WonderBoardStage(new Resources(0, 2, 0, 0, 0, 0, 0, 0));
		stage2 = new WonderBoardStage(new Resources(0, 0, 2, 0, 0, 0, 0, 0));
		stage3 = new WonderBoardStage(new Resources(0, 0, 0, 0, 0, 0, 0, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public TheTempleOfArtemisInEphesus(int side)
	{
		super(3, side);
		stage1 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 2, 0, 0, 0, 0, 0, 0) : new Resources(0, 2, 0, 0, 0, 0, 0, 0)));
		stage2 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 2, 0, 0, 0, 0, 0) : new Resources(0, 0, 2, 0, 0, 0, 0, 0)));
		stage3 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 0, 0, 1, 1, 0, 0)));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public boolean isBuilt()
	{
		return (stage1.isBuilt() & stage2.isBuilt() & stage3.isBuilt());
	}
	
	public boolean BuildStage1(Structure card, Resources funds)
	{
		if ( !stage1.isBuilt() )
			return stage1.build(card, funds);
		return false;
	}
	
	public boolean BuildStage2(Structure card, Resources funds)
	{
		if ( stage1.isBuilt() && !stage2.isBuilt() )
			return stage2.build(card, funds);
		return false;
	}
	
	public boolean BuildStage3(Structure card, Resources funds)
	{
		if ( stage1.isBuilt() && stage2.isBuilt() && !stage3.isBuilt() )
			return stage3.build(card, funds);
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
