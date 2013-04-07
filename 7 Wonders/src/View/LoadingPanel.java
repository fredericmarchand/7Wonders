package View;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Images.*;
import java.awt.Font;

public class LoadingPanel extends JPanel implements ImageLoadingGUI {
	private static final long serialVersionUID = 1L;

	private JLabel bgLabel;
	private JLabel2D lblLoadingImages;
	private JProgressBar progressBar;
	
	public LoadingPanel() {
		setLayout(null);
		setSize(1280, 860);
		
		lblLoadingImages = new JLabel2D("Loading images...");
		lblLoadingImages.setForeground(Color.WHITE);
		lblLoadingImages.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoadingImages.setOutlineColor(Color.BLACK);
		lblLoadingImages.setStroke(new BasicStroke(2f));
		lblLoadingImages.setBounds(465, 377, 416, 20);
		add(lblLoadingImages);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		progressBar.setForeground(new Color(205, 92, 92));
		progressBar.setLocation(465, 405);
		progressBar.setSize(350, 56);
		add(progressBar);
		
		bgLabel = new JLabel();
		bgLabel.setSize(1280, 860);
        add(bgLabel);
	}

	@Override
	public void startImage(int current, int total, String imagename) {
		lblLoadingImages.setText("Loading image " + imagename);
	}

	@Override
	public void endImage(int current, int total, String imagename) {
		if(Images.get("bg") != null) bgLabel.setIcon(Images.get("bg"));
		int percentage = (int)(((float)current/(float)total)*100);
		progressBar.setValue(percentage);
	}
}
