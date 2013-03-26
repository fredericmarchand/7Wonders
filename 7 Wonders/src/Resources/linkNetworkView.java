package Resources;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.MClient;
import Controls.GameController;
import Controls.Match1;
import Player.Player;
import View.MainFrame;
import Resources.*;

public class linkNetworkView {
	Lobby lobby;
	MatchLobby matchLobby;
	Chat chat;
	CreateMenu createMenu;
	MClient mclient;	
	GameController gc;
	StartMenu startMenu;
	
	public linkNetworkView(MClient c){
		mclient = c;
		lobby = new Lobby(mclient);
		matchLobby = new MatchLobby(mclient);
		chat = new Chat(mclient);
		createMenu = new CreateMenu(mclient);
		
	}
	
	public void launchMainFrame(Match1 m, Player p){
		 gc = new GameController(p,m);
	}
	public void killMainFrame(){
		//close match
		
		lobby.showGUI();
	}
	public void launchLobby(){
		
		lobby.showGUI();
		lobby.update(mclient.getMatchList());
	}
	
	public void updateLobby(ArrayList<Long> list){
		lobby.update(list);
	}
	
	public void launchMatchLobby(){
		
		matchLobby.showGUI();
	}
	
	public void failedMatchLobby(){
		lobby.failedJoin();
		lobby.showGUI();
	}
	
	public void launchChatFrame(){
		
		chat.launchChatFrame(mclient.getHost());
	}
	public void killChatFrame(){
		chat.kill();
	}
	
	
	public void launchCreateMenu(){

		createMenu.showGUI();
	}
	
	public Chat getChat(){return chat;}
	
	public void failConnect(){
		
		int failJoin = JOptionPane.showConfirmDialog(null, 
				"Failed to connect to server\nDo you wish to retry");
		if(failJoin == JOptionPane.OK_OPTION){
			startMenu = new StartMenu();
			startMenu.showGUI();
//			String uname = JOptionPane.showInputDialog("What is your username?");
//			String ip = JOptionPane.showInputDialog("IP :");
//			String port = JOptionPane.showInputDialog("Port :");
//			mclient = new MClient();
//
//			// mclient.createUser(uname, mclient.getID());
//			mclient.setUser_username(uname);
//			// mclient.serverConnect("",6);
//			mclient.serverConnect(ip, Integer.parseInt(port));

		}
		else{ 
			System.exit(0);
		}
			
		
	}
	
}
