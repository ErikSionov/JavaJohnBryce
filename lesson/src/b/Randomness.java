package b;

public class Randomness
{
	public static void main(String[] args)
	{
		// random number 0 - 10
		int r = (int) (Math.random() * 11);
		System.out.println(r);

		if (r > 5)
		{
			System.out.println("Thicc");
		}
		else System.out.println("Smol");

	}
}
