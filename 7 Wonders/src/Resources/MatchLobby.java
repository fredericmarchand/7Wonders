package Resources;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.MClient;

public class MatchLobby extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JButton start;
	JPanel buttonPanel;
	MClient mclient;
		
	public MatchLobby(MClient m){
		mclient = m;
		setLayout(new GridBagLayout());
		start = new JButton("Start");
		
		add(start);
		
		 start.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	mclient.sendStartRequest();
//	            	MainFrame f = new MainFrame(null);
//	            	f.setVisible(true);
	            	setVisible(false);
	            }
	        });
		 setSize(200,100);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 if(!mclient.getHost()) start.setEnabled(false);
			 
		 
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public void showGUI(){
		setVisible(true);
	}
	
	
//	public static void main (String args[]){
//		MatchLobby m = new MatchLobby(null);
//		m.setVisible(true);
//	}

}
