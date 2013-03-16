package Resources;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.MClient;
@SuppressWarnings("serial")
public class StartMenu extends JFrame{
	
	private JPanel panel;
	private JButton create,join,quit;
	private FlowLayout layout = new FlowLayout();
	String uname,ip,port;
	MClient mclient;
	
	public StartMenu(){
		create = new JButton("CREATE");
		join = new JButton("JOIN");
		quit = new JButton("QUIT");
		panel = new JPanel();
		panel.setSize(300,300);
		panel.add(create);
		panel.add(join);
		panel.add(quit);
		
	     create.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	               setVisible(false);
	               CreateMenu mc = new CreateMenu(mclient);
	               mc.showGUI();
	            }
	        });
	     
	    join.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	               setVisible(false);
	               Lobby l = new Lobby(mclient);
	               l.showGUI();
	            }
	        });
	     
	    quit.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	               System.exit(0);
	            }
	        });
		
		add(panel);
	}
	
	 public void showGUI() {
	        //Create and set up the window.
		 	// TODO Auto-generated method stub
			String uname = JOptionPane.showInputDialog("What is your username?");
			String ip = JOptionPane.showInputDialog("IP :");
			String port = JOptionPane.showInputDialog("Port :");
			mclient = new MClient();
			mclient.createUser(uname, mclient.getID());
			mclient.serverConnect(ip, Integer.parseInt(port));

	        pack();
	        setVisible(true);
	    }
	 
	 public static void main(String args[]){
		 StartMenu f = new StartMenu();
		 f.showGUI();
	 }
}