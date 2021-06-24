package d;

public class Animal
{
	private String name;
	private String gender;
	private int apendage;
	private boolean fur;
	private boolean tail;
	private int age;
	final String classification = "Animal";
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getApendage()
	{
		return apendage;
	}
	public void setApendage(int apendage)
	{
		this.apendage = apendage;
	}
	public boolean isFur()
	{
		return fur;
	}
	public void setFur(boolean fur)
	{
		this.fur = fur;
	}
	public boolean isTail()
	{
		return tail;
	}
	public void setTail(boolean tail)
	{
		this.tail = tail;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		if(age < 120 && age > 0)
		{
			this.age = age;
		}
		else
		{
			System.out.println("trying to set age out of range");
		}
	}
	
	public void getInfo()
	{
		System.out.print(getName() + ", with " + getApendage() + " apendage. " + (isFur() == true? "with fur" : "no fur") + ", " + (isTail() == true? "with tail" : "no tail") + ". " + "gender: " +getGender());
		System.out.print(" age: " + getAge());
	}
	
}
