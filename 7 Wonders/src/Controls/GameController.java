package Controls;

import Structures.Structure;
import View.Controller;

public class GameController implements Controller {

	public static final int BEGINNINGOFGAME = 0;
	public static final int BEGINNINGOFAGE = 1;
	public static final int BEGINNINGTURN = 2;
	public static final int MOVECHOICE = 3;
	public static final int ENDOFTURN = 4;
	public static final int ENDOFAGE = 5;
	public static final int ENDOFGAME = 6;

	public static final int CHOOSERESOURCE = 10;
	public static final int CHOOSESCIENTIFICSYMBOL = 11;
	public static final int CHOOSETRADINGPREFERENCE = 12;
	public static final int CHOOSEFROMDISCSARDED = 13;
	public static final int PLAYLASTCARD = 14;
	
	
	@Override
	public boolean handleCardClicked(Structure s) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
