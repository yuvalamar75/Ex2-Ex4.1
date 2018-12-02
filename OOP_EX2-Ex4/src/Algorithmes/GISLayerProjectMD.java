package Algorithmes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.org.apache.bcel.internal.generic.NEW;

import GIS.Meta_data;
import Geom.Point3D;
 
/*
 * This class implements the Meta_data interface for the GISProject and GISLayer.
 * return the time that the layer/project created.
 */
public class GISLayerProjectMD implements Meta_data {
	String s1=new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(Calendar.getInstance().getTime());;
	static final Long duration = (long) (((120 * 60)) * 1000);

	@Override
	public long getUTC() {
		String time = s1;  
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

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
		s1="";
		String timeStamp = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(Calendar.getInstance().getTime());
		s1=s1+"Created in: "+timeStamp;
		return s1;
	}

}
