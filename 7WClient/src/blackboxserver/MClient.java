package blackboxserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import blackboxserver.Packet.Packet0LoginRequest;
import blackboxserver.Packet.Packet1LoginAnswer;
import blackboxserver.Packet.Packet2Message;
import blackboxserver.Packet.Packet3Connection;
import blackboxserver.Packet.Packet4Object;
import blackboxserver.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class MClient {
    private Client client;
    public static Scanner scanner;
    private ArrayList<Object> connectedList;
    private boolean GAME_ALIVE = true;
    
    public MClient(){
    	scanner = new Scanner(System.in);
    	client = new Client();
    	NetworkListener nl = new NetworkListener();
    	
    	client.addListener(nl);
    	client.start();
    	register();
    	

    	
    	
    	try {
			Log.info("Please enter the specified IP!");
			String x = scanner.next();
			Log.info("Please enter the specified Port!");
			int p = Integer.parseInt(scanner.next());
			client.connect(50000,x,p);
			  Scanner s = new Scanner(System.in);
		        while(GAME_ALIVE){
		        	String m = s.next();
		            Packet2Message mpackage = new Packet2Message();
			        mpackage.setObject(m);
			        client.sendTCP(mpackage);		        	
		        }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			client.stop();
		}
    }
    
    public void register(){
    	Kryo kryo = client.getKryo();
    	kryo.register(Packet0LoginRequest.class);
    	kryo.register(Packet1LoginAnswer.class);
    	kryo.register(Packet2Message.class);
    	kryo.register(Packet3Connection.class);
    	kryo.register(Packet4Object.class);
    	kryo.register(java.util.ArrayList.class);
    	kryo.register(Match.class);
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MClient();
		Log.set(Log.LEVEL_TRACE);

	}

}
