package Images;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Images {
	
	private static HashMap<String, ImageIcon> map;
	private static ArrayList<String> stringList;
	
	private static int total;
	private static int current;
	
	private static ImageLoadingGUI callback;
	
	public static ImageIcon get(String key) {
		return map.get(key);
	}
	
	// Sets up the array that will keep the data of all the images that require loading.
	// Once the list is built, they will be batch-loaded into a HashMap.
	private static void buildImageList() {
		for(int i = 1; i < 4; i++) add("age"+i, "/Images/Icons/age"+i+".png");
		add("X", "/Images/Icons/X.png");
		for(int i = 1; i <= 75; i++) add("card"+i, "/Images/Cards/"+i+".jpg", 182, 280);
		add("arrow0", "/Images/Icons/arrowno.png");
		add("arrow1", "/Images/Icons/arrowtrading.png");
		add("arrow2", "/Images/Icons/arrowyes.png");
		add("options", "/Images/Icons/chooser.png");
		add("cardLabelbg", "/Images/Icons/cardlblbg.png");
		for(int i = 1; i <= 7; i++)	for(int j = 1; j <= 2; j++)	add("boardSmall"+(i-1)+(j-1), "/Images/Boards/board"+i+j+".png", 320, 150);
		for(int i = 1; i <= 7; i++)	for(int j = 1; j <= 2; j++)	add("boardBig"+(i-1)+(j-1), "/Images/Boards/board"+i+j+".png");
		add("checkmarkSmall", "/Images/Icons/yes.png,18,18");
		add("checkmarkBig", "/Images/Icons/yes.png,34,34");
		add("overlayFar", "/Images/Icons/overlayforeign.png");
		add("overlayNear", "/Images/Icons/overlay.png");
		add("res0011000", "/Images/Icons/resource-0011000.png");
		add("res0101000", "/Images/Icons/resource-0101000.png");
		add("res0110000", "/Images/Icons/resource-0110000.png");
		add("res1001000", "/Images/Icons/resource-1001000.png");
		add("res1010000", "/Images/Icons/resource-1010000.png");
		add("res1100000", "/Images/Icons/resource-1100000.png");
		add("res0000111", "/Images/Icons/resource-0000111.png");
		add("res1111000", "/Images/Icons/resource-1111000.png");
		add("resOverlay2", "/Images/Icons/circleselect2.png");
		add("resOverlay3", "/Images/Icons/circleselect3.png");
		add("resOverlay4", "/Images/Icons/circleselect4.png");
		add("sciPicker", "/Images/Icons/sciencepicker.png");
		add("sciOverlay1", "/Images/Icons/scienceoverlay1.png");
		add("sciOverlay2", "/Images/Icons/scienceoverlay2.png");
		add("sciOverlay3", "/Images/Icons/scienceoverlay3.png");
	}
	
	private static void add(String name, String path) {
		stringList.add(name+","+path+",0,0");
	}
	
	private static void add(String name, String path, int width, int height) {
		stringList.add(name+","+path+","+width+","+height);
	}
	
	public static void setGUI(ImageLoadingGUI g) {
		callback = g;
	}
	
	// Loops through the image list and loads them based on the parameters given
	// The syntax for the string for an image is the following:
	//   "<name>,<path>,<width>,<height>"
	// If the width and height are set to 0, it is not resized and is kept as default
	private static void loadImages() {
		for (String s : stringList) {
			String[] args = s.split(",");
			if(args[2].equals("0") || args[3].equals("0")) map.put(args[0], new ImageIcon(Images.class.getResource(args[1])));
			else map.put(args[0], new ImageIcon(new ImageIcon(Images.class.getResource(args[1])).getImage().getScaledInstance(Integer.parseInt(args[2]), Integer.parseInt(args[3]), java.awt.Image.SCALE_SMOOTH)));
			doneLoadingOne();
		}
	}
	
	private static void doneLoadingOne() {
		current++;
		if(callback != null) callback.update(current, total);
		//System.out.println("Loaded image " + current + " of " + total + ".");
	}
	
	public static void run() {
		map = new HashMap<String, ImageIcon>();
		stringList = new ArrayList<String>();
		
		buildImageList();
		total = stringList.size();
		current = 0;
		
		System.out.println("Starting to load " + total + " images.");	
		long startTime = System.currentTimeMillis();
		loadImages();
		System.out.println("Done loading all images. Took " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
