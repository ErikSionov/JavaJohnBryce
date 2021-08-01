package characterCreator;

public class DiceRoller
{

	/**
	 * rolls a number of dice n with amount of 1 to dice faces equals to
	 * diceType
	 * 
	 * @param numberOfDice
	 *            number of dice to roll
	 * @param diceType
	 *            dice type, d4, d6, d8, d10, d12, d20, d100
	 * @return sum of dice rolled
	 */
	public static int roll(int numberOfDice, int diceType)
	{
		int diceRoll = 0;
		int sum = 0;

		for (int i = 1; i <= numberOfDice; i++)
		{
			diceRoll = (int) (Math.random() * diceType + 1);
			sum += diceRoll;

		}

		return sum;
	}

	/**same as DiceRoller.roll only adds a log of the roll
	 * 
	 *  @see DiceRoller.roll 
	 */
	public static int rollVisible(int numberOfDice, int diceType)
	{
		int sum = roll(numberOfDice, diceType);
		System.out.println(numberOfDice + "d" + diceType + " rolled for " + sum);

		return sum;

	}
}
