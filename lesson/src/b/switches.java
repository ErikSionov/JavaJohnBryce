package b;

public class switches
{
	public static void main(String[] args)
	{
		String key = "OFF"; // set machine state
		
		switch (key) // declare a switch
		{
			case "OFF" :
				System.out.println("red");
				break;
			case "STAND_BY" :
				System.out.println("yellow");
				break;
			case "ON" :
				System.out.println("green");
				break;
			default :
				System.out.println("undefined");
				break;
		}
	}
}
