package Coords;

import java.util.Arrays;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyCoords myCoords = new MyCoords();
		
		Point3D a = new Point3D(32.103315,35.209039,670);
		Point3D b = new Point3D(32.106352,35.205225,650);
		double x = myCoords.distance3d(a,b);
		System.out.println(x);
		Point3D v=myCoords.vector3D(a, b);
		System.out.println(v);
		Point3D bnew=myCoords.add(a, v);
		System.out.println(bnew);
		double[] ad = myCoords.azimuth_elevation_dist(a,b);
		System.out.println(Arrays.toString(ad));
		
		
		
		
		

	}

}
