package Player;

import Controls.CommandMessage;
import Controls.Match;

public class User {

	protected long ID;
	protected String username;
	private Match currentMatch;
	private CommandMessage msg;
	
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
	
	public void setUsername(String name)
	{
		username = name;
	}
	
	public void setID(long id)
	{
		ID = id;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
