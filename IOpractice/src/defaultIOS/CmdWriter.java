package defaultIOS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CmdWriter {

	public static void main(String[] args) {

		
		System.out.println();
		File src = new File("C:/JavaJB/cmdToFile.txt");

		
		for (String s : args) {
			if (s.equalsIgnoreCase("h") || s.equalsIgnoreCase("\\h")|| s.equalsIgnoreCase("/h")) {
				System.out.println("use this to write text into files, using \"\" to indicate a whole line.");
				continue;
			}
			if(s.equalsIgnoreCase("d") || s.equalsIgnoreCase("\\d") || s.equalsIgnoreCase("/d")) {
				System.out.println("deleted file at c:/JavaJB/cmdToFile.txt");
				src.delete();
				continue;
			}
			if (s.equalsIgnoreCase("EXIT")) {
				System.out.println("exiting...");
				System.exit(1);
			} else {
				writeToFile(src, s);
				continue;
			}
		}

	}

	private static void writeToFile(File src, String s) {
		
		try(BufferedWriter out = new BufferedWriter(new FileWriter(src, true))){
			
			out.write(s);
			out.write("\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
