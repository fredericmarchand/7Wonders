package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Structures.Structure;

public class FullscreenCardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JLabel> cardArr, labels, labelBg;
	private ArrayList<Structure> cards;
	
	@SuppressWarnings("unused")
	private Controller controller;
	
	private int totalWidth;
	
	public FullscreenCardsPanel (ArrayList<Structure> ca, Controller c) {
		setLayout(null);
		setOpaque(false);
		
		cards = ca;
		controller = c;
		
		cardArr = new ArrayList<JLabel>();
		labels = new ArrayList<JLabel>();
		labelBg = new ArrayList<JLabel>();
		
		totalWidth = 0;
		
		update();
	}
	
	public void update() {
		cardArr.clear();
		labels.clear();
		labelBg.clear();
		this.removeAll();
		if(cards == null) cards = new ArrayList<Structure>();
		for(int i = 0; i < cards.size(); i++) {
			cardArr.add(new JLabel(Images.cards[cards.get(i).getID()]));
			cardArr.get(i).setSize(182, 280);
			
			labels.add(new JLabel(cards.get(i).getName()));
			labels.get(i).setForeground(Color.BLACK);
			labels.get(i).setFont(new Font("Arial", Font.PLAIN, 18));
			labels.get(i).setUI(new VerticalLabelUI(false));
			labels.get(i).setSize(25, 188);
			
			labelBg.add(new JLabel());
			labelBg.get(i).setSize(30, 188);
			int w = labels.get(i).getFontMetrics(labels.get(i).getFont()).stringWidth(labels.get(i).getText()) + 23;
			labelBg.get(i).setIcon(new ImageIcon(Images.cardlabelbg.getScaledInstance(30, w, java.awt.Image.SCALE_SMOOTH)));
			labelBg.get(i).setSize(30, w);
			
			// Calculate offset
			int xoffset = (i*37);
			int yoffset = (i*66) % 594;
			
			
			cardArr.get(i).setLocation(xoffset, yoffset);
			labels.get(i).setLocation(xoffset + 10, yoffset + 74);
			labelBg.get(i).setLocation(xoffset + 8, yoffset + 275 - w);
			
			totalWidth = xoffset + cardArr.get(i).getWidth();
		}
		setPreferredSize(new Dimension(totalWidth, 838));
		
		// Add in reverse order to have proper stagger
		for(int i = cardArr.size()-1; i >= 0; i--) {
			add(labels.get(i));
			add(labelBg.get(i));
			add(cardArr.get(i));
		}
	}
	
	public void setCards(ArrayList<Structure> c) {
		cards = c;
	}
}
