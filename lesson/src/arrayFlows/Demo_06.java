package arrayFlows;

import java.util.Arrays;

public class Demo_06
{
	public static void main(String[] args)
	{
		int[] arr = {2,4,6};
		int[] arr2 = new int[5];
		
		//copy elements from arr to arr2
		System.arraycopy(arr, 0, arr2, 2, arr.length);
		
		//assign the arr reference to a new array object
		arr = arr2;
		
		//print
		System.out.println(Arrays.toString(arr));
	}
}
