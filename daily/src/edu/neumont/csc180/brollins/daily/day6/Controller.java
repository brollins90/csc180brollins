package edu.neumont.csc180.brollins.daily.day6;

import java.io.File;
import java.io.FileOutputStream;

public class Controller {

	public static void main(String[] args) {

		try {
			
			char c = 'R';
			byte b = 0b01001000; // 'H'
	
			File f = new File("C:\\_\\2.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fs = new FileOutputStream(f);
			fs.write(c);
			fs.close();
			
		} catch (Exception e) {
			
		}
	}
}
