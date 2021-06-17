package arrayFlows;

public class Demo_03
{

	public static void main(String[] args)
	{
		int[] arr = new int[100];
		
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (int)(Math.random()*21);
			System.out.print(arr[i] + ", ");
		}
		System.out.println("\n===========");
		for (int i = 0; i < arr.length; i+=2)
		{
			int x =arr[i]+arr[i+1];
			System.out.print(x + ", ");
		}
	}

}
