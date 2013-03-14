package Player;

import Controls.CommandMessage;
import Controls.Match;

public class User {

	protected int ID;
	protected String username;
	private Match currentMatch;
	private CommandMessage msg;
	
	public User(int id, String name)
	{
		ID = id;
		username = name;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public int getID()
	{
		return ID;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
