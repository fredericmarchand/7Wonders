package blackboxserver;

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
		c.sendTCP(new Packet0LoginRequest());
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
		Log.info("[CLIENT] PewPew");
		if(o instanceof Packet1LoginAnswer){
			boolean answer = ((Packet1LoginAnswer) o).getAccepted();
			if(answer){
				System.out.println("Please enter your first message for the server!");
				while(true){
					if(MClient.scanner.hasNext()){
						Packet2Message mpacket = new Packet2Message();
						mpacket.setObject(MClient.scanner.nextLine());
						c.sendTCP(mpacket);
						Log.info("Please enter another message");
					}
				}
			}else{
				System.out.println("OUCH");
				c.close();
			}
		
		}
		
		
		//modify connectivity
		if(o instanceof Packet3Connection){
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.setAccepted(true);
			c.sendTCP(loginAnswer);
		}
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
	}

}
