package server;



import Controls.CommandMessage;
import Resources.*;
import Resources.Packet.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;



@SuppressWarnings("unused")
public class NetworkListener extends Listener{

	Server server;
	MServer mserver;
	
	public void init(Server s, MServer m){
		server = s;
		mserver = m;
	}
	public void connected(Connection arg0) {
		System.out.println("[SERVER] User has connected");
//		
//		// TODO Auto-generated method stub
//		for(Connection c: server.getConnections())
//			System.out.println(c.getRemoteAddressTCP().toString());
//	
//		super.connected(arg0);
	
	}
	@Override
	public void disconnected(Connection c) {
		// TODO Auto-generated method stub
		System.out.println("[SERVER] User has disconnected");
		for(Match e :mserver.getMatchList())
			if(e.contains(c)){
				//replace with AI ?
				e.removeConnection(c);
			}
		
	}
	@Override
	public void received(Connection c, Object o) {
		try{
		System.out.println("[SERVER] Received packet" );
		
		if(o instanceof Packet0LoginRequest){
			//create response
			//assign client an ID value
			//send info through packets
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			loginAnswer.setIDValue(mserver.getID());
			mserver.incID();
			loginAnswer.setObject(mserver.getMatchID_List());
			c.sendTCP(loginAnswer);
		}
		//modify to handle objects from game
		if(o instanceof Packet2Message){			
			String message = (String)((Packet2Message)o).getObject();
			System.out.println(message);
			if(message.equals("CREATE")){
				//Request made to create a match
				//match is created
				//returns a packet to client, containing the new matchID
				//client must be set to host 
				System.out.println("[SERVER ---------- CREATE Received packet" );
				long matchID = mserver.createMatch();
				mserver.bridgeClient(c,matchID);//adding client to match connection list
				Packet4Object P = new Packet4Object();
				P.setID(5);
				P.setObject(matchID);
				c.sendTCP(P);
			}
			if(message.equals("LIST")){
				System.out.println("[SERVER ---------- LIST Received packet" );
				Packet4Object l = new Packet4Object();
				l.setID(1);
				l.setObject(mserver.getMatchID_List());
				c.sendTCP(l);				
			}
	
			
		}	
		//modify connectivity
		if(o instanceof Packet3Connection){
			Packet1LoginAnswer joinAnswer = new Packet1LoginAnswer();
			joinAnswer.setAccepted(true);
			c.sendTCP(joinAnswer);
		}
		
		if(o instanceof Packet4Object){
			
			switch(((Packet4Object) o).getID()){
			case 0: ;
			case 1: ;
			case 2: 
			{
				System.out.println("[SERVER] Received match JOIN Request");
				//test if the game is viable to join
				//if not return false
				//state failed to join game
				
				System.out.println(Long.parseLong((String) ((Packet4Object)o).getObject()));
				System.out.println(c.getRemoteAddressTCP());
				//mserver.bridgeClient(c, Long.parseLong((String)((Packet4Object)o).getObject()))){
				boolean join = mserver.bridgeClient(c, Long.parseLong((String)((Packet4Object)o).getObject()));
				Packet3Connection joinResponse = new Packet3Connection();
				joinResponse.setAccepted(join);
				joinResponse.setIDValue(Long.parseLong((String)((Packet4Object)o).getObject()));
				c.sendTCP(joinResponse);
				break;
			}
					
			case 3: 
			{
				System.out.println("[SERVER] Received Client move");
			break;	
			}
			case 4: ;
					 break;
			default: break;
			}
			//add client to match list
		}
		if(o instanceof Packet5Disconnect){
			//disconnect player from game
			System.out.println("[SERVER] CLIENT HAS BEEN REMOVED" +
					mserver.removeClient(c, ((Packet5Disconnect)o).getMID()));
			
		}
		if(o instanceof Packet6ChatMsg){
			System.out.println("[SERVER] RECEIVED CHAT MESSAGE");
			Match m = mserver.findMatch(((Packet6ChatMsg)o).getMID());
			if(m!=null){
				//USING THE PUSH PATTERN
				//sending chat packet without notification
				//if clients have the same match id as the chat sender
				//send the chat packet to all clients of that unique instance
				//of the match
				for(Connection x: m.getConnections())
					x.sendTCP(o);
				
				
			}
		}
		if(o instanceof Packet7MatchFunction){
				//pass info to controller
				//(mserver.findMatch(((Packet7MatchFunction)o).getMID())).build
			
		}
		if(o instanceof Packet8ClientResponse){
			//find match//pass shit
				(mserver.findMatch(((Packet8ClientResponse)o).getMID())).handOff(((Packet8ClientResponse)o));
		}
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
		System.out.println("[SERVER] Received packet");
		}catch(Exception e){
			System.out.println(e);
		}
		if(o instanceof Packet11ImmediateStart){
			(mserver.findMatch(((Packet11ImmediateStart)o).getMID())).sendStartMatchRequest();
		}
	}

}
