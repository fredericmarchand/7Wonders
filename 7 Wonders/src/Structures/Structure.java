package Structures;

import Tokens.Resources;


public class Structure {

	private static final int INVALID = 0x00;
	public static final int RED_CARD = 0x10;
	public static final int BLUE_CARD = 0x20;
	public static final int BROWN_CARD = 0x30;
	public static final int GREY_CARD = 0x40;
	public static final int YELLOW_CARD = 0x50;
	public static final int GREEN_CARD = 0x60;
	public static final int PURPLE_CARD = 0x70;
	
	protected Resources resourceCost;
	protected int ID;
	protected String name;
	protected int color;
	protected int age;
	
	//Constructors
	public Structure()
	{
		resourceCost = new Resources();
		ID = 0;
		name = "";
		color = INVALID;
		age = 0;
	}
	
	public Structure(Resources resourceCost, int id, String name, int color, int age)
	{
		this.resourceCost = resourceCost;
		ID = id;
		this.name = name;
		this.color = color;
		this.age = age;
	}
	
	public Structure(Structure s)
	{
		resourceCost = s.resourceCost;
		ID = s.ID;
		this.name = s.name;
		this.color= s.color;
		this.age = s.age;
	}
	
	//getters
	public Resources getResourceCost()
	{
		return resourceCost;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public int getAge()
	{
		return age;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
