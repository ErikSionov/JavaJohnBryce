package oop_02;

public class Point
{
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(int val)
	{
		this(val,val);
	}

	private int x;
	private int y;

	public void moveRight()
	{
		x++;
	}
	
	//method overload
	public void moveRight(int steps)
	{
		x += steps;
	}
}
