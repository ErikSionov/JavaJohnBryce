package b;

public class switches2
{
	public static void main(String[] args)
	{
		//randomize a number between 0-10
		int grade = (int)(Math.random()*11);
		System.out.println(grade);
		
		//check grade with a switch statement 
		switch(grade)
		{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.println("fail");
				break;
			case 6:
				System.out.println("pass");
				break;
			case 7:
			case 8:
				System.out.println("good");
				break;
			case 9:
			case 10:
				System.out.println("excellent");
				break;
		}
		
	}
}
