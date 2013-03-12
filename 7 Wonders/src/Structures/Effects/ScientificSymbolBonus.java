package Structures.Effects;

import Player.Player;

public class ScientificSymbolBonus extends SpecialEffect {
	
	public static final int ScientificSymbolBonusID = 0x07;
	private int symbolType; 
	private boolean choose;
	
	//public ScientificSymbolBonus()
	//{
	//	super(ScientificSymbolBonusID, false, NO_RELOAD, END_OF_GAME);
	//	symbolType = 0;
	//	choose = true;
	//}
	
	public ScientificSymbolBonus(int symbol, int activate, boolean choose)
	{
		super(ScientificSymbolBonusID, false, NO_RELOAD, activate);
		symbolType = symbol;
		this.choose = choose;
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
