package server;

import resources.Match;
import resources.Packet.*;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;



public class NetworkListener extends Listener{

	Server server;
	MServer mserver;
	
	public void init(Server s, MServer m){
		server = s;
		mserver = m;
	}
	public void connected(Connection arg0) {
		Log.info("[SERVER] User has connected");
		
		// TODO Auto-generated method stub
		for(Connection c: server.getConnections())
			Log.info(c.getRemoteAddressTCP().toString());
	
		super.connected(arg0);
	
	}
	@Override
	public void disconnected(Connection arg0) {
		// TODO Auto-generated method stub
		Log.info("[SERVER] User has disconnected");
		//super.disconnected(arg0);
	}
	@Override
	public void received(Connection c, Object o) {
		
		Log.info("[SERVER] Received packet" );
		//System.out.println(o);
		
		if(o instanceof Packet0LoginRequest){
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			c.sendTCP(loginAnswer);
		}
		//modify to handle objects from game
		if(o instanceof Packet2Message){			
			String message = (String)((Packet2Message)o).getObject();
			System.out.println(message);
			if(message.equals("CREATE")){
				Log.info("[SERVER ---------- CREATE Received packet" );
				mserver.createMatch();
			}
			if(message.equals("JOIN")){
				Log.info("[SERVER ---------- JOIN Received packet" );
				Packet4Object l = new Packet4Object();
				l.setID(1);
				l.setObject(mserver.getMatchList());
				c.sendTCP(l);
			}
			if(message.equals("LIST")){
				System.out.println("[SERVER ---------- LIST Received packet" );
				Packet4Object l = new Packet4Object();
				l.setID(1);
				l.setObject(mserver.getMatchList());
				
				c.sendTCP(l);
			}
			
		}	
		//modify connectivity
		if(o instanceof Packet3Connection){
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			c.sendTCP(loginAnswer);
		}
		
		if(o instanceof Packet4Object){
			
			switch(((Packet4Object) o).getID()){
			case 0: ;
			case 1: ;
			case 2: 
			{
				System.out.println(Long.parseLong((String) ((Packet4Object)o).getObject()));
				System.out.println(c.getRemoteAddressTCP());
				//mserver.bridgeClient(c, ((Match)o).getMatch_ID());
//				c.sendTCP(new Packet3Connection());					 
			};
					
			case 3: ;
			case 4: ;
					 break;
			default: break;
			}
			//add client to match list
		}
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
		Log.info("[SERVER] Received packet");
	}

}
