package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Csv {
	String filename = "WigleWifi_20171201110209.csv";
	File file = new File (filename);
	try {
		Scanner inputStream = new Scanner(file);
	}catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	

}
