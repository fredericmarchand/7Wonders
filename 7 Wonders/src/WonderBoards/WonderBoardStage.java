package WonderBoards;

import java.util.ArrayList;

import Structures.Structure;
import Structures.Effects.SpecialEffect;
import Tokens.Resources;

public class WonderBoardStage {

	private Resources resourceCost;
	@SuppressWarnings("unused")
	private Structure card;
	private boolean built;
	private ArrayList<SpecialEffect> effects;

	
	public WonderBoardStage()
	{
		resourceCost = new Resources();
		card = null;
		built = false;
		effects = new ArrayList<SpecialEffect>();
	}
	
	public WonderBoardStage(Resources cost)
	{
		built = false;
		card = null;
		resourceCost = cost;
		effects = new ArrayList<SpecialEffect>();
	}
	
	
	public Resources getResourceCost()
	{
		return resourceCost;
	}	
	
	public ArrayList<SpecialEffect> getEffects()
	{
		return effects;
	}
	
	public boolean isBuilt()
	{
		return built;
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
