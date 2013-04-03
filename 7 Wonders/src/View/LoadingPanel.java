package View;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import Images.*;
import java.awt.Font;

public class LoadingPanel extends JPanel implements ImageLoadingGUI {
	private static final long serialVersionUID = 1L;

	//private int total;
	
	private JProgressBar progressBar;
	
	public LoadingPanel() {
		setLayout(null);
		setSize(1280, 860);
		
		JLabel2D lblLoadingImages = new JLabel2D("Loading images...", SwingConstants.CENTER);
		lblLoadingImages.setForeground(Color.WHITE);
		lblLoadingImages.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoadingImages.setOutlineColor(Color.BLACK);
		lblLoadingImages.setStroke(new BasicStroke(2f));
		lblLoadingImages.setBounds(465, 377, 350, 20);
		add(lblLoadingImages);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		progressBar.setForeground(new Color(205, 92, 92));
		progressBar.setLocation(465, 405);
		progressBar.setSize(350, 56);
		
		add(progressBar);
		
		JLabel bgLabel = new JLabel(new ImageIcon(LoadingPanel.class.getResource("/Images/Icons/loadingbg.jpg")));
		bgLabel.setSize(1280, 860);
        add(bgLabel);
	}

	@Override
	public void imagesBeginLoad(int t) {
		//total = t;
	}

	@Override
	public void update(int current, int total) {
		int percentage = (int)(((float)current/(float)total)*100);
		progressBar.setValue(percentage);
		System.out.println(percentage);
	}
}
