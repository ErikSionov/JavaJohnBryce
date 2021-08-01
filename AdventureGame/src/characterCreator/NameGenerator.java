package characterCreator;

import java.util.Random;

public class NameGenerator {

	public static void main(String[] args) {

		String randomName = generateRandomName();

		System.out.println("randomly generated name = " + randomName);

	}

	private static String generateRandomName() {

		String name = "";

		Random random = new Random();

		String[] prefix = {"Da", "Ra", "Na", "Li", "La", "Ma", "So", "Sha", "Ro", "Yo", "A"};
		String[] mid = {"ni", "fa", "ha", "ta", "cha"};
		String[] suffix = {"el", "na", "sha", "m", "tam", "ron", "ha", "shi", "ya", "r"};

		if (random.nextInt(11) >= 4) {

			name = prefix[random.nextInt(prefix.length)] + suffix[random.nextInt(suffix.length)];

		} else {

			name = prefix[random.nextInt(prefix.length)] + mid[random.nextInt(mid.length)] + suffix[random.nextInt(suffix.length)];
		}

		return name;
	}

}
