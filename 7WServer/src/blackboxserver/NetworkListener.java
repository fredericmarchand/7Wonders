package blackboxserver;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;


import blackboxserver.Packet.*;

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
		System.out.println(o);
		
		if(o instanceof Packet0LoginRequest){
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			c.sendTCP(loginAnswer);
		}
		//modify to handle objects from game
		if(o instanceof Packet2Message){
			
			
			
			String message = (String)((Packet2Message)o).getObject();
			if(message.equals("CREATE")){
				Log.info("[SERVER ---------- CREATE Received packet" );
				mserver.createMatch();
			}
			else if(message.equals("JOIN")){
				Log.info("[SERVER ---------- JOIN Received packet" );
				Packet4Object l = new Packet4Object();
				l.setID(1);
				l.setObject(mserver.getMatchList());
				c.sendTCP(l);
			}
			Log.info(message);
		}	
		//modify connectivity
		if(o instanceof Packet3Connection){
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			c.sendTCP(loginAnswer);
		}
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
		Log.info("[SERVER] Received packet");
	}

}
