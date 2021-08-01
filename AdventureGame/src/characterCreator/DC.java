package characterCreator;

public class DC {
	int value;
	
	public static boolean check(int difficulty, Stat stat)
	{
		//roll stat mod + 1d20 and check against difficulty check
		int roll = DiceRoller.roll(1, 20);
		if(roll + stat.getValue() >= difficulty)
		{
			return true;
		}
		
		return false;
	}
	
}

