package File_format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import Algorithmes.ToGisElement;
import Coords.MyCoords;

public class test {
	
	public static void main(String[] args) {
	ArrayList<String[]> a=CSVReader.data("D:\\WigleWifi_20171203085618.csv");
	String[] data=a.get(100);
	System.out.println(Arrays.toString(data));
	ToGisElement mygis=new ToGisElement(data);
	System.out.println(mygis.getData().getUTC());
	System.out.println(mygis.getData().toString());
	System.out.println(mygis.getPoint().get_x());
	
	
	

}}
