package View;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import Structures.Structure;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[] cardImages;
	private ArrayList<Structure> cards;	
	private JLabel cardArr[], lblArr[];
	
	public CardsPanel(ImageIcon[] c, ArrayList<Structure> ca) {
		setLayout(null);
		setOpaque(false);
		setSize(1274, 280);
		
		cardArr = new JLabel[7];
		lblArr = new JLabel[7];
		
		cardImages = c;
		cards = ca;
		
		for (int i = 0; i < 7; i++) {
			cardArr[i] = new JLabel();
			lblArr[i] = new JLabel();
			lblArr[i].setForeground(Color.BLACK);
			lblArr[i].setFont(new Font("Arial", Font.PLAIN, 18));
			lblArr[i].setUI(new VerticalLabelUI(false));
			
			cardArr[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cards.remove(cards.size()-1);
					update();
				}
			});
			
			add(lblArr[i]);
			add(cardArr[i]);
		}
		
		setDefaultLocation();

		update();
	}
	
	public void update() {
		updateCards();
		updateLabels();
		updateLocation();
	}
	
	public void updateCards() {
		for (int i = 0; i < 7; i++) {
			if(cards.size() > i) 
				cardArr[i].setIcon(cardImages[cards.get(i).getID()]);
			else cardArr[i].setIcon(null);
		}
	}
	
	public void updateLabels() {
		for(int i = 0; i < 7; i++) {
			if(cards.size() > i)
				lblArr[i].setText(cards.get(i).getName());
			else lblArr[i].setText("");
		}
	}
	
	private void setDefaultLocation() {
		for (int i = 0; i < 7; i++) {
			cardArr[i].setBounds(i*182, 0, 182, 280);
			lblArr[i].setBounds(10 + i*182, 74, 25, 188);
		}
	}
	
	private void updateLocation() {
		int a = cards.size();
		for (int i = 0; i < 7; i++) {
			cardArr[i].setBounds(i*182 + ((7-a) * 91), 0, 182, 280);
			lblArr[i].setBounds(10 + i*182 + ((7-a) * 91), 74, 25, 188);
		}
	}
}
