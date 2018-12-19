package Algorithms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import GIS.Meta_data;
import Geom.Point3D;

public class MetaDataF implements Meta_data {
	long date;
	String[] data;
	static final Long duration = (long) (((120 * 60)) * 1000);

	public MetaDataF(String [] data) {
		this.data= data;
	}

	@Override
	public long getUTC() {
		String time = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(Calendar.getInstance().getTime());;                            
		SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		java.util.Date dt;
		try {
			dt = df.parse(time);
			Long l = dt.getTime()+duration;
			return l;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                      

		return -1;
	}
	public String toString() {
		String s1="";
		s1=s1+"Type: "+data[0]+"\n";
		s1=s1+"ID: "+data[1]+"\n";
		s1=s1+"Speed: "+data[5]+"\n";
		
		
		return s1;
	}
	public String tostring() {
		String s1="";
		s1=s1+"Type: "+data[0]+"\n";
		s1=s1+"ID: "+data[1]+"\n";
		s1=s1+"Speed: "+data[5]+"\n";
		
		
		return s1;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
