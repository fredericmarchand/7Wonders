package WonderBoards.Boards;

import Structures.Structure;
import Structures.Effects.CopyGuild;
import Structures.Effects.FreeConstruction;
import Structures.Effects.ResourcesBonus;
import Structures.Effects.TradingPerks;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class TheStatueOfZeusInOlympia extends WonderBoard {

	
	public TheStatueOfZeusInOlympia()
	{
		super(5, 1);
		WonderBoardStage stage1 = new WonderBoardStage(new Resources(0, 0, 2, 0, 0, 0, 0, 0));
		WonderBoardStage stage2 = new WonderBoardStage(new Resources(0, 2, 0, 0, 0, 0, 0, 0));
		WonderBoardStage stage3 = new WonderBoardStage(new Resources(2, 0, 0, 0, 0, 0, 0, 0));
		
		if ( side == SIDE_A )
		{
			stage1.getEffects().add(new VictoryPointBonus(3));
			stage2.getEffects().add(new FreeConstruction());
			stage3.getEffects().add(new VictoryPointBonus(7));
		}
		else
		{
			stage1.getEffects().add(new TradingPerks(3, 0));
			stage2.getEffects().add(new VictoryPointBonus(5));
			stage3.getEffects().add(new CopyGuild());
		}
		
		startResource = new ResourcesBonus(0, 0, 1, 0, 0, 0, 0);
		
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public TheStatueOfZeusInOlympia(int side)
	{
		super(5, side);
		WonderBoardStage stage1 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 2, 0, 0, 0, 0, 0) : new Resources(0, 0, 2, 0, 0, 0, 0, 0)));
		WonderBoardStage stage2 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 2, 0, 0, 0, 0, 0, 0) : new Resources(0, 2, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage3 = new WonderBoardStage((side == SIDE_A ? new Resources(2, 0, 0, 0, 0, 0, 0, 0) : new Resources(2, 0, 0, 0, 0, 1, 0, 0)));
		
		if ( side == SIDE_A )
		{
			stage1.getEffects().add(new VictoryPointBonus(3));
			stage2.getEffects().add(new FreeConstruction());
			stage3.getEffects().add(new VictoryPointBonus(7));
		}
		else
		{
			stage1.getEffects().add(new TradingPerks(3, 0));
			stage2.getEffects().add(new VictoryPointBonus(5));
			stage3.getEffects().add(new CopyGuild());
		}
		
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
		
		startResource = new ResourcesBonus(0, 0, 1, 0, 0, 0, 0);
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
	


}
