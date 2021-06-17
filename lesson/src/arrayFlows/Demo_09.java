package arrayFlows;

import java.util.Arrays;

public class Demo_09
{
	public static void main(String[] args)
	{
		System.out.println(Arrays.toString(args));
		System.out.println("length : " + args.length);
		System.out.println(sum(1, 2, 3, 6));
		System.out.println(sums(1, 2, 3, 6));
		System.out.println(avg(1, 2, 3, 6));
	}

	static int sum(int... nums)
	{
		int x = 0;
		for (int i = 0; i < nums.length; i++)
		{
			x += nums[i];
		}
		return x;
	}

	static int avg(int... nums)
	{
		int x = 0;
		int dev = 0;
		for (int i = 0; i < nums.length; i++)
		{
			x+= nums[i];
			dev++;
		}
		
		return x/dev;
	}
	
	public static int sums(int... numbers)
	{
		int sum = 0;
		for(int x : numbers) {
			sum += x;
		}
		return sum;
	}
}
