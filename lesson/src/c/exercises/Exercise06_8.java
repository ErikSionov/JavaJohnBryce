package c.exercises;

public class Exercise06_8
{
	public static void main(String[] args)
	{
		double sal = (int) (Math.random() * 150_000);
		System.out.println("bruto " + sal);

		double tax = 0;

		if (sal <= 23_000)
		{
			tax = sal * 0.1;
		}
		else 
		{
			tax += 23_000 * 0.1;
			
			if (sal <= 50_000)
			{
				tax += (sal - 23_000) * 0.2;
			}
			else
			{
				tax += 27_000 * 0.2;
				if(sal <= 100_000) {
					tax += (sal - 50_000) * 0.3;
				}
				else
				{
					tax += 50_000 * 0.3;
					tax += (sal - 100_000) * 0.4;
				}
			}
		}
		System.out.println("tax is " + tax);
		System.out.println("net sallary is " + (sal -tax));
	}
}
