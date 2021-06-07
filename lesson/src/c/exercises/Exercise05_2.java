package c.exercises;

public class Exercise05_2
{
	public static void main(String[] args)
	{
		int r = (int)(Math.random()*101);
		System.out.println(r);
		
		if(r<50) {
			System.out.println("small");
		}else if(r>50) {
			System.out.println("big");
		}else {
			System.out.println("BINGO");
		}
		
	}
}
