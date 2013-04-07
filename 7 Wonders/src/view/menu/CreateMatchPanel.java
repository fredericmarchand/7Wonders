package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.game.JLabel2D;

import client.MClient;

import Images.Images;
import Resources.Lobby;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class CreateMatchPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton create, cancel;
	private JLabel2D human, ai;
	private JLabel bgimg;
	private JTextField humanNum, aiNum;
	private MClient client;
	
	public CreateMatchPanel(MClient mc) {
		setLayout(null);
		setSize(1280, 860);
		
		client = mc;
		create = new JButton("Create");
		create.setFont(new Font("Tahoma", Font.BOLD, 24));
		create.setBounds(495, 406, 290, 47);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 24));
		cancel.setBounds(495, 464, 290, 47);
		
		human = new JLabel2D("Human player(s) : ", SwingConstants.CENTER);
		human.setFont(new Font("Arial Black", Font.PLAIN, 20));
		human.setForeground(Color.WHITE);
		human.setOutlineColor(Color.BLACK);
		human.setStroke(new BasicStroke(2f));
		human.setBounds(495, 334, 231, 25);
		
		ai = new JLabel2D("A.I. player(s) : ", SwingConstants.CENTER);
		ai.setFont(new Font("Arial Black", Font.PLAIN, 20));
		ai.setForeground(Color.WHITE);
		ai.setOutlineColor(Color.BLACK);
		ai.setStroke(new BasicStroke(2f));
		ai.setBounds(534, 370, 192, 25);
		
		humanNum = new JTextField(5);
		humanNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		humanNum.setBounds(736, 334, 49, 25);
		
		aiNum = new JTextField(5);
		aiNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aiNum.setBounds(736, 370, 49, 25);
		
		

		add(human);
		add(humanNum);
		add(ai);
		add(aiNum);

		add(create);
		add(cancel);

		bgimg = new JLabel();
		bgimg.setLocation(0, 0);
		bgimg.setSize(1280, 860);
		add(bgimg);
		
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fix this code
				try{
					if (humanNum.getText().equals(""))
						humanNum.setText("1");
					if (aiNum.getText().equals(""))
						aiNum.setText("0");
					if (((Integer.parseInt(humanNum.getText()) <= 7 && Integer
							.parseInt(humanNum.getText()) >= 1))
							&& (Integer.parseInt(humanNum.getText()) + Integer
									.parseInt(aiNum.getText())) > 0){
						client.sendCreateMatchRequest(
								Integer.parseInt(humanNum.getText()),
								Integer.parseInt(aiNum.getText()));
	
						setVisible(false);
					} else{
						JOptionPane.showMessageDialog(null,
								"Must be 7 or less players or\n1 or more players!");
					}
				}
				catch(Exception error){ 
					error.printStackTrace();
					humanNum.setText("");
					aiNum.setText("");
					JOptionPane.showMessageDialog(null, "Incorrect input" +
							"\nPlease enter 0-7 in the text boxes" +
							"\nfor a total of at most 7 players");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lobby l = new Lobby(client);
				l.showGUI();
				setVisible(false);
			}
		});

		setVisible(false);
	}
	
	public void showGUI() {
		bgimg.setIcon(Images.get("bg"));
		setVisible(true);
	}
}
