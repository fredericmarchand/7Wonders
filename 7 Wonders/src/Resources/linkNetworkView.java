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
		
		chat.launchChatFrame();
	}
	public void killChatFrame(){
		chat.kill();
	}
	
	
	public void launchCreateMenu(){

		createMenu.showGUI();
	}
	
	public Chat getChat(){return chat;}
	
}
