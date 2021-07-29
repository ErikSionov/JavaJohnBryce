package c.exercises;
import random.Random;


public class ternaryOperator
{
	public static void main(String[] args)
	{
		double a = Math.random();
		System.out.println(a);
		//ternary Operator as in three 
		String m;
		m = a > 0.5 ? "big" : "small";
		System.out.println(m);
		
		System.out.println(Random.Integer(1, 10));
	}
}
