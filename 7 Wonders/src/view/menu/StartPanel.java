package view.menu;


import image.Images;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import client.MClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final boolean DEBUG_MODE = true;
	
	private JButton create, join, quit;
	private MClient mclient;
	private MainFrame mainframe;
	private JLabel bgimg;
	
	public StartPanel(MainFrame m) {
		setLayout(null);
		setSize(1280, 860);
		
		mainframe = m;
		
		create = new JButton("Create new match");
		create.setLocation(480, 301);
		create.setFont(new Font("Tahoma", Font.BOLD, 28));
		create.setSize(319, 75);
		join = new JButton("Join a match");
		join.setLocation(480, 392);
		join.setFont(new Font("Tahoma", Font.BOLD, 28));
		join.setSize(319, 75);
		quit = new JButton("Quit");
		quit.setLocation(480, 482);
		quit.setFont(new Font("Tahoma", Font.BOLD, 28));
		quit.setSize(319, 75);
		
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.launchCreateMenu(mclient.getLink().launchCreateMenu());
				setVisible(false);
			}
		});

		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.launchLobby(mclient.getLink().launchLobby());;
				setVisible(false);
			}
		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(create);
		add(join);
		add(quit);
		
		bgimg = new JLabel();
		bgimg.setSize(1280, 860);
		add(bgimg);
	}
	
	public void run() {
		bgimg.setIcon(Images.get("bg"));
		
		String uname = "";
		String ip = "";
		String port = "";
		try {
			if(DEBUG_MODE) {
				ip = "127.0.0.1";
				port = "60001";
			} else {
				while(ip.isEmpty())
					ip= JOptionPane.showInputDialog("IP: ");
				while(port.isEmpty())
					port = JOptionPane.showInputDialog("Port: ");
			}
			while(uname.isEmpty())
				uname = JOptionPane.showInputDialog("What is your username? ");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Incorrect Input");
			System.exit(0);
		}
		
		mclient = new MClient(mainframe);
		mclient.setUser_username(uname);

		try {
			while (!mclient.serverConnect(ip, Integer.parseInt(port))) {
				//mclient.getLink().failConnect();
				ip = JOptionPane.showInputDialog("IP: ");
				port = JOptionPane.showInputDialog("Port: ");
			}
		} catch (Exception e) {
			
			//JOptionPane.showMessageDialog(null, "Incorrect Input");
		}
			

		setVisible(true);
	}

}
