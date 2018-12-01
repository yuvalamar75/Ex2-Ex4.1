package Coords;

import java.util.Arrays;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import Geom.Point3D;
import jdk.net.NetworkPermission;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyCoords myCoords = new MyCoords();
		
		Point3D a = new Point3D(32.103315,35.209039,670);
		
		Point3D b = new Point3D(32.106352,35.205225,650);
		
		System.out.println(Arrays.toString(myCoords.azimuth_elevation_dist(a, b)));
		
		
		
		
		Point3D c = new Point3D(337.6989921,-359.2492069,-20);
		Point3D d= myCoords.add(a, c);
		
		double x = myCoords.distance3d(a,b);
		System.out.println(x);
		Point3D v=myCoords.vector3D(a, b);
		System.out.println("vector3d"+v);
		Point3D bnew=myCoords.add(a, v);
		System.out.println(bnew);
		double[] ad = myCoords.azimuth_elevation_dist(a,b);
		System.out.println(Arrays.toString(ad));
		System.out.println(myCoords.isValid_GPS_Point(a));
		
		
		
		
		

	}

}
