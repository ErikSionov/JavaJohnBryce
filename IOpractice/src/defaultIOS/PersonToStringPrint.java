package defaultIOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PersonToStringPrint {

	public static void main(String[] args) {

		Person[] arr = new Person[3];

		arr[0] = new Person("yossi", 101, 45);
		arr[1] = new Person("Danna", 102, 24);
		arr[2] = new Person("Kathy", 103, 33);

		File src = new File("C:/JavaJB/persons.txt");

		try (PrintWriter writer = new PrintWriter(src);) {
			for (int i = 0; i < arr.length; i++) {
				writer.println(arr[i].toString());
				System.out.println(arr[i].toString());
			}
			System.out.println("writing to file complete!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
