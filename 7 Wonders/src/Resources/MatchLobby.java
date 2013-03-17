package Resources;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import View.MainFrame;

public class MatchLobby extends JFrame{
	private JList list;
	private JButton start;
	JPanel buttonPanel;
		
	public MatchLobby(){
		setLayout(new GridBagLayout());
		start = new JButton("Start");
		
		add(start);
		
		 start.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	MainFrame f = new MainFrame(null);
	            	f.setVisible(true);
	            	setVisible(false);
	            }
	        });
		 setSize(200,100);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public void showGUI(){
		setVisible(true);
	}
	
	public static void main (String args[]){
		MatchLobby m = new MatchLobby();
		m.setVisible(true);
	}

}
