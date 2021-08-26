package defaultIOS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyImageOrData {
	
	public static void main(String[] args) {
		
		File src = new File("C:/JavaJB/flower.jpg");
		File dst = new File("C:/JavaJB/flower_Copy2.jpg");
		
		try(
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dst));
				){
			
			int b = in.read();
			while(b != -1) {
				out.write(b);
				b = in.read();
			}
			System.out.println("file copying complete!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
