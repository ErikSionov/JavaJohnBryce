package arraysHomework;

import java.util.Arrays;

public class Ex_704
{
	public static void main(String[] args)
	{
		int[] array01 = new int[10];
		
		for (int i = 0; i < array01.length; i++)
		{
			array01[i] = (int)(Math.random()*11);
		}
		
		System.out.println(Arrays.toString(array01));
		
		for (int i = 0; i < array01.length/2; i++)
		{
			//should run on half of the array only cause
			//starts to switch the number back to original place
			int t = array01[i];
			array01[i] = array01[array01.length-1-i];
			array01[array01.length-1-i] = t;
		}
		
		System.out.println(Arrays.toString(array01));
	}
}
