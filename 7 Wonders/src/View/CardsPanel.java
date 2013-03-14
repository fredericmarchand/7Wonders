package View;

import java.util.ArrayList;
import java.util.Arrays;

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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class CardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[] cardImages;
	private ArrayList<Structure> cards;	
	private JLabel cardArr[], lblArr[], lblBgArr[], lblOptions[], lblArrow[][];
	private MouseListener ml[];
	private MouseMotionListener mml[];
	private Image lblBgImage;
	private ImageIcon arrowYes, arrowTrading, arrowNo, optionsIcon;
	private boolean showOptions[];
	private boolean canDoAction[][];
	
	private Controller controller;
	
	public CardsPanel(ImageIcon[] ii, ArrayList<Structure> ca, Controller c) {
		setLayout(null);
		setOpaque(false);
		setSize(1274, 280);
		
		cardArr = new JLabel[7];
		lblArr = new JLabel[7];
		lblBgArr = new JLabel[7];
		lblOptions = new JLabel[7];
		lblArrow = new JLabel[7][3];
		ml = new MouseListener[7];
		mml = new MouseMotionListener[7];
		showOptions = new boolean[7];
		canDoAction = new boolean[7][2];
		for (boolean[] b : canDoAction) Arrays.fill(b, Boolean.FALSE);
		Arrays.fill(showOptions, Boolean.FALSE);
		
		controller = c;
		cardImages = ii;
		cards = ca;
		lblBgImage = new ImageIcon(CardsPanel.class.getResource("/Images/Icons/cardlblbg.png")).getImage();
		arrowYes = new ImageIcon(CardsPanel.class.getResource("/Images/Icons/arrowyes.png"));
		arrowTrading = new ImageIcon(CardsPanel.class.getResource("/Images/Icons/arrowtrading.png"));
		arrowNo = new ImageIcon(CardsPanel.class.getResource("/Images/Icons/arrowno.png"));
		optionsIcon = new ImageIcon(CardsPanel.class.getResource("/Images/Icons/chooser.png"));
		
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
			
			lblOptions[i] = new JLabel();
			lblOptions[i].setSize(150, 192);
			
			for(int j = 0; j < 3; j++) {
				lblArrow[i][j] = new JLabel();
				lblArrow[i][j].setSize(91, 57);
				lblArrow[i][j].setIcon(null);
				add(lblArrow[i][j]);
			}
			
			ml[i] = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					for (int i = 0; i < cards.size(); i++) {
						if (((JLabel)e.getComponent()) == cardArr[i]) {
							//if(controller != null) controller.handleCardClicked(cards.get(i));
							Arrays.fill(showOptions, Boolean.FALSE);
							for (boolean[] b : canDoAction) Arrays.fill(b, Boolean.FALSE);
							showOptions[i] = true;
							canDoAction[i][0] = false;
							canDoAction[i][1] = false;
							break;
						}
					}
					update();
				}
			};
			
			mml[i] = new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					for (int i = 0; i < cards.size(); i++) {
						if (((JLabel)e.getComponent()) == cardArr[i]) {
							for(int j = 0; j < 3; j++) {
								if(e.getPoint().x > (26) && e.getPoint().y > (80 + 63*j)
								   && e.getPoint().x < (26 + 91) && e.getPoint().y < (80 + 63*j + 57) && showOptions[i])
									lblArrow[i][j].setIcon(arrowYes);
								else lblArrow[i][j].setIcon(null);
							}
							break;
						}
					}
					update();
				}
			};
			
			add(lblOptions[i]);
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
			cardArr[i].addMouseMotionListener(mml[i]);
		}
	}
	
	public void removeMouseListeners() {
		for (int i = 0; i < 7; i++) {
			cardArr[i].removeMouseListener(ml[i]);
			cardArr[i].removeMouseMotionListener(mml[i]);
		}
	}

	public void update() {
		updateCards();
		updateLabels();
		updateLabelBackgrounds();
		updateLocations();
		updateOptions();
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
		for (int i = 0; i < 7; i++) {
			cardArr[i].setLocation(i*182 + ((7-cards.size()) * 91), 0);
			lblArr[i].setLocation(10 + i*182 + ((7-cards.size()) * 91), 74);
			lblBgArr[i].setLocation(8 + i*182 + ((7-cards.size()) * 91), 275 - lblBgArr[i].getHeight());
			lblOptions[i].setLocation(26 + i*182 + ((7-cards.size()) * 91), 75);
			for(int j = 0; j < 3; j++) lblArrow[i][j].setLocation(26 + i*182 + ((7-cards.size()) * 91), 80 + 63*j);
		}
	}
	
	private void updateOptions() {
		for (int i = 0; i < 7; i++) {
			if(showOptions[i]) lblOptions[i].setIcon(optionsIcon);
			else lblOptions[i].setIcon(null);
		}
	}
}
