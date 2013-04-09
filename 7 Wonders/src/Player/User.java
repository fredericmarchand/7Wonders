package Player;

import java.util.ArrayList;

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
		//if ( this.msg == null || currentMatch == null ) return;
		//if ( (this.msg.getMsgType() == CommandMessage.MOVE_TYPE && currentMatch.getAge() != 4) || this.msg.getMsgType() == CommandMessage.SCIENTIFIC_SYMBOL_TYPE ) 
		//	lastMessage = true;
		//else 
			lastMessage = false;
	}
	
	public void sendCommandMessage()
	{
		if ( client != null && msg != null )
			client.sendCommandMessage(SevenWondersProtocol.encodeCommandMessage(msg), lastMessage);
		System.out.println("[CLIENT ------ USER] MClient : \t" + client );
		System.out.println("Send Command Message: " + msg);
	}
	
	public void updateMatch(Match2 match)
	{
		currentMatch.setAge(match.getAge());
		currentMatch.setTurn(match.getTurn());
		currentMatch.getDiscardedCards().clear();
		currentMatch.getDiscardedCards().addAll(match.getDiscardedCards());
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
		}
		else
		{
			Match2 mat = SevenWondersProtocol.decodeMatch(encoding);
			SevenWondersProtocol.assignUsernamesAndIDs(mat, names, ids);
			mat.setLocalPlayerID(ID);
			updateMatch(mat);
			if ( client != null && client.getMainFrame() != null )
			{
				if ( currentMatch.getAge() == 4 && currentMatch.getTurn() == 1 )
					client.getMainFrame().updateChooseScience();
				client.getMainFrame().updateValues();
			}
		}
	}
	
	public void returnToLobby()
	{
		client.returnToLobby();
	}
	
	public void startMatch()
	{
		client.getMainFrame().startController(new NetworkGameController(client, currentMatch));
	}
}
