package client;

import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

import Controls.*;

import Player.Player;
import Player.User;
import Resources.Chat;

import Resources.linkNetworkView;
import Resources.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.Log;


//kill mclient once frames have been closed.
public class MClient {
	private Client client;
	private boolean host = false;
	private ArrayList<Long> matchList;
	// if client has yet to join a game
	// match ID is 0
	private long matchID = 0000;
	private long ID = 0000;
	private String username;
	private User user;
	private linkNetworkView link;
	private Player player;
	Controls.Match1 m;

	// player receive game
	public MClient() {
		matchList = new ArrayList<Long>();
		client = new Client();
		NetworkListener nl = new NetworkListener(this);

		link = new linkNetworkView(this);

		client.addListener(nl);
		client.start();
		register();

	}

	public void sendCreateMatchRequest(int human, int ai) {
		Packet12CreateMatch packet = new Packet12CreateMatch();
		packet.setHuman(human);
		packet.setAI(ai);
		client.sendTCP(packet);
	}

	// get/set host values
	public void setHost(boolean h) {
		host = h;
	}

	public boolean getHost() {
		return host;
	}

	// get/set ID values
	public void setID(long _ID) {
		ID = _ID;
	}

	public long getID() {
		return ID;
	}

	// set match id to which client has joined
	public void setMID(long id) {
		matchID = id;
	}

	public long getMID() {
		return matchID;
	}

	public void setMatchList(ArrayList<Long> list) {
		matchList = list;
	}

	public ArrayList<Long> getMatchList() {
		return matchList;
	}

	// getter for chat
	public void setUser(User u) {
		user = u;
	}

	public linkNetworkView getLink() {
		return link;
	}

	public User getUser() {
		return user;
	}

	public void setUser_username(String s) {
		username = s;
	}

	public String getUser_username() {
		return username;
	}

	public void setUser_ID(long id) {
	}

	public void createUser(String uname, long id) {
		user = new User(uname, id);
	}

	public void serverConnect(String ip, int port) {
		try {
			client.connect(5000, ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			client.stop();
			link.failConnect();
		}
		client.sendTCP(new Packet0LoginRequest());
	}

	// any class type sent over the network must be registered to the kryo
	// generic types are implicitly registered

	public void sendStartRequest() {
		Packet11ImmediateStart packet = new Packet11ImmediateStart();
		packet.setMID(matchID);
		client.sendTCP(packet);
	}

	public void quitMatch() {
		if (matchID > 0) {
			Packet5Disconnect quit = new Packet5Disconnect();
			quit.setMID(matchID);
			client.sendTCP(quit);
			matchID = 0000; // no longer in a game
		}
	}

	public void sendChat(String s) {
		Packet6ChatMsg msg = new Packet6ChatMsg();
		String _msg = ("[" + username + "." + ID + "]" + " " + s);
		msg.setMsg(_msg);
		msg.setCID(ID);
		msg.setuName(username);
		msg.setMID(matchID);
		client.sendTCP(msg);
	}

	public void sendMatchListRequest() {
		Packet2MatchListRequest packet = new Packet2MatchListRequest();
		packet.setObject("LIST");
		client.sendTCP(packet);
	}

	// send request packets to server

	public void sendMatchRequest(String mname) {
		Packet13MatchJoinRequest rPacket = new Packet13MatchJoinRequest();
		rPacket.setMID(Long.parseLong(mname));
		client.sendTCP(rPacket);

	}

	// /HEAVY EDIT////
	public void turn(Packet7MatchFunction o) {
		// player.passMatch((Controls.Match)(o.getObject()));
		// response(player.getCommandMsg());
		// player....

		// if(player.getMatchComplete?
		// new Packet5
		// set info
		// send tcp
	}

	public void response(CommandMessage msg) {
		Packet8ClientResponse resp = new Packet8ClientResponse();
		resp.setCID(ID);
		resp.setMID(matchID);
		resp.setObject(msg);
		client.sendTCP(resp);
	}

	public void startMatch() {
		m = new Controls.Match1();
		player = new Player();
		link.getChat().countdown();
		link.launchMainFrame(m, player);
		// load match
	}

	// return to lobby
	public void returnToLobby() {
		quitMatch();
		// return list?
		// send list request packet
		// receive list
		// set as some variable

	}

	public void register() {
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2MatchListRequest.class);
		kryo.register(Packet3Connection.class);
		kryo.register(Packet4Object.class);
		kryo.register(Packet5Disconnect.class);
		kryo.register(Packet6ChatMsg.class);
		kryo.register(Packet7MatchFunction.class);
		kryo.register(Packet8ClientResponse.class);
		kryo.register(Packet9StartMatch.class);
		kryo.register(Packet10EndMatch.class);
		kryo.register(Packet11ImmediateStart.class);
		kryo.register(Packet12CreateMatch.class);
		kryo.register(Packet13MatchJoinRequest.class);
		kryo.register(Packet14HostCreateMatch.class);
		kryo.register(Packet15MatchDisconnect.class);
		kryo.register(java.util.ArrayList.class);
		kryo.register(Match1.class);
	}

	public static void main(String[] args) {

		Log.set(Log.LEVEL_TRACE);

	}

}
