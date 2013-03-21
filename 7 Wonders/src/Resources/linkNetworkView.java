package Resources;

import java.util.ArrayList;

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
	
	public linkNetworkView(MClient c){
		mclient = c;
	}
	
	public void launchMainFrame(Match1 m, Player p){
		 gc = new GameController(p,m);
	}
	public void killMainFrame(){
		//close match
		
		lobby.showGUI();
	}
	public void launchLobby(){
		lobby = new Lobby(mclient);
		lobby.showGUI();
	}
	
	public void updateLobby(ArrayList<Long> list){
		lobby.update(list);
	}
	
	public void launchMatchLobby(){
		matchLobby = new MatchLobby(mclient);
		matchLobby.showGUI();
	}
	
	public void failedMatchLobby(){
		lobby.failedJoin();
		lobby.showGUI();
	}
	
	public void launchChatFrame(){
		chat = new Chat(mclient);
		chat.launchChatFrame();
	}
	
	
	
	public void launchCreateMenu(){
		createMenu = new CreateMenu(mclient);
		createMenu.showGUI();
	}
	
	public Chat getChat(){return chat;}
	
}
