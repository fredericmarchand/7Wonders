package Resources;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.MClient;


public class CreateMenu extends JFrame {
	JButton create,cancel;
	JLabel human,ai;
	JTextField humanNum,aiNum;
	MClient mclient;

	public CreateMenu(MClient mc){
		setLayout(new GridLayout(4,3,5,10));
		mclient = mc;
		create = new JButton("Create");
		cancel = new JButton("Cancel");
		human = new JLabel("Human player(s) :");
		ai = new JLabel("A.I. player(s) :");
		humanNum = new JTextField(5);
		aiNum = new JTextField(5);
		
		add(human);
		add(humanNum);
		add(ai);
		add(aiNum);
		
		
		
		add(create);
		add(cancel);
		
	    create.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               mclient.sendCreateMatchRequest();
            }
        });
		
		
		setResizable(false);
		setSize(200,175);
	}
	
	public void showGUI(){
		setVisible(true);
	}
	
//	public static void main(String args[]){
//		CreateMenu f = new CreateMenu();
//		f.pack();
//		f.setVisible(true);
//	}
}
