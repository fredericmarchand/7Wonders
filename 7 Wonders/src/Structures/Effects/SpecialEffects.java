package Structures.Effects;

public class SpecialEffects {

	protected static final int NO_RELOAD = 0;
	protected static final int RELOAD_EVERY_TURN = 1;
	protected static final int RELOAD_EVERY_AGE = 2;
	
	protected int ID;
	protected boolean usedUp;
	protected int type;
	
	public SpecialEffects()
	{
		ID = 0;
		usedUp = true;
		type = NO_RELOAD;
	}
	
	public SpecialEffects(int id, boolean used, int type)
	{
		ID = id;
		usedUp = used;
		this.type = type;
	}
	
	public int GetType()
	{
		return type;
	}
	
	public boolean isUsedUp()
	{
		return usedUp;
	}
	
	public void Reset()
	{
		if ( type == RELOAD_EVERY_TURN || type == RELOAD_EVERY_AGE )
		{
			usedUp = false;
		}
	}
	
}
