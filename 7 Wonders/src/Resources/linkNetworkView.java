package Resources;

import java.util.ArrayList;

import client.MClient;
import Controls.GameController;
import Player.Player;
import View.MainFrame;
import Resources.*;

public class linkNetworkView {
	Lobby lobby;
	MatchLobby matchLobby;
	Chat chat;
	CreateMenu createMenu;
	MClient mclient;	
	
	public linkNetworkView(MClient c){
		mclient = c;
	}
	
	public void launchMainFrame(Controls.Match m, Player p){
		GameController gc = new GameController(p,m);
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
