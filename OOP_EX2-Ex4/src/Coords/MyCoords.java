package Coords;

import Geom.Point3D;
/**
 * This class implement coords_converter interface
 * The class has functions that Performs operations on GPS coordinates.
 *	Like adding,distanced,vectorin3D etc.
 *
 * 
 * @author YuVaLAndDvIr
 *
 */


public class MyCoords implements coords_converter{
	final double EARTH_RADIUS=6371000;
	/* computes a new point which is the gps point transformed by a 3D vector (in meters)/
	 * 
	 */
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double Lon_Norm=Math.cos(gps.get_x()*Math.PI/180);
		double x=Math.asin(local_vector_in_meter.x()/EARTH_RADIUS)*(180/Math.PI)+gps.x();
		double y=Math.asin(local_vector_in_meter.y()/(EARTH_RADIUS*Lon_Norm))*(180/Math.PI)+gps.y();
		double z=gps.z()+local_vector_in_meter.z();
		Point3D gpsn=new Point3D(x,y,z);
		return gpsn;
		
		
	}
	/** 
	 * computes the 3D distance (in meters) between the two gps like points 
	 * @param gps0
	 * @param gps1
	 * @return
	 */
public double distance3d(Point3D gps0, Point3D gps1) {
		double lonnorm=Math.cos(gps0.x()*(Math.PI/180));
		double diflon;
		double diflat;
		double difalt;

		double dif_radlat;
		double dif_radlon;
		double altmeter;
		double lonmeter;
		 diflat = gps1.x()-gps0.x();
		 diflon = gps1.y()-gps0.y();
		 difalt = gps1.z()-gps0.z();
		 dif_radlat = diflat*Math.PI/180;
		 dif_radlon = diflon*Math.PI/180;
		 altmeter = Math.sin(dif_radlat)*EARTH_RADIUS;
		 lonmeter = Math.sin(dif_radlon)*EARTH_RADIUS*lonnorm;
		
		 double distance= Math.sqrt(Math.pow(altmeter, 2)+Math.pow(lonmeter, 2));
		 return distance;
		
	}
/** 
 * computes the 3D vector (in meters) between two gps like points 
 * */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {

		double lonNorm=Math.cos(gps0.x()*Math.PI/180);
		double diff_lat =gps1.x()-gps0.x();
		double diff_lon=gps1.y()-gps0.y();
		double diff_z=gps1.z()-gps0.z();
		double diff_radianlat=diff_lat*Math.PI/180;
		double diff_radianlon=diff_lon*Math.PI/180;
		double toMeterlat=Math.sin(diff_radianlat)*EARTH_RADIUS;
		double toMeterlon=Math.sin(diff_radianlon)*lonNorm*EARTH_RADIUS;
		return new Point3D(toMeterlat, toMeterlon, diff_z);
		
		
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
				double longps0 = Math.toRadians(gps0.y()); 
				double longps1 = Math.toRadians(gps1.y()); 
				double latgps0 = Math.toRadians(gps0.x()); 
				double latgps1 = Math.toRadians(gps1.x()); 
				double delta = longps1 - longps0;
				double left = Math.sin(delta)*Math.cos(latgps1);
				double right = Math.cos(latgps0)*Math.sin(latgps1)-Math.sin(latgps0)*Math.cos(latgps1)*Math.cos(delta);
				double	azimut = Math.atan2(left, right);
				double distance = distance3d(gps0,gps1);
				azimut = Math.toDegrees(azimut);
				if(azimut<0) azimut+=360;
				double high = gps1.z() - gps0.z();
				double eleveation = Math.toDegrees(Math.asin(high/distance));
			
				double arr[] = {azimut,eleveation,distance};
				return arr;

			}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if ((p.x()<=180 && p.x()>=-180) && (p.y()>=-90 && p.y()<=90) && (p.z()>=-450 )) return true;
		return false;
}
}
	




	


