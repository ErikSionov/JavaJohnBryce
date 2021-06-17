package objectOrientedProgramming;

public class demo_02
{
	public static void main(String[] args)
	{
		Employee e = new Employee("Derp", 5400);
		Manager m = new Manager("Yossi", 12000, "sales");
		
		System.out.println(e.getName() + " get paid " + e.getSalary());
		System.out.println(m.getName() + " get paid " + m.getSalary() + " in the " + m.getDepartment() + " department");
	}
}
