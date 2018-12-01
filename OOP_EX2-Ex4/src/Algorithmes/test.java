package Algorithmes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import File_format.CSVReader;
import GIS.GIS_element;
import GIS.GIS_layer;

public class test {

	public static void main(String[] args) throws java.text.ParseException  {
		// TODO Auto-generated method stub
/*		 Long duration = (long) (((120 * 60)) * 1000);
            
		String time = "2017-12-03 08:54:33";                              
		SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-mm hh:mm:ss");
		java.util.Date dt;
		try {
			dt = df.parse(time);
			Long l = dt.getTime()+duration;
			System.out.println(l);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                      
		

		
	}*/
		
		
		ArrayList<String[]> a=CSVReader.data("D:\\WigleWifi_20171203085618.csv");
		Set<GIS_element> set= new HashSet<>();
		GIS_layer b=new ToGISLayer(set);
		for (int i = 0; i < a.size(); i++) {
			
			ToGisElement c=new ToGisElement(a.get(i));
			b.add(c);
			
			
		}
		ArrayList<String[]> a1=CSVReader.data("WigleWifi_20171201110209.csv");
		Set<GIS_element> set2= new HashSet<>();
		GIS_layer b1=new ToGISLayer(set2);
		for (int i = 0; i < a1.size(); i++) {
			
			ToGisElement c=new ToGisElement(a.get(i));
			b1.add(c);
			
			
		}
		GISProject p=new GISProject();
		p.add(b1);
		p.add(b1);
		
		for (int i = 0; i < p.size(); i++) {
			
		}
		
			
		
	}
}
