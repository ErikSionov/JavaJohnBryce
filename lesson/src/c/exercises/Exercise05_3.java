package c.exercises;

public class Exercise05_3
{
	public static void main(String[] args)
	{
		int r = (int)(Math.random()*101);
		System.out.println(r);
		
		if(r<50) {
			System.out.println("small");
		}else {
			System.out.println("big");
		}
		
		System.out.println(r%2 == 0 ? "Even" : "Odd");
		
	}
}
