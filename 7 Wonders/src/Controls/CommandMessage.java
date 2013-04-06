package Controls;

import java.util.ArrayList;

import Tokens.Resources;
import Tokens.ScientificSymbols;

public class CommandMessage {

	//type id's
	public static final int RESOURCE_CHOICE_TYPE = 0;
	public static final int MOVE_TYPE = 1;
	public static final int SCIENTIFIC_SYMBOL_TYPE = 2;
	

	private long playerID; 
	private int msgType;
	private ArrayList<Resources> resourceChoice;
	private int cardID;
	private int action;
	private int preference;
	private ArrayList<ScientificSymbols> symbolChoice;
	
	public CommandMessage()
	{
		playerID = 0;
		msgType = 0;
		resourceChoice = new ArrayList<Resources>();
		cardID = 0;
		action = 0;
		preference = 0;
		symbolChoice = new ArrayList<ScientificSymbols>();
	}
	
	public CommandMessage(long pid, int type, int cardid, int actionchoice, int tradepreference, ArrayList<ScientificSymbols> scientificSymbols, ArrayList<Resources> reschoice)
	{
		playerID = pid;
		msgType = type;
		resourceChoice = reschoice;
		cardID = cardid;
		action = actionchoice;
		preference = tradepreference;
		symbolChoice = scientificSymbols;
	}
	
	public long getPlayerID()
	{
		return playerID;
	}
	public void setPlayerID(long pid)
	{
		playerID = pid;
	}
	
	public int getMsgType()
	{
		return msgType;
	}
	public void setMsgType(int t)
	{
		msgType = t;
	}
	
	public int getCardID()
	{
		return cardID;
	}
	public void setCardID(int id)
	{
		cardID = id;
	}
	
	public int getAction()
	{
		return action;
	}
	public void setAction(int a)
	{
		action = a;
	}
	
	public int getPreference()
	{
		return preference;
	}
	public void setPreference(int p)
	{
		preference = p;
	}
	
	public ArrayList<ScientificSymbols> getScientificSymbols()
	{
		return symbolChoice;
	}
	public void setScientificSymbol(ArrayList<ScientificSymbols> s)
	{
		symbolChoice = s;
	}
	
	public ArrayList<Resources> getResourceChoices()
	{
		return resourceChoice;
	}
	public void setResourceChoices(ArrayList<Resources> choices)
	{
		resourceChoice = choices;
	}

	


}
