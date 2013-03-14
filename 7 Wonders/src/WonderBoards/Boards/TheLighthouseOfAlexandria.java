package WonderBoards.Boards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Structures.Effects.ResourcesBonus;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class TheLighthouseOfAlexandria extends WonderBoard {

	
	public TheLighthouseOfAlexandria()
	{
		super(2, 1);
		WonderBoardStage stage1 = new WonderBoardStage(new Resources(0, 2, 0, 0, 0, 0, 0, 0));
		WonderBoardStage stage2 = new WonderBoardStage(new Resources(2, 0, 0, 0, 0, 0, 0, 0));
		WonderBoardStage stage3 = new WonderBoardStage(new Resources(0, 0, 0, 0, 2, 0, 0, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);

		startResource = new ResourcesBonus(0, 0, 0, 0, 1, 0, 0);
	}
	
	public TheLighthouseOfAlexandria(int side)
	{
		super(2, side);
		WonderBoardStage stage1 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 2, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 0, 2, 0, 0, 0, 0)));
		WonderBoardStage stage2 = new WonderBoardStage((side == SIDE_A ? new Resources(2, 0, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 2, 0, 0, 0, 0, 0)));
		WonderBoardStage stage3 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 0, 0, 2, 0, 0, 0) : new Resources(0, 3, 0, 0, 0, 0, 0, 0)));
		
		startResource = new ResourcesBonus(0, 0, 0, 0, 1, 0, 0);
		
		if ( side == SIDE_A )
		{
			stage1.getEffects().add(new VictoryPointBonus(3));
			stage2.getEffects().add(new ResourceChoice(1, 1, 1, 1, 0, 0, 0, false));
			stage3.getEffects().add(new VictoryPointBonus(7));
		}
		else
		{
			stage1.getEffects().add(new ResourceChoice(1, 1, 1, 1, 0, 0, 0, false));
			stage2.getEffects().add(new ResourceChoice(0, 0, 0, 0, 1, 1, 1, false));
			stage3.getEffects().add(new VictoryPointBonus(7));
		}
		
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
