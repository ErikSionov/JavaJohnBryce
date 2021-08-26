package defaultIOS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UsingPrintWriter {
	public static void main(String[] args) {

		File file = new File("C:/JavaJB/letter.txt");

		File copy = new File("C:/JavaJB/letter_copy.txt");

		try (PrintWriter out = new PrintWriter(copy); BufferedReader in = new BufferedReader(new FileReader(file));) {
			
			String str = in.readLine();
			while(str != null) {
				out.println(str);
				str = in.readLine();
			}
			System.out.println("printed into the file");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
