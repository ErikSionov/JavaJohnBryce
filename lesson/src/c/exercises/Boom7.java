package c.exercises;

public class Boom7
{
	public static void main(String[] args)
	{
		for (int i = 0; i <= 100; i++)
		{
			if (i % 7 == 0 && i!=0)
			{
				System.out.println("boom");
				continue;
			}
			else
			{
				int n = i;
				while (n != 0)
				{
					if (n % 10 == 7)
					{
						System.out.println("boom");
						break;
					} 
					else
					{
						n = n / 10;
					}

				}
				if (n==0) {
					System.out.println(i);
				}
			}
			
		}
	}
}
