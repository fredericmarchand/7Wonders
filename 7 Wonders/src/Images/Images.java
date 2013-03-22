package Images;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Images {
	public static Image cardlabelbg;
	public static ImageIcon cards[], arrows[], boardsSmall[][], boardsBig[][], options, 
							checkmarkSmall, checkmarkBig, overlayFar, overlayNear,
							res0011000, res0101000, res0110000, res1001000, 
							res1010000, res1100000, res0000111, res1111000,
							resOverlay2, resOverlay3, resOverlay4,
							sciOverlay3, sciOverlay2, sciOverlay1, sciPicker, 
							age[], xClose;
	
	public static void run() {
		long startTime = System.currentTimeMillis();
		
		
		// MatchPanel
		age = new ImageIcon[3];
		age[0] = new ImageIcon(Images.class.getResource("/Images/Icons/age1.png"));
		age[1] = new ImageIcon(Images.class.getResource("/Images/Icons/age2.png"));
		age[2] = new ImageIcon(Images.class.getResource("/Images/Icons/age3.png"));
		xClose = new ImageIcon(Images.class.getResource("/Images/Icons/X.png"));
		
		System.out.println("Done loading MatchPanel images. Took " + (System.currentTimeMillis() - startTime) + "ms");
		
		// Cards
		cards = new ImageIcon[76];
		for(int i = 1; i <= 75; i++) 
			cards[i] = new ImageIcon(new ImageIcon(Images.class.getResource("/Images/Cards/"+i+".jpg")).getImage().getScaledInstance(182, 280, java.awt.Image.SCALE_SMOOTH));
		
		System.out.println("Done loading Card images. Took " + (System.currentTimeMillis() - startTime) + "ms");
		
		arrows = new ImageIcon[3];
		arrows[0] = new ImageIcon(Images.class.getResource("/Images/Icons/arrowno.png"));
		arrows[1] = new ImageIcon(Images.class.getResource("/Images/Icons/arrowtrading.png"));
		arrows[2] = new ImageIcon(Images.class.getResource("/Images/Icons/arrowyes.png"));
		options = new ImageIcon(Images.class.getResource("/Images/Icons/chooser.png"));
		cardlabelbg = new ImageIcon(Images.class.getResource("/Images/Icons/cardlblbg.png")).getImage();
		
		System.out.println("Done loading CardPanel images. Took " + (System.currentTimeMillis() - startTime) + "ms");
		
		// Boards
		boardsSmall = new ImageIcon[7][2];
		boardsBig = new ImageIcon[7][2];
		for(int i = 1; i <= 7; i++) {
			for(int j = 1; j <= 2; j++) {
				URL temp = Images.class.getResource("/Images/Boards/board"+i+j+".png");
				boardsSmall[i-1][j-1] = new ImageIcon(new ImageIcon(temp).getImage().getScaledInstance(320, 150, java.awt.Image.SCALE_SMOOTH));
				boardsBig[i-1][j-1] = new ImageIcon(temp);
			}
		}
		
		System.out.println("Done loading WonderBoard images. Took " + (System.currentTimeMillis() - startTime) + "ms");
		
		checkmarkSmall = new ImageIcon(new ImageIcon(Images.class.getResource("/Images/Icons/yes.png")).getImage().getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH));
		checkmarkBig = new ImageIcon(new ImageIcon(Images.class.getResource("/Images/Icons/yes.png")).getImage().getScaledInstance(34, 34, java.awt.Image.SCALE_SMOOTH));
		overlayFar = new ImageIcon(Images.class.getResource("/Images/Icons/overlayforeign.png"));
		overlayNear = new ImageIcon(Images.class.getResource("/Images/Icons/overlay.png"));
		
		
		// ResourceChoicePanel
		res0011000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-0011000.png"));
		res0101000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-0101000.png"));
		res0110000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-0110000.png"));
		res1001000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-1001000.png"));
		res1010000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-1010000.png"));
		res1100000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-1100000.png"));
		res0000111 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-0000111.png"));
		res1111000 = new ImageIcon(Images.class.getResource("/Images/Icons/resource-1111000.png"));
		resOverlay2 = new ImageIcon(Images.class.getResource("/Images/Icons/circleselect2.png"));
		resOverlay3 = new ImageIcon(Images.class.getResource("/Images/Icons/circleselect3.png"));
		resOverlay4 = new ImageIcon(Images.class.getResource("/Images/Icons/circleselect4.png"));
		
		
		// ScienceChoicePanel
		sciPicker = new ImageIcon(Images.class.getResource("/Images/Icons/sciencepicker.png"));
		sciOverlay1 = new ImageIcon(Images.class.getResource("/Images/Icons/scienceoverlay1.png"));
		sciOverlay2 = new ImageIcon(Images.class.getResource("/Images/Icons/scienceoverlay2.png"));
		sciOverlay3 = new ImageIcon(Images.class.getResource("/Images/Icons/scienceoverlay3.png"));
		
		System.out.println("Done loading all images. Took " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
