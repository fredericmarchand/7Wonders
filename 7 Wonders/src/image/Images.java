package image;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Images {
	
	private static HashMap<String, ImageIcon> map;
	private static ArrayList<String> stringList;
	
	private static int total;
	private static int current;
	private static boolean loaded = false;
	
	private static ImageLoadingGUI callback;
	
	public static ImageIcon get(String key) {
		return map.get(key);
	}
	
	// Sets up the array that will keep the data of all the images that require loading.
	// Once the list is built, they will be batch-loaded into a HashMap.
	private static void buildImageList() {
		add("bg", "/image/icons/bg.jpg");
		for(int i = 1; i < 4; i++) add("age"+i, "/image/icons/age"+i+".png");
		add("X", "/image/icons/X.png");
		for(int i = 1; i <= 75; i++) add("card"+i, "/image/cards/"+i+".jpg", 182, 280);
		add("arrow0", "/image/icons/arrowno.png");
		add("arrow1", "/image/icons/arrowtrading.png");
		add("arrow2", "/image/icons/arrowyes.png");
		add("options", "/image/icons/chooser.png");
		add("cardLabelbg", "/image/icons/cardlblbg.png");
		for(int i = 1; i <= 7; i++)	for(int j = 1; j <= 2; j++)	add("boardSmall"+(i-1)+(j-1), "/image/boards/board"+i+j+".png", 320, 150);
		for(int i = 1; i <= 7; i++)	for(int j = 1; j <= 2; j++)	add("boardBig"+(i-1)+(j-1), "/image/boards/board"+i+j+".png");
		add("checkmarkSmall", "/image/icons/yes.png,18,18");
		add("checkmarkBig", "/image/icons/yes.png,34,34");
		add("overlayFar", "/image/icons/overlayforeign.png");
		add("overlayNear", "/image/icons/overlay.png");
		add("res0011000", "/image/icons/resource-0011000.png");
		add("res0101000", "/image/icons/resource-0101000.png");
		add("res0110000", "/image/icons/resource-0110000.png");
		add("res1001000", "/image/icons/resource-1001000.png");
		add("res1010000", "/image/icons/resource-1010000.png");
		add("res1100000", "/image/icons/resource-1100000.png");
		add("res0000111", "/image/icons/resource-0000111.png");
		add("res1111000", "/image/icons/resource-1111000.png");
		add("resOverlay2", "/image/icons/circleselect2.png");
		add("resOverlay3", "/image/icons/circleselect3.png");
		add("resOverlay4", "/image/icons/circleselect4.png");
		add("sciPicker", "/image/icons/sciencepicker.png");
		add("sciOverlay1", "/image/icons/scienceoverlay1.png");
		add("sciOverlay2", "/image/icons/scienceoverlay2.png");
		add("sciOverlay3", "/image/icons/scienceoverlay3.png");
		add("freeBuild", "/image/icons/freebuild.png");
		add("freeBuildOverlay", "/image/icons/freebuildoverlay.png");
		add("tradingpref0", "/image/icons/arrow-left.png");
		add("tradingpref1", "/image/icons/arrow-right.png");
		add("tradingpref2", "/image/icons/random.png");
		add("tradingpref0Over", "/image/icons/arrow-leftOverlay.png");
		add("tradingpref1Over", "/image/icons/arrow-rightOverlay.png");
		add("tradingpref2Over", "/image/icons/randomOverlay.png");
		add("victory", "/image/icons/victory.png");
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
			startLoading(args[1]);
			if(args[2].equals("0") || args[3].equals("0")) map.put(args[0], new ImageIcon(Images.class.getResource(args[1])));
			else map.put(args[0], new ImageIcon(new ImageIcon(Images.class.getResource(args[1])).getImage().getScaledInstance(Integer.parseInt(args[2]), Integer.parseInt(args[3]), java.awt.Image.SCALE_SMOOTH)));
			doneLoadingOne(args[1]);
		}
	}
	
	private static void startLoading(String imagename) {
		if(callback != null) callback.startImage(current, total, imagename);
	}
	
	private static void doneLoadingOne(String imagename) {
		current++;
		if(callback != null) callback.endImage(current, total, imagename);
		//System.out.println("Loaded image " + current + " of " + total + ".");
	}
	
	public static void run() {
		if(!loaded){
			map = new HashMap<String, ImageIcon>();
			stringList = new ArrayList<String>();
			
			buildImageList();
			total = stringList.size();
			current = 0;
			
			System.out.println("Starting to load " + total + " images.");	
			long startTime = System.currentTimeMillis();
			loadImages();
			System.out.println("Done loading all images. Took " + (System.currentTimeMillis() - startTime) + "ms");
			loaded = true;
		}
	}
}
