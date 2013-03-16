package Resources;
import client.MClient;

public class Chat {
	private ChatFrame chatFrame;
	MClient client ;	
	public Chat(MClient c){
		chatFrame = new ChatFrame(this);
		client = c;
	}
	
	public void addChat(String s){
		chatFrame.appendChat(s);
	}
	public void launchChatFrame(){
		chatFrame.createAndShowGUI();
	}
	public void sendMsg(String s){
		client.sendChat(s);
	}
	
	public void countdown(){
		chatFrame.appendChat("[MATCH] STARTING IN T-MINUS 10 SECONDS");
		for(int i = 10; i > 0;i--){
			chatFrame.appendChat("[MATCH] " + String.valueOf(i) + " seconds");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
