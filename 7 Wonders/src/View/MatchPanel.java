package View;

import java.io.File;

import javax.swing.*;

import Controls.Match;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MatchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Match match;
	private ImageIcon cards[];
	
	private FarPanel f1, f2, f3, f4;
	private NearPanel n1, n2;
	private LocalPanel playerPanel;
	private CardsPanel cardsPanel;
	
	public MatchPanel(int numPlayers) {
		setLayout(null);
		setSize(1280, 860);
		
		match = new Match(7);
		
		// Load all the Card images
		int numCards = new File("src/Images/Cards").listFiles().length;
		cards = new ImageIcon[numCards+1];
		for(int i = 1; i < numCards+1; i++) {
			cards[i] = new ImageIcon(new ImageIcon(MatchPanel.class.getResource("/Images/Cards/"+i+".jpg")).getImage().getScaledInstance(182, 280, java.awt.Image.SCALE_SMOOTH));
		}
		
		// Make the overview panels
		
		// Players
		playerPanel = new LocalPanel(match.getPlayers().get(0));
		playerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				localClicked();
			}
		});
		playerPanel.setLocation(401, 450);
		
		// Neighbours
		n1 = new NearPanel(match.getPlayers().get(1));
		n1.setLocation(0, 150);
		n2 = new NearPanel(match.getPlayers().get(2));
		n2.setLocation(802, 150);
		
		// Foreigners
		f1 = new FarPanel(match.getPlayers().get(3));
		f1.setLocation(0, 0);
		f2 = new FarPanel(match.getPlayers().get(4));
		f2.setLocation(320, 0);
		f3 = new FarPanel(match.getPlayers().get(5));
		f3.setLocation(640, 0);
		f4 = new FarPanel(match.getPlayers().get(6));
		f4.setLocation(960, 0);
		
		// Cards
		cardsPanel = new CardsPanel(cards, match.getPlayers().get(0).getCards());
		cardsPanel.setSize(1274, 280);
		cardsPanel.setLocation(3, 558);
		cardsPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardsClicked();
			}
		});
		
		add(cardsPanel);
		add(playerPanel);
		add(n1);
		add(n2);
		add(f1);
		add(f2);
		add(f3);
		add(f4);
		
	}
	

	private void localClicked() {
		if(cardsPanel.getY() == 558)
			cardsPanel.setLocation(cardsPanel.getX(), 750);
		//else cardsPanel.setLocation(cardsPanel.getX(), 558);
	}
	
	private void cardsClicked() {
		if(cardsPanel.getY() == 750)
			cardsPanel.setLocation(cardsPanel.getX(), 558);
	}
}
