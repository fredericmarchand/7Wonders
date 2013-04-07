package view.menu;

import image.Images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import view.resources.JLabel2D;
import view.resources.JTextFieldLimit;


import client.MClient;

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
		human.setBounds(527, 334, 199, 25);
		
		ai = new JLabel2D("A.I. player(s) : ", SwingConstants.CENTER);
		ai.setFont(new Font("Arial Black", Font.PLAIN, 20));
		ai.setForeground(Color.WHITE);
		ai.setOutlineColor(Color.BLACK);
		ai.setStroke(new BasicStroke(2f));
		ai.setBounds(565, 370, 161, 25);
		
		humanNum = new JTextField(1);
		humanNum.setHorizontalAlignment(JTextField.CENTER);
		humanNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		humanNum.setDocument(new JTextFieldLimit(1, JTextFieldLimit.NUMBER_ONLY));
		humanNum.setText("1");
		humanNum.setBounds(736, 334, 49, 25);
		
		aiNum = new JTextField(1);
		aiNum.setHorizontalAlignment(JTextField.CENTER);
		aiNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		aiNum.setDocument(new JTextFieldLimit(1, JTextFieldLimit.NUMBER_ONLY));
		aiNum.setText("2");
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
					
					
					if (humanNum.getText().equals("")) humanNum.setText("1");
					if (aiNum.getText().equals("")) aiNum.setText("0");
					int human = Integer.parseInt(humanNum.getText());
					int ai = Integer.parseInt(aiNum.getText());
					
					if(ai > 6) JOptionPane.showMessageDialog(null, "Too many AI players!", "Error", JOptionPane.ERROR_MESSAGE);
					else if(human + ai > 7) JOptionPane.showMessageDialog(null, "Cannot have more than 7 total players!", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						while(human + ai < 3) ai++;
						client.sendCreateMatchRequest(human, ai);
						setVisible(false);
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
