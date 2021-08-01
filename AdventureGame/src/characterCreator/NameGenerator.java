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
		
		String[] preffix = {"Da"};
		String[] mid = {"ni"};
		String[] suffix = {"el"};
		
		if(random.nextInt(11) >= 4) {
			
			name = preffix[random.nextInt(preffix.length)] + suffix[random.nextInt(suffix.length)];
			
		}else {
			
			name = preffix[random.nextInt(preffix.length)] + mid[random.nextInt(mid.length)] + suffix[random.nextInt(suffix.length)];
		}
		
		return name;
	}
	
}
