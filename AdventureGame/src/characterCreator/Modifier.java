package characterCreator;

public class Modifier
{
	private String name;
	private int value;
	
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "Modifier [name=" + name + ", value=" + value + "]";
	}
}

