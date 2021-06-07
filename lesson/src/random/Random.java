package random;

public class Random
{
	public static int Integer(int a, int b) {
		
		if(a>b) {
			int t = a;
			a = b;
			b = t;
		}
		
		int num = (int)(Math.random()*(b+1) + (a));
		
		
		return num;
	}
}
