package b;

public class DemoIfElse
{

	public static void main(String[] args)
	{
		int grade = (int)(Math.random() * 11);
		System.out.println("Yossi got a grade of " + grade);
		
		if(grade < 6) {
			System.out.println("fail");
		}else if(grade == 6) {
			System.out.println("enough");
		}else if(grade <= 8) {
			System.out.println("good");
		}else {
			System.out.println("excellent");
		}
	}

}
