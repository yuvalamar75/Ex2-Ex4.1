package Coords;
/*
 * Test Unit that checks MYCoordsTest functions.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.javafx.geom.AreaOp.AddOp;

import Geom.Point3D;

class MyCoordsTest {
	Point3D a, b , c;
	MyCoords mycoords=new MyCoords();
	double eps=0.0001;


	@BeforeEach
	void setUp() throws Exception {
		 a = new Point3D(32.103315,35.209039,670);
		 b = new Point3D(32.106352,35.205225,650);
		 c = new Point3D(337.6989921,-359.2492069,-20);
	}

	@Test
	void testAdd() {
		Point3D actual=mycoords.add(a, c);
		Point3D expected=b;
		assertEquals(expected.get_x(), actual.get_x(),eps,"Wrong lat cordinate" );
		assertEquals(expected.get_y(), actual.get_y(),eps,"Wrong lon cordinate");
		assertEquals(expected.get_z(), actual.get_z(),eps,"Wrong alt cordinate" );
	}

	@Test
	void testDistance3d() {
		double actual=mycoords.distance3d(a,b);
		double expected=493.0523318;
		assertEquals(expected, actual,eps,"Wrong distance3d");
	
		
	}

	@Test
	void testVector3D() {
		Point3D actual=mycoords.vector3D(a, b);
		Point3D expected=new Point3D(337.69899206128815,-359.24920693881893,-20.0);
		assertEquals(expected.get_x(), actual.get_x(),eps,"Wrong lat in meters" );
		assertEquals(expected.get_y(), actual.get_y(),eps,"Wrong lon in meters");
		assertEquals(expected.get_z(), actual.get_z(),eps,"Wrong alt in meters" );
	}

	@Test
	void testAzimuth_elevation_dist() {
		double[] actual=mycoords.azimuth_elevation_dist(a, b);
		double[] expected= {313.23042032651705, -2.3247635173865278, 493.0523318324134};
		assertEquals(expected[0], actual[0],eps,"Wrong lat in meters" );
		assertEquals(expected[1], actual[1],eps,"Wrong lon in meters");
		assertEquals(expected[2], actual[2],eps,"Wrong alt in meters" );
		
	}

	@Test
	void testIsValid_GPS_Point() {
		boolean actual=mycoords.isValid_GPS_Point(a);
		boolean expected= true;
		assertEquals(expected, actual);
		
	}

}
