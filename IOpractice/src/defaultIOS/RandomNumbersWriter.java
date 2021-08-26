package defaultIOS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomNumbersWriter {

	public static void main(String[] args) {

		List<Integer> numList = new ArrayList<>();
		File src = new File("C:/JavaJB/numbers.txt");

		for (int i = 0; i < 10; i++) {
			int r = (int) (Math.random() * 6 + 10);
			if (numList.contains(r)) {
				continue;
			} else {
				numList.add(r);
				System.out.println(r);
			}
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(src, true))) {
			writer.write(numList.toString());
			writer.newLine();
			System.out.println("numbers written to file.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
