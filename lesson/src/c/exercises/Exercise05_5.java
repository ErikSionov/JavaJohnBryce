package c.exercises;

public class Exercise05_5
{
	public static void main(String[] args)
	{
		int a = (int)(Math.random()*101);
		int b = (int)(Math.random()*101);
		int c = (int)(Math.random()*101);
		
		
		System.out.println(a + ", " +b + ", " +c);
		
		if(a>b) {
			if(a>c) {
				System.out.println("max is " +a);
			}else {
				System.out.println("max is " +c);
			}
		}else {
			if(b>c) {
				System.out.println("max is " +b);
			}else {
				System.out.println("max is " +c);
			}
		}
	}
}
