package Player;

import java.util.ArrayList;

import Structures.Structure;

import client.MClient;
import Controls.CommandMessage;
import Controls.Match2;
import Controls.NetworkGameController;
import Controls.SevenWondersProtocol;

public class User {

	protected long ID;
	protected String username;
	private Match2 currentMatch;
	private CommandMessage msg;
	private MClient client;
	
	//default constructor
	public User(){}
	public User(String name, long id)
	{
		ID = id;
		username = name;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public long getID()
	{
		return ID;
	}
	
	public MClient getClient()
	{
		return client;
	}
	
	public void setUsername(String name)
	{
		username = name;
	}
	
	public void setID(long id)
	{
		ID = id;
	}
	
	public void setClient(MClient cl)
	{
		System.out.println("setClient: " + cl);
		client = cl;
	}
	
	public void setCommand(CommandMessage msg)
	{
		this.msg = msg;
	}
	
	public void sendCommandMessage()
	{
		if ( client != null )
			client.sendCommandMessage(msg);
		
		System.out.println("Send Command Message: " + msg);
	}
	
	public void updateMatch(Match2 match)
	{
		currentMatch.setAge(match.getAge());
		currentMatch.setTurn(match.getTurn());
		currentMatch.getDiscardedCards().clear();
		currentMatch.getDiscardedCards().addAll(match.getDiscardedCards());
//		for ( Player p: currentMatch.getPlayers() )
//		{
//			//fuck this shit
//		}
	}
	
	@SuppressWarnings("unchecked")
	public void receive(ArrayList<Object> encoding, ArrayList<Object> ids, ArrayList<Object> names)
	{
		currentMatch = SevenWondersProtocol.decodeMatch(encoding);
		assignUsernamesAndIDs(currentMatch, names, ids);
		NetworkGameController gc = new NetworkGameController(this, currentMatch);
	}
	
	public void returnToLobby()
	{
		client.returnToLobby();
	}
	
	public void startMatch()
	{
		//System.out.println("StartMatch Wonderboard?: " + match.getPlayers().get(0).getWonderBoard());
		//currentMatch = match;
		currentMatch.setLocalPlayerID(ID);
		//@SuppressWarnings("unused")
		//NetworkGameController gc = new NetworkGameController(this, currentMatch);
	}
	
	
	


}
