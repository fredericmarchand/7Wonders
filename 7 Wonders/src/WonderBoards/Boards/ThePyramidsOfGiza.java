package WonderBoards.Boards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class ThePyramidsOfGiza extends WonderBoard {

	private WonderBoardStage stage1, stage2, stage3, stage4;
	
	public ThePyramidsOfGiza()
	{
		super(7, 1);
		stage1 = new WonderBoardStage(new Resources(0, 2, 0, 0, 0, 0, 0, 0));
		stage2 = new WonderBoardStage(new Resources(0, 0, 3, 0, 0, 0, 0, 0));
		stage3 = new WonderBoardStage(new Resources(0, 4, 0, 0, 0, 0, 0, 0));
		stage4 = null;
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public ThePyramidsOfGiza(int side)
	{
		super(7, side);
		stage1 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 2, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 2, 0, 0, 0, 0, 0)));
		stage2 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 3, 0, 0, 0, 0, 0) : new Resources(0, 3, 0, 0, 0, 0, 0, 0)));
		stage3 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 4, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 0, 3, 0, 0, 0, 0)));
		if ( side == SIDE_B ) stage4 = new WonderBoardStage(new Resources(0, 4, 0, 0, 0, 0, 1, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
		if ( side == SIDE_B ) stages.add(stage4);
	}
	
	public boolean isBuilt()
	{
		if ( side == SIDE_A )
			return (stage1.isBuilt() & stage2.isBuilt() & stage3.isBuilt());
		return (stage1.isBuilt() & stage2.isBuilt() & stage3.isBuilt() & stage4.isBuilt());
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
	
	public boolean BuildStage4(Structure card, Resources funds)
	{
		if ( (side == SIDE_B) && stage1.isBuilt() && stage2.isBuilt() && stage3.isBuilt() && !stage4.isBuilt() )
			return stage4.build(card, funds);
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
