package server;

import java.util.ArrayList;
import java.util.ListIterator;

import Controls.CommandMessage;
import Resources.*;
import Resources.Packet.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

@SuppressWarnings("unused")
public class NetworkListener extends Listener {

	Server server;
	MServer mserver;

	public void init(Server s, MServer m) {
		server = s;
		mserver = m;
	}

	public void connected(Connection arg0) {
	}

	@Override
	public void disconnected(Connection c) {
		System.out.println("[SERVER] User has disconnected");

		for (Match e : mserver.getMatchList()){
			if (e.contains(c)) {
				// replace with AI ?
				e.removeConnectionOnly(c);
				break;
			}
		}
		ListIterator<Match> it = mserver.getMatchList().listIterator();
		while (it.hasNext()) {
			Match m = it.next();
			if (m.getHumanConnectionCount() == 0) {
				it.remove();
				System.out.println("[SERVER] No one in match  - DELETED ");
			}
		}
		try{
			Packet15MatchDisconnect packet = new Packet15MatchDisconnect();
			c.sendTCP(packet);
		}catch(Exception e){
			
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void received(Connection c, Object o) {
		System.out.println("[SERVER] Received packet");
		

		if (o instanceof Packet0LoginRequest) {
			// create response
			// assign client an ID value
			// send info through packets
			System.out.println("[SERVER] Client connected");
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			loginAnswer.setIDValue(mserver.getID());
			mserver.incID();
			loginAnswer.setObject(mserver.getLobbyList());
			c.sendTCP(loginAnswer);
		}
		// modify to handle objects from game
		if (o instanceof Packet2MatchListRequest) {
			String message = (String) ((Packet2MatchListRequest) o).getObject();
			if (message.equals("LIST")) {
				System.out.println("[SERVER ---------- LIST Received packet");
				Packet4Object l = new Packet4Object();
				l.setObject(mserver.getLobbyList());
				c.sendTCP(l);
				
				for(Object s : mserver.getLobbyList())
					System.out.println("[SERVER] Match List:\t" + s);
			}

		}
		// modify connectivity
		if (o instanceof Packet3Connection) {
			Packet1LoginAnswer joinAnswer = new Packet1LoginAnswer();
			joinAnswer.setAccepted(true);
			c.sendTCP(joinAnswer);
		}

		if (o instanceof Packet4Object) {

		}
		if (o instanceof Packet5Disconnect) {
			// disconnect player from game
			System.out
					.println("[SERVER] CLIENT HAS BEEN REMOVED"
							+ mserver.removeClient(c,
									((Packet5Disconnect) o).getMID(),((Packet5Disconnect)o).getCID()));
			Packet15MatchDisconnect packet = new Packet15MatchDisconnect();
			c.sendTCP(packet);
			
		}
		if (o instanceof Packet6ChatMsg) {
			System.out.println("[SERVER] RECEIVED CHAT MESSAGE");
			Match m = mserver.findMatch(((Packet6ChatMsg) o).getMID());
			if (m != null) {
				// USING THE PUSH PATTERN
				// sending chat packet without notification
				// if clients have the same match id as the chat sender
				// send the chat packet to all clients of that unique
				// instance of the match
				for (Connection x : m.getConnections())
					x.sendTCP(o);

			}
		}
		if (o instanceof Packet7MatchFunction) {
			// pass info to controller
			// (mserver.findMatch(((Packet7MatchFunction)o).getMID())).build

		}
		if (o instanceof Packet8ClientResponse) {
			System.out.println("[SERVER] Received client command message \n\t"+
			((Packet8ClientResponse)o).getObject());
			System.out.println("[SERVER] Searching for match: \t" + 
								((Packet8ClientResponse) o).getMID() + " Found: \t "  + 
								mserver.findMatch(((Packet8ClientResponse) o).getMID()));
			(mserver.findMatch(((Packet8ClientResponse) o).getMID()))
					.handOff((ArrayList<Integer>)((Packet8ClientResponse) o).getObject(),
							((Packet8ClientResponse) o).getCID(),
							((Packet8ClientResponse) o).getBoolean());

			for(Integer i : (ArrayList<Integer>)((Packet8ClientResponse) o).getObject())
				System.out.println(i);
		}

		if (o instanceof Packet11ImmediateStart) {
			(mserver.findMatch(((Packet11ImmediateStart) o).getMID()))
					.startMatch();
		}
		if (o instanceof Packet12CreateMatch) {
			System.out.println("[SERVER ---------- CREATE Received packet");
			long matchID = mserver.createMatch(
					((Packet12CreateMatch)o).getHuman(),
					((Packet12CreateMatch)o).getAI(),
					((Packet12CreateMatch)o).getCID(),
					((Packet12CreateMatch)o).getUName(),
					((Packet12CreateMatch)o).getAIDiff());
			
			System.out.println("[SERVER] New Match : \t " + matchID);
			
			mserver.bridgeClient(c, matchID,((Packet12CreateMatch)o).getCID(),
					((Packet12CreateMatch)o).getUName());
			// adding client to match
			// connection list
			
			Packet14HostCreateMatch packet = new Packet14HostCreateMatch();
			packet.setMID(matchID);
			packet.setnPlayer(((Packet12CreateMatch) o).getHuman()+((Packet12CreateMatch) o).getAI());
			c.sendTCP(packet);
			
			mserver.updateMatch(matchID);
			
			
			mserver.updateMatchCount(matchID);
		}
		if (o instanceof Packet13MatchJoinRequest) {
			Packet3Connection joinResponse = new Packet3Connection();
			boolean join = false;
			if(!mserver.containsMatch(((Packet13MatchJoinRequest) o).getMID())){
				joinResponse.setAccepted(false);
				join = false;
				joinResponse.setIDValue(0);
				Packet4Object l = new Packet4Object();
				l.setObject(mserver.getLobbyList());
				c.sendTCP(l);
				//for reset of match list
			}else{
				if ((mserver.findMatch(((Packet13MatchJoinRequest) o)
						.getMID()).get_inProgress()) == true) {
					joinResponse.setAccepted(false);
					join = false;
					joinResponse.setIDValue(0);
				} else {
					join = mserver.bridgeClient(c, ((Packet13MatchJoinRequest) o)
							.getMID(), ((Packet13MatchJoinRequest)o).getCID(),
							((Packet13MatchJoinRequest)o).getUName());
					joinResponse.setAccepted(join);
					joinResponse.setIDValue(((Packet13MatchJoinRequest) o)
							.getMID());
				}
			}
				c.sendTCP(joinResponse);
				
			if (join) {
				mserver.updateMatch(((Packet13MatchJoinRequest) o).getMID());
				Packet6ChatMsg msg = new Packet6ChatMsg();
				msg.setCID(((Packet13MatchJoinRequest) o).getCID());
				msg.setMsg(((Packet13MatchJoinRequest) o).getUName()
						+ " has joined the match");
				msg.setuName("SYSTEM");
				for (Connection x : mserver.findMatch(
						((Packet13MatchJoinRequest) o).getMID())
						.getConnections())
					x.sendTCP(msg);
				mserver.updateMatchCount(((Packet13MatchJoinRequest) o).getMID());
			}
			
			
		}

	}
	


}
