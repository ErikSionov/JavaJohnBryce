package objectOrientedProgramming;

public class PointDemo
{
	public static void main(String[] args)
	{
		Point p1 = new Point(10,10);
		Point p2 = new Point(6,12);
		
		System.out.print("p1 location is ");
		p1.GetPointData();
		
		p1.moveDown();
		p1.moveDown();
		p1.moveRight();
		p1.moveRight();
		
		System.out.print("p1 location is ");
		p1.GetPointData();
	}
}
