package File_format;

import java.util.Vector;

import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;

public class GisWriter implements GIS_element{
	Vector<GPSObject> gpsvector;
		
	 public GisWriter(Vector<GPSObject> gpsvector) {
		 for (int i = 0; i < gpsvector.size(); i++) {
			this.longi = gpsvector.get(i).CurrentLongitude;
			this.lat = gpsvector.get(i).CurrentLatitude;
			this.firstseen = gpsvector.get(i).FirstSeen;
			this.rssi = gpsvector.get(i).RSSI;
		} 
	}
	

	@Override
	public Geom_element getGeom() {
		return null;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		
	}

}
