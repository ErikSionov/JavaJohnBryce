package arraysHomework;

import java.util.Arrays;
import java.util.Iterator;

public class Ex_703
{
	public static void main(String[] args)
	{
		int[] arr = new int[10];
		int[] arr2;
		
		int arraySize = 0;
		
		
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (int)(Math.random()*11);
		}
		
		System.out.println(Arrays.toString(arr));
		
		for (int i = 0; i <= 10; i++)
		{
			for (int j = 0; j < arr.length; j++)
			{
				if(i == arr[j])
				{
					arraySize++;
					break;
				}
			}
		}
		
		
		System.out.println("size of the second array " +arraySize);
		arr2 = new int[arraySize];
		
		int index = 0;
		
		l : for (int i = 0; i < arr.length; i++)
		{
			//make sure to run on the external index value and not the
			//second array length as may cause runtime exceptions
			for (int j = 0; j < index; j++)
			{
				if(arr[i] == arr2[j])
				{
					continue l;
				}
				
			}
				arr2[index] = arr[i];
				index++;
		}
		System.out.println(Arrays.toString(arr2));
		int[] arrayCopy = new int[index];
		//copying array by arraycopy function
		//place to copy from, starting copy position, the array to copy into, starting position of the second array, and ending index of the second array
		System.arraycopy(arr2, 0, arrayCopy, 0, arrayCopy.length); 
		System.out.println("check array copy function " + Arrays.toString(arrayCopy));
	}
}
