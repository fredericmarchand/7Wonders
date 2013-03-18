package Resources;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
            	//fix this code
            	if(humanNum.getText().equals(""))humanNum.setText("0");
            	if(aiNum.getText().equals(""))aiNum.setText("0");
            	if(((Integer.parseInt(humanNum.getText())<=7&&Integer.parseInt(humanNum.getText())>=1))&&
            			(Integer.parseInt(humanNum.getText())+Integer.parseInt(aiNum.getText()))<=7){
	               mclient.sendCreateMatchRequest(Integer.parseInt(humanNum.getText()),Integer.parseInt(aiNum.getText()));
	               
	               setVisible(false);
            	}
            	else
            		JOptionPane.showMessageDialog(null, "Must be 7 or less players or\n1 or more players!");
            }
        });
	    cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               System.exit(0);
            }
        });
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
