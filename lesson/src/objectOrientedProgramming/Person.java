package objectOrientedProgramming;

public class Person
{
	//attributes
	private int id;
	private String name;
	private int age;
	
	public Person()
	{
		
	}
	
	public Person(int id, String name, int age)
	{
		this.id = id;
		this.name = name;
		this.setAge(age);
	}
	
	public int getAge()
	{
		return age;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public void setAge(int age)
	{
		if(age >= 0 && age <= 120)
		{
			this.age = age;
		}
		else
		{
			System.out.println("age of " + name + " is out of human age range please try 0 to 120");
		}
	}
	
	//methods
	void speak()
	{
		System.out.println(name + " is speaking");
	}
	
	void walk()
	{
		System.out.println(name + "is walking");
	}
	
	public void getData()
	{
		System.out.println("this is " + name + ".");
		System.out.println("He is " + age + " years old.");
		System.out.println("His/Her Id is " + id + ".");
	}
}
