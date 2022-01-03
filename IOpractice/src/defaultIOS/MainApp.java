package defaultIOS;

import java.util.Scanner;

public class MainApp {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		 
		System.out.println("input a line");
		
		String input = sc.nextLine();
		
		System.out.println(input);
		
		sc.close();
		
	}
	
}