package Player;

import client.MClient;
import Controls.CommandMessage;
import Controls.Match2;
import Controls.NetworkGameController;

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
		client = cl;
	}
	
	public void setCommand(CommandMessage msg)
	{
		this.msg = msg;
	}
	
	public void sendCommandMessage()
	{
		client.sendCommandMessage(msg);
	}
	
	public void updateMatch(Match2 match)
	{
		currentMatch.setAge(match.getAge());
		currentMatch.setTurn(match.getTurn());
		currentMatch.getDiscardedCards().clear();
		currentMatch.getDiscardedCards().addAll(match.getDiscardedCards());
		for ( Player p: currentMatch.getPlayers() )
		{
			//fuck this shit
		}
	}
	
	public void returnToLobby()
	{
		client.returnToLobby();
	}
	
	public void startMatch(Match2 match)
	{
		currentMatch = match;
		currentMatch.setLocalPlayerID(ID);
		NetworkGameController gc = new NetworkGameController(new Player(username, ID), currentMatch);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
