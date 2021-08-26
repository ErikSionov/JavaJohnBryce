package defaultIOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WriteFileToScreen {
	public static void main(String[] args) {
		String filePath = "c:/JavaJB/";
		File file = new File(filePath);

		for (int i = 0; i < file.listFiles().length; i++) {
			System.out.println(i + " " + file.listFiles()[i]);
		}

		System.out.println("choose a filenumber to print");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		file = file.listFiles()[choice];
		
		System.out.println(file.toString());
		
		try (FileReader in = new FileReader(file)) {

			int character = in.read();
			System.out.print((char) character);
			while (character != -1) {
				character = in.read();
				System.out.print((char) character);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		sc.close();
	}
}
