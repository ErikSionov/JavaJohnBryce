package b;

public class forLoop
{
	public static void main(String[] args)
	{
		System.out.println("==all numbers untill 100 devided by 5 and 7==");
		
		for (int i = 1; i <= 100 ; i++)
		{
			if(i % 5 == 0 || i%7 == 0)
			{
				System.out.println(i);
			}
			
		}
		
		System.out.println("==all numbers untill 100 devided by 5==");
		
		for (int i = 5; i <= 100; i= i+5)
		{
			System.out.println(i);
		}
		
		System.out.println("==factorial==");

		int rand = (int)(Math.random()*11);
		System.out.println(rand);
		
		int f = 1;
		
		for (int i = 2; i <= rand; i++)
		{
			f = f * i ;
		}
		
		System.out.println(f);
		
	}
}
