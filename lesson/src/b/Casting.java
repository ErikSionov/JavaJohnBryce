package b;

public class Casting
{
	public static void main(String[] args)
	{
		byte x = 100;
		int y = x; //casted a byte into integer (implicit)
		
		System.out.println(x);
		System.out.println(y);
		
		int a = 100;
		byte b = (byte)a; //cast from integer to byte (explicitly)
		
		System.out.println(b);
	}
}
