package mainMenu;

import java.util.Scanner;

import characterCreator.Character;
import characterCreator.Race;

public class MainApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input;
		Character player01 = new Character("Drago");
		player01.setRace(Race.randomRace());
		System.out.println(player01.getStats()[0].mod());
		player01.getStatsInfo();
		System.out.println("Welcome to a character creation screen\n" + "choose your race: HUMAN, DWARF, ELF, ORC, HALFLING");
		while(true) {
			try {
				input = scan.nextLine().toUpperCase();
				player01.setRace(Race.valueOf(input));
			}catch(Exception e) {
				System.out.println("please choose one of the races.");
				continue;
			}
			break;
		}
		
		scan.close();
		System.out.println("end of program!");
	}

}
