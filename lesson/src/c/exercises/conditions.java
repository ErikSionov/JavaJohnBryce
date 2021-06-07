package c.exercises;

public class conditions
{

	public static void main(String[] args)
	{
		int num1 = (int)(Math.random()*101);
		int num2 = (int)(Math.random()*101);
		int max = 0;
		System.out.println(num1 + ", " + num2);
		
		if(num1>num2) {
			max = num1;
		}else {
			max = num2;
		}
		
		System.out.println(max + " is max");
	}

}
