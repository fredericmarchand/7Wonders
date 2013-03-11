package View;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import Structures.Structure;

public class CardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[] cardImages;
	private ArrayList<Structure> cards;
	private JLabel lblCard0, lblCard1, lblCard2, lblCard3, lblCard4, lblCard5, lblCard6;
	
	public CardsPanel(ImageIcon[] c, ArrayList<Structure> ca) {
		setLayout(null);
		setSize(1274, 280);
		
		cardImages = c;
		cards = ca;
		
		lblCard0 = new JLabel();
		lblCard0.setBounds(0, 0, 182, 280);
		add(lblCard0);
		
		lblCard1 = new JLabel();
		lblCard1.setBounds(182, 0, 182, 280);
		add(lblCard1);
		
		lblCard2 = new JLabel();
		lblCard2.setBounds(364, 0, 182, 280);
		add(lblCard2);
		
		lblCard3 = new JLabel();
		lblCard3.setBounds(546, 0, 182, 280);
		add(lblCard3);
		
		lblCard4 = new JLabel();
		lblCard4.setBounds(728, 0, 182, 280);
		add(lblCard4);
		
		lblCard5 = new JLabel();
		lblCard5.setBounds(910, 0, 182, 280);
		add(lblCard5);
		
		lblCard6 = new JLabel();
		lblCard6.setBounds(1092, 0, 182, 280);
		add(lblCard6);
		
		update();
	}
	
	public void update() {
		updateCardLabels();
	}
	
	public void updateCardLabels() {
		if(cards.size() >= 1) lblCard0.setIcon(cardImages[cards.get(0).getID()+1]);
		if(cards.size() >= 2) lblCard1.setIcon(cardImages[cards.get(1).getID()+1]);
		if(cards.size() >= 3) lblCard2.setIcon(cardImages[cards.get(2).getID()+1]);
		if(cards.size() >= 4) lblCard3.setIcon(cardImages[cards.get(3).getID()+1]);
		if(cards.size() >= 5) lblCard4.setIcon(cardImages[cards.get(4).getID()+1]);
		if(cards.size() >= 6) lblCard5.setIcon(cardImages[cards.get(5).getID()+1]);
		if(cards.size() >= 7) lblCard6.setIcon(cardImages[cards.get(6).getID()+1]);
	}
}
