package File_format;

import java.util.Vector;

import Coords.MyCoords;

public class test {
	
	public static void main(String[] args) {
		CSVReader CSVReader = new CSVReader();
		Vector<GPSObject> a=CSVReader.CvsReader("WigleWifi_20171203085618.csv");
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).FirstSeen);
			
		}
	}

}
