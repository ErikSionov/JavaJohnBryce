package objectOrientedProgramming;

public class Manager extends Employee
{
	
	public Manager() {}
	
	
	/**creates a manager class, extends Employee class
	 * 
	 * 
	 * @param name takes in name of a person
	 * @param salary how much he's getting paid
	 * @param department what department he is working at
	 */
	public Manager(String name, int salary, String depart)
	{
		super(name, salary);
		this.department = depart;
	}

	private String department;

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}
	
}
