package WonderBoards;

import Structures.Structure;
import Tokens.Resources;

public class WonderBoardStage {

	private Resources resourceCost;
	@SuppressWarnings("unused")
	private Structure card;
	private boolean built;
	
	public WonderBoardStage()
	{
		resourceCost = null;
		card = null;
		built = false;
	}
	
	public WonderBoardStage(Resources cost)
	{
		built = false;
		card = null;
		resourceCost = cost;
	}
	
	
	public Resources GetResourceCost()
	{
		return resourceCost;
	}	
	
	public boolean isBuilt()
	{
		return built;
	}
	
	public boolean Build(Structure card, Resources resources)
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
