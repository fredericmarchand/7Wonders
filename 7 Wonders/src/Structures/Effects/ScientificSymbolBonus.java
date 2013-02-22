package Structures.Effects;

import Player.Player;

public class ScientificSymbolBonus extends SpecialEffects {
	
	public static final int ShieldBonusID = 0x07;
	private int symbolType; 
	private boolean choose;
	
	public ScientificSymbolBonus()
	{
		super(ShieldBonusID, false, NO_RELOAD);
		symbolType = 0;
		choose = true;
	}
	
	public ScientificSymbolBonus(int symbol)
	{
		super(ShieldBonusID, false, NO_RELOAD);
		symbolType = symbol;
		choose = false;
	}
	
	public void acquireSymbol(Player p)
	{
		if ( !usedUp )
		{
			switch ( symbolType )
			{
				case 1: p.getScientificSymbols().addCompass(1); break;
				case 2: p.getScientificSymbols().addGears(1); break;
				case 3: p.getScientificSymbols().addTablets(1); break;
			}
		}
		usedUp = true;
	}
	
	public void chooseSymbol(Player p, int sym)
	{
		if ( !usedUp )
		{
			switch ( sym )
			{
				case 1: p.getScientificSymbols().addCompass(1); break;
				case 2: p.getScientificSymbols().addGears(1); break;
				case 3: p.getScientificSymbols().addTablets(1); break;
			}
		}
		if ( sym > 0 && sym < 4 )
			usedUp = true;
	}

	public boolean canChoose()
	{
		return choose;
	}
	
}
