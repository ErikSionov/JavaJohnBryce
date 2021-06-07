package a;

import java.util.Scanner;

public class minmax {

	public static void main(String[] args) {
		
		System.out.println("insert up to 100 numbers to find the second max");
		
		int m1, m2, n;
		
		Scanner scan = new Scanner(System.in);
		
		m1 = scan.nextInt();
		
		System.out.println("input another number");
		m2 = scan.nextInt();
		
		if (m1 < m2)
		{
			int t = m1;
			m1 = m2;
			m2 = t;
		}
		
		System.out.println("first max number is: " +m1 + " and second max is " +m2);
		
		for (int i = 3; i < 100; i++) {
			System.out.println("input another number");
			n = scan.nextInt();
			
			if (m1 == m2) {
				if (n > m1) m1=n;
			}
			else {
				if (n > m1) { m2=m1; m1=n;}
				else if(n < m1 && n > m2) m2=n;
			}
			
			System.out.println("max numbers are: " +m1 + " and " +m2);
			
		}
	}

}
