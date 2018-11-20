package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter{
	final double EARTH_RADIUS=6371000;
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double dlat=local_vector_in_meter.x()/EARTH_RADIUS;
		double dlon=local_vector_in_meter.y()/(EARTH_RADIUS*Math.cos(Math.PI*gps.x()/180));
		double latn=gps.x() + dlat*180/Math.PI;
		double lonn=gps.y() + dlon*180/Math.PI;
		double hn=gps.z()+local_vector_in_meter.z();
		Point3D gpsn=new Point3D(latn,lonn,hn);
		return gpsn;
	}

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
				//**distance**//
				double distance = distance3d(gps0,gps1);
				//**elevation**//
				azimut = Math.toDegrees(azimut);
				if(azimut<0) azimut+=360;
				double high = gps1.z() - gps0.z();
				double eleveation = Math.toDegrees(Math.asin(high/distance));
			
				double arr[] = {azimut,eleveation,distance};
				return arr;

			}

	
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if ((p.x()<180 ||  p.x()>-180) || (p.y()<-90 || p.y()>90) || (p.z()<-450 )) return false;
		return true;
}
}
	




	


