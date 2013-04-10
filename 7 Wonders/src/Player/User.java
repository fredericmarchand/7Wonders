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
	private boolean lastMessage;
	private boolean pause;
	private int lastMessageID;
	
	//default constructor
	public User(){}
	public User(String name, long id)
	{
		ID = id;
		username = name;
		pause = false;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public boolean isPaused()
	{
		return pause;
	}
	
	public void pause()
	{
		if ( currentMatch != null )
			pause = true;
	}
	
	public int getLastMsgID()
	{
		return lastMessageID;
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
		client = cl;
	}
	
	public void setCommand(CommandMessage msg)
	{
		this.msg = msg;
		lastMessage = false;
	}
	
	public void sendCommandMessage()
	{
		/*boolean choice = false;
		if ( msg.getMsgType() == CommandMessage.RESOURCE_CHOICE_TYPE && lastMessageID == CommandMessage.CHOSEN_DISCARDED_TYPE )
			choice = true;
		else if ( (msg.getMsgType() == CommandMessage.CHOSEN_GUILD_TYPE && lastMessageID != CommandMessage.CHOSEN_GUILD_TYPE) 
				|| (msg.getMsgType() == CommandMessage.SCIENTIFIC_SYMBOL_TYPE && lastMessageID != CommandMessage.SCIENTIFIC_SYMBOL_TYPE) )
			choice = true;
		else if ( msg.getMsgType() != lastMessageID ) choice = true;*/
		
		if ( client != null && msg != null && !pause && msg.getMsgType() != lastMessageID )
		{
			lastMessageID = msg.getMsgType();
			client.sendCommandMessage(SevenWondersProtocol.encodeCommandMessage(msg), lastMessage);
		}
		//System.out.println("[CLIENT ------ USER] MClient : \t" + client );
		//System.out.println("Send Command Message: " + msg);
	}
	
	public void updateMatch(Match2 match)
	{
		currentMatch.setAge(match.getAge());
		currentMatch.setTurn(match.getTurn());
		currentMatch.getDiscardedCards().clear();
		currentMatch.getDiscardedCards().addAll(match.getDiscardedCards());
		System.out.println(match.getDiscardedCards().size());
		for ( Structure s: match.getDiscardedCards() )
			System.out.println(s.getName());
		currentMatch.setNumPlayers(match.getNumPlayers());
		currentMatch.setLocalPlayerID(match.getLocalPlayerID());
		for ( Player p: currentMatch.getPlayers() )
		{
			p.updatePlayer(match.getPlayerByID(p.getID()));
		}
	}
	
	public void receive(ArrayList<Integer> encoding, ArrayList<Long> ids, ArrayList<String> names)
	{
		if ( encoding.size() == 0 ) return;
		if ( currentMatch == null )
		{
			currentMatch = SevenWondersProtocol.decodeMatch(encoding);
			SevenWondersProtocol.assignUsernamesAndIDs(currentMatch, names, ids);
			currentMatch.setLocalPlayerID(ID);
			pause = false;
		}
		else
		{
			Match2 mat = SevenWondersProtocol.decodeMatch(encoding);
			SevenWondersProtocol.assignUsernamesAndIDs(mat, names, ids);
			mat.setLocalPlayerID(ID);
			updateMatch(mat);
			pause = false;
			if ( client != null && client.getMainFrame() != null )
			{
				if ( lastMessageID == CommandMessage.RESOURCE_CHOICE_TYPE )
				//{
					client.getMainFrame().update();
					client.getMainFrame().updateValues();
				//}
				
			}
		}
	}
	
	//public void returnToLobby()
	//{
	//	client.returnToLobby();
	//}
	
	public void startMatch()
	{
		client.getMainFrame().startController(new NetworkGameController(client, currentMatch));
	}
}
