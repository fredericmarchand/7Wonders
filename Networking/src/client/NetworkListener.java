package client;

import java.util.ArrayList;

import resources.Match;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;



import resources.Packet.*;


public class NetworkListener extends Listener{
	Client c;
	MClient mclient;
	ArrayList<Match> list;
	
	public NetworkListener(MClient m){
		mclient = m;
	}
	
	public void init(Client c){
		this.c = c;
	}
	public void connected(Connection arg0) {
		Log.info("[CLIENT] Connected");
		// TODO Auto-generated method stub
		//c.sendTCP(new Packet0LoginRequest());
		//super.connected(arg0);
	}
	@Override
	public void disconnected(Connection arg0) {
		// TODO Auto-generated method stub
		Log.info("[CLIENT] Disconnected");
		//super.disconnected(arg0);
	}
	@Override
	public void received(Connection c, Object o) {
		System.out.println("[CLIENT] RECEIVED PACKET");
		if(o instanceof Packet1LoginAnswer){
			if(!((Packet1LoginAnswer)o).getAccepted())
				c.close();
		}
		if(o instanceof Packet3Connection){
			Log.info("Packet3Connection Received");
		}
		if(o instanceof Packet4Object){
			Log.info("[CLIENT] PACKET 4");
			if(((Packet4Object) o).getID()==1){
				list = (ArrayList<Match>)((Packet4Object)o).getObject();
				for(Match e : list)
					Log.info(Long.toString(e.getMatch_ID()));
			//	mclient.joinMatch(list);
			}
		}
		
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
	}

}
