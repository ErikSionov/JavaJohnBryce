package c.exercises;

public class Exercise05_6
{
	public static void main(String[] args)
	{
		int r = (int)(Math.random() * 51);
		System.out.println("index " + r);
		
		long f1 = 1;
		long f2 = 1;
		System.out.print(f1 + ", ");
		
		for(int i = 3; i <=r; i++)
		{
			System.out.print(f2 + ", ");
			f2 = f1 + f2;
			f1 = f2 - f1;
		}
		
		System.out.println(f2);
	}
}
