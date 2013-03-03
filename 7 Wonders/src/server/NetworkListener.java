package server;



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
		
		// TODO Auto-generated method stub
		for(Connection c: server.getConnections())
			System.out.println(c.getRemoteAddressTCP().toString());
	
		super.connected(arg0);
	
	}
	@Override
	public void disconnected(Connection c) {
		// TODO Auto-generated method stub
		System.out.println("[SERVER] User has disconnected");
		//super.disconnected(arg0);
	}
	@Override
	public void received(Connection c, Object o) {
		try{
		System.out.println("[SERVER] Received packet" );
		
		//System.out.println(o);
		
		if(o instanceof Packet0LoginRequest){
			//create response
			//assign client an ID value
			//send info through packets
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			loginAnswer.setIDValue(mserver.getID());
			mserver.incID();
			c.sendTCP(loginAnswer);
		}
		//modify to handle objects from game
		if(o instanceof Packet2Message){			
			String message = (String)((Packet2Message)o).getObject();
			System.out.println(message);
			if(message.equals("CREATE")){
				System.out.println("[SERVER ---------- CREATE Received packet" );
				mserver.bridgeClient(c,mserver.createMatch());
				Packet4Object P = new Packet4Object();
				P.setID(5);
				P.setObject(true);
				c.sendTCP(P);
			}
			if(message.equals("LIST")){
				System.out.println("[SERVER ---------- LIST Received packet" );
				Packet4Object l = new Packet4Object();
				l.setID(1);
				l.setObject(mserver.getMatchID_List());
				c.sendTCP(l);				
			}
			if(message.equals("QUIT")){
				
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
					
			case 3: ;
			case 4: ;
					 break;
			default: break;
			}
			//add client to match list
		}
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
		System.out.println("[SERVER] Received packet");
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
