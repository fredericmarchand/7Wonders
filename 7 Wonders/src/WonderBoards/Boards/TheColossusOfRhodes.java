package WonderBoards.Boards;

import Structures.Structure;
import Structures.Effects.CoinBonus;
import Structures.Effects.ResourcesBonus;
import Structures.Effects.ShieldBonus;
import Structures.Effects.VictoryPointBonus;
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
		startResource = new ResourcesBonus(1, 0, 0, 0, 0, 0, 0);
	}
	
	public TheColossusOfRhodes(int side)
	{
		super(1, side);
		WonderBoardStage stage1 = new WonderBoardStage((side == SIDE_A? new Resources(0, 0, 2, 0, 0, 0, 0, 0) : new Resources(0, 3, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage2 = new WonderBoardStage((side == SIDE_A? new Resources(0, 0, 0, 3, 0, 0, 0, 0) : new Resources(4, 0, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage3;
		startResource = new ResourcesBonus(1, 0, 0, 0, 0, 0, 0);
		
		if ( side == SIDE_A )
		{
			stage1.getEffects().add(new VictoryPointBonus(3));
			stage2.getEffects().add(new ShieldBonus(2));
		}
		else
		{
			stage1.getEffects().add(new VictoryPointBonus(3));
			stage1.getEffects().add(new ShieldBonus(1));
			stage1.getEffects().add(new CoinBonus(3));
			stage2.getEffects().add(new VictoryPointBonus(4));
			stage2.getEffects().add(new ShieldBonus(1));
			stage2.getEffects().add(new CoinBonus(4));
		}
		
		stages.add(stage1);
		stages.add(stage2);
		
		if ( side == SIDE_A ) 
		{
			stage3 = new WonderBoardStage(new Resources(4, 0, 0, 0, 0, 0, 0, 0));
			stage3.getEffects().add(new VictoryPointBonus(7));
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
	


}
