package d;

public class Human extends Animal
{
	private String race;
	private String eyeColor;
	private String hairColor;
	final String classification = "Human";
	
	public String getRace()
	{
		return race;
	}
	public void setRace(String race)
	{
		this.race = race;
	}
	public String getEyeColor()
	{
		return eyeColor;
	}
	public void setEyeColor(String eyeColor)
	{
		this.eyeColor = eyeColor;
	}
	public String getHairColor()
	{
		return hairColor;
	}
	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
	}
	
	public void getInfo()
	{
		super.getInfo();
		System.out.print(" race: " + getRace());
	}
	
}
