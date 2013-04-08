package Resources;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.MClient;
import view.menu.CreateMatchPanel;
import view.menu.LobbyPanel;
import view.menu.StartPanel;

public class linkNetworkView {
	LobbyPanel lobby;
	MatchLobby matchLobby;
	Chat chat;
	CreateMatchPanel createMenu;
	MClient mclient;
	StartPanel startMenu;
	
	public linkNetworkView(MClient c){
		mclient = c;
		lobby = new LobbyPanel(mclient);
		matchLobby = new MatchLobby(mclient);
		chat = new Chat(mclient);
		createMenu = new CreateMatchPanel(mclient);
		
	}
	
	public void killMainFrame() {
		//close match
		lobby.showGUI();
	}
	
	public LobbyPanel launchLobby() {
		lobby.showGUI();
		lobby.update(mclient.getMatchList());
		return lobby;
	}
	
	public void updateLobby(ArrayList<Long> list) {
		lobby.update(list);
	}
	
	public void launchMatchLobby() {
		matchLobby.showGUI();
	}
	
	public void failedMatchLobby() {
		lobby.failedJoin();
		lobby.showGUI();
	}
	
	public void launchChatFrame() {
		chat.launchChatFrame(mclient.getHost());
	}
	
	public void killChatFrame() {
		chat.kill();
	}
	
	public CreateMatchPanel launchCreateMenu() {
		createMenu.showGUI();
		return createMenu;
	}
	
	public Chat getChat() {
		return chat;
	}
	
	public void failConnect() {
		
		int failJoin = JOptionPane.showConfirmDialog(null, "Failed to connect to the server.\nDo you wish to retry?");
		if(failJoin == JOptionPane.OK_OPTION){
			startMenu.run();
		}
		else{ 
			System.exit(0);
		}		
	}
	
}
