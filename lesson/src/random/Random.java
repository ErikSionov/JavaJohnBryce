package random;

public class Random
{
	public static int Integer(int a, int b) {
		int mod = 0;
		if(a == 0 || b == 0)
		{
			mod = 1;
		}
		else if(a<0 || b < 0)
		{
			//somehow stop operation as cannot take negative numbers
		}
		if(a>b) {
			int t = a;
			a = b;
			b = t;
		}
		
		int num = (int)(Math.random()*(b+mod) + (a));
		
		
		return num;
	}
}
