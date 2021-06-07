package b;

public class FunctionsAndMethods
{
	public static void main(String[] args)
	{
		int res = sum (4, 9);
		System.out.println(res);
		System.out.println(sum(5,5));
		System.out.println(sum(5,5,7));
	}
	
	//method definition
	static int sum (int a, int b)
	{
		
		return a + b;
	}
	static int sum (int a, int b, int c)
		{
			return a + b + c;
		}
}
