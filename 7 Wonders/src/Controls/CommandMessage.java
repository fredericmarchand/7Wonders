package Controls;

import Resources.Packet;
import client.MClient;

public class CommandMessage {

	private int playerID, cardID, moveID;
	
	public CommandMessage(int pid, int cardid, int moveid)
	{
		playerID = pid;
		cardID = cardid;
		moveID = moveid;
	}
	
	public void send(MClient c)
	{
		Packet.Packet7MatchFunction packet = new Packet.Packet7MatchFunction();
		packet.setObject(this);
		packet.setMID(c.getMID());
		packet.setCID(c.getID());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
