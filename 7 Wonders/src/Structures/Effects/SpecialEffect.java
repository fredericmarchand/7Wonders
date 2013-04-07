package Structures.Effects;

public class SpecialEffect {

	protected static final int NO_RELOAD = 0;
	protected static final int RELOAD_EVERY_TURN = 1;
	protected static final int RELOAD_EVERY_AGE = 2;
	
	protected static final int END_OF_TURN = 0;
	protected static final int START_OF_TURN = 1;
	protected static final int IN_TURN = 2;
	public static final int END_OF_GAME = 3;
	
	protected int ID;
	protected boolean usedUp;
	protected int type;
	protected int activation;
	
	public SpecialEffect()
	{
		ID = 0;
		usedUp = true;
		type = NO_RELOAD;
		activation = 2;
	}
	
	public SpecialEffect(int id, boolean used, int type, int act)
	{
		ID = id;
		usedUp = used;
		this.type = type;
		activation = act;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public int getType()
	{
		return type;
	}
	
	public boolean isUsedUp()
	{
		return usedUp;
	}
	
	public int activateTime()
	{
		return activation;
	}
	
	public void use()
	{
		usedUp = true;
	}
	
	public void reset()
	{
		if ( type == RELOAD_EVERY_TURN || type == RELOAD_EVERY_AGE )
		{
			usedUp = false;
		}
	}
	
	
	public void setters(int used, int typ, int act)
	{
		if ( used ==  1 ) usedUp = true;
		else usedUp = false;
		type = typ;
		activation = act;
	}
	
}
