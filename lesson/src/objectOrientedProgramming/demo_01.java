package objectOrientedProgramming;

public class demo_01
{
	public static void main(String[] args)
	{
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person(1251363, "layla", 121);
		
		p1.setId(10122567);
		p1.setName("Derp");
		p1.setAge(45);
		
		p2.setId(10234647);
		p2.setName("Derpina");
		p2.setAge(38);
		
		p1.getData();
		System.out.println();
		p2.getData();
		System.out.println();
		p3.getData();
		System.out.println();
		
	}
}
