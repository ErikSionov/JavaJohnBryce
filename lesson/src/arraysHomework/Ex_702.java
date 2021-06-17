package arraysHomework;

import java.util.Arrays;

public class Ex_702
{
	public static void main(String[] args)
	{
		int arr[] = new int[50];
		
		int max = arr[0];
		int maxIndex = arr[0];
		int doubleMaxes = -1;
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (int)(Math.random()*101);
			
			if(arr[i] > max)
			{
				max = arr[i];
				maxIndex = i;
			}
	
		}
		
		for (int i = 0; i < arr.length; i++)
		{
			if(arr[i] == max) {
				doubleMaxes++;
			}
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println("the max is " + max + " on index " + maxIndex);
		System.out.println("number of same maximums " + doubleMaxes);
	}
}
