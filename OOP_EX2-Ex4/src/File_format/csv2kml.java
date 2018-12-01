package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Algorithmes.GISLayer;
import Algorithmes.ToGisElement;
import Coords.MyCoords;

import GIS.GIS_element;
import GIS.GIS_layer;

/*
 * this class crate new kml file from csv. 
 */
public class csv2kml {
	/*
	 * This function get path of csv file,convert the file to kml and save in the destination we get.
	 * @param String for input path.
	 * @param String for output path 
	 */
	public static void writeFileKML(String s,String output) {
	
		ArrayList<String> content =new ArrayList<String>();
		ArrayList<String[]> get=CSVReader.data(s);
		ToGisElement c=new ToGisElement(get.get(0));
		Set<GIS_element> set= new HashSet<>();
		GISLayer b=new GISLayer(set);
		for (int i = 0; i < get.size(); i++) {
			
			ToGisElement r=new ToGisElement(get.get(i));
			b.add(r);
		}
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		String kmlduc = "<Document>";
		String kmlduce = "</Document>";
		String kmlend = "</kml>";
		content.add(kmlstart);
		content.add(kmlduc);

		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<GIS_element> it = b.iterator(); 
			while(it.hasNext()) {
				c=(ToGisElement) it.next();
				
				String kmlelement ="<Placemark>\n" +
						"<name>"+c.getname()+"</name>\n" +
						"<description>\n"+c.getData()+"/n"+"</description>\n" +
						"<Point>\n" +
						"<coordinates>"+c.getPoint().get_y()+"&"+c.getPoint().get_x()+"&"+c.getPoint().get_z()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";
				content.add(kmlelement);
			}
			content.add(kmlduce);
			content.add(kmlend);
			String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
			csv=csv.replaceAll("&", ",");
			System.out.println(csv);
			bw.write(csv);
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String csvFile = "D:\\WigleWifi_20171203085618.csv";
		String output = "D:\\newnew21212121888.kml";
		
		writeFileKML(csvFile,output);
		
		

	}

}

