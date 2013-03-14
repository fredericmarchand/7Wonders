package View;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import Structures.Structure;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[] cardImages;
	private ArrayList<Structure> cards;	
	private JLabel cardArr[], lblArr[], lblBgArr[];
	private MouseListener ml[];
	private Image lblBgImage;
	
	private Controller controller;
	
	public CardsPanel(ImageIcon[] ii, ArrayList<Structure> ca, Controller c) {
		setLayout(null);
		setOpaque(false);
		setSize(1274, 280);
		
		cardArr = new JLabel[7];
		lblArr = new JLabel[7];
		lblBgArr = new JLabel[7];
		ml = new MouseListener[7];
		
		controller = c;
		cardImages = ii;
		cards = ca;
		lblBgImage = new ImageIcon(CardsPanel.class.getResource("/Images/Icons/cardlblbg.png")).getImage();
		
		for (int i = 0; i < 7; i++) {
			cardArr[i] = new JLabel();
			cardArr[i].setSize(182, 280);
			
			lblArr[i] = new JLabel();
			lblArr[i].setForeground(Color.BLACK);
			lblArr[i].setFont(new Font("Arial", Font.PLAIN, 18));
			lblArr[i].setUI(new VerticalLabelUI(false));
			lblArr[i].setSize(25, 188);
			lblBgArr[i] = new JLabel();
			lblBgArr[i].setSize(30, 188);
			
			ml[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < cards.size(); i++) {
						if (((JLabel)e.getComponent()) == cardArr[i]) {
							if(controller != null) controller.handleCardClicked(cards.get(i));
						}
					}
					update();
				}
			};
			
			add(lblArr[i]);
			add(lblBgArr[i]);
			add(cardArr[i]);
		}
		
		addMouseListeners();
		update();
	}
	
	public void addMouseListeners() {
		for (int i = 0; i < 7; i++) {
			cardArr[i].addMouseListener(ml[i]);
		}
	}
	
	public void removeMouseListeners() {
		for (int i = 0; i < 7; i++) {
			cardArr[i].removeMouseListener(ml[i]);
		}
	}

	public void update() {
		updateCards();
		updateLabels();
		updateLabelBackgrounds();
		updateLocations();
	}
	
	private void updateLabelBackgrounds() {
		for (int i = 0; i < 7; i++) {
			if(cards.size() > i) {
				int w = lblArr[i].getFontMetrics(lblArr[i].getFont()).stringWidth(lblArr[i].getText()) + 23;
				lblBgArr[i].setIcon(new ImageIcon(lblBgImage.getScaledInstance(30, w, java.awt.Image.SCALE_SMOOTH)));
				lblBgArr[i].setSize(30, w);
			} else lblBgArr[i].setIcon(null);
		}
	}

	private void updateCards() {
		for (int i = 0; i < 7; i++) {
			if(cards.size() > i) 
				cardArr[i].setIcon(cardImages[cards.get(i).getID()]);
			else cardArr[i].setIcon(null);
		}
	}
	
	private void updateLabels() {
		for(int i = 0; i < 7; i++) {
			if(cards.size() > i)
				lblArr[i].setText(cards.get(i).getName());
			else lblArr[i].setText("");
		}
	}
	
	private void updateLocations() {
		int a = cards.size();
		for (int i = 0; i < 7; i++) {
			cardArr[i].setLocation(i*182 + ((7-a) * 91), 0);
			lblArr[i].setLocation(10 + i*182 + ((7-a) * 91), 74);
			lblBgArr[i].setLocation(8 + i*182 + ((7-a) * 91), 275 - lblBgArr[i].getHeight());
		}
	}
}
