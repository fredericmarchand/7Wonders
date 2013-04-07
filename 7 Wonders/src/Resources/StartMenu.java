package Resources;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.MClient;
		
public class StartMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private final boolean DEBUG_MODE = true;
	
	private JPanel panel;
	private JButton create, join, quit;
	String uname, ip, port;
	MClient mclient;

	public StartMenu() {
		setSize(300, 300);
		
		create = new JButton("CREATE");
		join = new JButton("JOIN");
		quit = new JButton("QUIT");
		panel = new JPanel();
		panel.setSize(300, 300);
		panel.add(create);
		panel.add(join);
		panel.add(quit);

		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mclient.getLink().launchCreateMenu();
				setVisible(false);
			}
		});

		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mclient.getLink().launchLobby();

			}
		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		add(panel);
	}

	public void showGUI() {
		String uname = "";
		String ip = "";
		String port = "";
		try {
			if(DEBUG_MODE) {
				uname = "DEBUGNAME";
				ip = "127.0.0.1";
				port = "60001";
			} else {
				while(uname.isEmpty())
					uname = JOptionPane.showInputDialog("What is your username?");
				while(ip.isEmpty())
					ip= JOptionPane.showInputDialog("IP :");
				while(port.isEmpty())
					port = JOptionPane.showInputDialog("Port :");
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Incorrect Input");
			System.exit(0);
		}
		mclient = new MClient();
		mclient.setUser_username(uname);
		try{
			mclient.serverConnect(ip, Integer.parseInt(port));
		} catch(Exception e) {
			this.dispose();
			JOptionPane.showMessageDialog(null, "Incorrect Input");
			showGUI();
			System.exit(0);
		}
		pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		StartMenu f = new StartMenu();
		f.showGUI();
	}
}
