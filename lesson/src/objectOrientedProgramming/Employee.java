package objectOrientedProgramming;

public class Employee
{
	private String name;
	private int Salary;

	/**creates an Employee object with name and salary
	 * 
	 */
	public Employee() {}
	
	
	
	/**creates an Employee object
	 * 
	 * 
	 * @param name takes in a string name
	 * @param salary takes in an int salary
	 */
	public Employee(String name, int salary)
	{
		this.name = name;
		this.Salary = salary;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSalary()
	{
		return Salary;
	}
	public void setSalary(int salary)
	{
		Salary = salary;
	}

}
