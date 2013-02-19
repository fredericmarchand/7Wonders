package blackboxserver;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;



import blackboxserver.Packet.*;


public class NetworkListener extends Listener{
	Client c;
	
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
		Log.info("[CLIENT] ");
		if(o instanceof Packet4Object){
			if(((Packet4Object) o).getID()==1){
				ArrayList l = (ArrayList)((Packet4Object)o).getObject();
				for(Object e: l)
					System.out.println(e);
			}
		}
		
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
	}

}
