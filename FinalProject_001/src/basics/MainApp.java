package basics;

import java.util.Scanner;

public class MainApp
{
	public static void main(String[] args)
	{
		Maze myMap = new Maze();
		int userMovesNum = 0;

		intro();
		myMap.printMap();
		System.out.println();
		while (!myMap.didIWin())
		{
			checkDirection(myMap, userMove());
			myMap.printMap();
			System.out.println("========MADE A MOVE=========");
			userMovesNum++;
			if (userMovesNum == 100)
			{
				break;
			} else
			{
				movesMessage(userMovesNum);
			}
		}

		if (userMovesNum >= 100)
		{
			System.out
					.println("Sorry, but you didn't escape in time- you lose!");
		} else
		{
			System.out.println("Congratulations, you made it out alive!");
			System.out.println("and you did it in " + userMovesNum + " moves");
		}
		System.exit(0);
	}

	private static void movesMessage(int moves)
	{
		switch (moves)
		{
			case 50 :
				System.out.println("Warning: You have made 50 moves,"
						+ "\nyou have 50 remaining before the maze exit closes.");
				break;
			case 75 :
				System.out.println(
						"Alert! You have 75 moves, you only have 25 moves left to escape.");
				break;
			case 90 :
				System.out.println(
						"DANGER! You have made 90 moves, you only have 10 moves left ot escape!");
				break;
			case 100 :
				System.out.println(
						"Oh no! You took too long to escape, and now the maze exit is closed FOREVER!!! >:[");
				break;
			default :
				break;
		}
	}

	private static void navigatePit(String dir, Maze map)
	{
		Scanner scan02 = new Scanner(System.in);
		System.out.println("Watch out! There's a pit ahead, jump it? (Y, N)");
		String answer = scan02.next();
		if (answer.equals("Y"))
		{
			map.jumpOverPit(dir);
		} else
		{
			System.out.println("You stay before the pit.");
		}
	}

	private static void checkDirection(Maze myMap, String userMove)
	{
		if (myMap.isThereAPit(userMove))
		{
			navigatePit(userMove, myMap);
		}
		else
		{
			if (userMove.equals("R"))
			{
				if (myMap.canIMoveRight())
				{
					myMap.moveRight();
					System.out.println("user moved right");
				} else
				{
					System.out.println("Sorry, you've hit a wall!");
				}

			} else if (userMove.equals("L"))
			{
				if (myMap.canIMoveLeft())
				{
					myMap.moveLeft();
					System.out.println("user moved left");
				} else
				{
					System.out.println("Sorry, you've hit a wall!");
				}

			} else if (userMove.equals("U"))
			{
				if (myMap.canIMoveUp())
				{
					myMap.moveUp();
					System.out.println("user moved up");
				} else
				{
					System.out.println("Sorry, you've hit a wall!");
				}

			} else if (userMove.equals("D"))
			{
				if (myMap.canIMoveDown())
				{
					myMap.moveDown();
					System.out.println("user moved down");
				} else
				{
					System.out.println("Sorry, you've hit a wall!");
				}
			}
		}
	}

	public static void intro()
	{
		System.out.println("Welcome to Maze Runner!");
		System.out.println("Here is your current position:");
	}

	public static String userMove()
	{
		String dir = "none";

		Scanner scan = new Scanner(System.in);
		System.out.println("Where would you like to move? (R, L, U, D)");
		dir = scan.next();
		if (dir.equals("exit"))
		{
			System.out.println("Terminating game...");
			System.exit(0);
		}
		while (!dir.equals("R") && !dir.equals("L") && !dir.equals("U")
				&& !dir.equals("D"))
		{
			System.out.println("Choose appropriate direction.");
			System.out.println("Where would you like to move? (R, L, U, D)");
			dir = scan.next();
		}

		return dir;
	}
}
