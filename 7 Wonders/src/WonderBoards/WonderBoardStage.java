package WonderBoards;

import Structures.Structure;
import Tokens.Resources;
import Structures.Effects.*;
import java.util.ArrayList;

public class WonderBoardStage {

	private Resources resourceCost;
	@SuppressWarnings("unused")
	private Structure card;
	private boolean built;
	private ArrayList<SpecialEffect> effects;
	
	public WonderBoardStage()
	{
		resourceCost = null;
		card = null;
		built = false;
		effects = new ArrayList<SpecialEffect>();
	}
	
	public WonderBoardStage(Resources cost)
	{
		built = false;
		card = null;
		resourceCost = cost;
	}
	
	
	public Resources getResourceCost()
	{
		return resourceCost;
	}	
	
	public boolean isBuilt()
	{
		return built;
	}
	
	public ArrayList<SpecialEffect> getEffects()
	{
		return effects;
	}
	
	public boolean build(Structure card, Resources resources)
	{
		if ( resourceCost.canAfford(resources) )
		{
			this.card = new Structure(card);
			built = true;
			return true;
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
