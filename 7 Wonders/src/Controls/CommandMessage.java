package Controls;

import java.util.ArrayList;

import Player.Player;
import Resources.Packet;
import client.MClient;

public class CommandMessage {

	//type id's
	public static final int RESOURCE_CHOICE_TYPE = 0;
	public static final int MOVE_TYPE = 1;
	public static final int SCIENTIFIC_SYMBOL_TYPE = 2;
	

	private long playerID; 
	private int msgType;
	private ArrayList<Integer> resourceChoice;
	private int cardID;
	private int action;
	private int preference;
	private int symbolChoice;
	
	public CommandMessage()
	{
		playerID = 0;
		msgType = 0;
		resourceChoice = new ArrayList<Integer>();
		cardID = 0;
		action = 0;
		preference = 0;
		symbolChoice = 0;
	}
	
	public CommandMessage(long pid, int type, int cardid, int actionchoice, int tradepreference, int scientificSymbol, ArrayList<Integer> reschoice)
	{
		playerID = pid;
		msgType = type;
		resourceChoice = reschoice;
		cardID = cardid;
		action = actionchoice;
		preference = tradepreference;
		symbolChoice = scientificSymbol;
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
	
	public int getScientificSymbol()
	{
		return symbolChoice;
	}
	public void setScientificSymbol(int s)
	{
		symbolChoice = s;
	}
	
	public ArrayList<Integer> getResourceChoices()
	{
		return resourceChoice;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
