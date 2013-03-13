package Structures.Effects;

import Player.Player;
import Tokens.Resources;

public class ResourcesBonus extends SpecialEffect {

		public  static final int ResourcesBonusID = 0x0C;
		
		private Resources resources;
		
		public ResourcesBonus(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus)
		{
			super(ResourcesBonusID, true, RELOAD_EVERY_TURN, START_OF_TURN);
			resources = new Resources(ore, stone, wood, clay, glass, loom, papyrus, 0);
		}
		
		public Resources getResources()
		{
			return resources;
		}
		
		public void acquireResources(Player p)
		{
			p.getResources().addResources(resources);
		}
		
}
