package oop_02;

public class Pixel extends Point
{
	
	private String color;
	
	public Pixel(int x, int y, String color)
	{
		super(x,y);
		this.color = color;
	}
}
