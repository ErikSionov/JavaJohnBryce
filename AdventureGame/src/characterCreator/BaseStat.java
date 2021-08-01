package characterCreator;

public class BaseStat
{
	private String name;
	private int value;
	int totalModifiers;
	private Modifier[] modifiers = new Modifier[100];
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public Modifier[] getModifiers()
	{
		return this.modifiers;
	}
	
	public void addModifier(String name, int bonus)
	{
		
		for (int i = 0; i < this.modifiers.length; i++)
		{
			if(this.modifiers[i] == null)
			{
				modifiers[i] = new Modifier();
				modifiers[i].setName(name);
				modifiers[i].setValue(bonus);
				String bonusType = (bonus >= 0) ? "+" : "";
				
				System.out.println("Modifier added: " + name + " | Bonus: " + bonusType + bonus);
				break;
			}
		}
	}
	
	public int calculateModifiers()
	{
		int sum = 0;
		
		for (int i = 0; i < this.getModifiers().length; i++)
		{
			if(this.getModifiers()[i] != null)
			{
				sum += this.getModifiers()[i].getValue();
			}
		}
		
		return sum;
	}
	
	@Override
	public String toString()
	{
		String statInfo = "|" + this.getName() + " \t| Base: " + this.getValue() + " \t| Bonus: " + this.calculateModifiers();
		
		return statInfo;
	}
}
