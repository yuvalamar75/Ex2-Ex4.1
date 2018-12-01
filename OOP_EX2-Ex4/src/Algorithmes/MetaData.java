package Algorithmes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import GIS.Meta_data;
import Geom.Point3D;
/*
 * This class implements the Meta_data interface.
 */
public class MetaData implements Meta_data{
	String[] s;
	long date;
	String s1;
	static final Long duration = (long) (((120 * 60)) * 1000);
	
	 public MetaData(String[] s) {
		 this.s=s;
		 
	}
	 /** returns the Universal Time Clock associated with this data; */
	@Override
	public long getUTC() {
		   
			String time = s[3];                            
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
		s1=s1+"MAC address: "+s[0]+"\n";
		s1=s1+"Channel: "+s[4]+"\n";
		s1=s1+"RSSI: "+s[5]+"\n";
		s1=s1+"Type: "+s[10];
		
		return s1;
	}
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	@Override
	public Point3D get_Orientation() {
		return null;
	}

	

}
