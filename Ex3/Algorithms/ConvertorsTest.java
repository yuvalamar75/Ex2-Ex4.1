package Algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class ConvertorsTest {
	
	// we need the boundries of the gps courdinates to build the "convertor object"
	// in this case we used the boundries of the given img
	
	final static double TopRightX=35.21236;
	final static double TopRightY=32.10569;
	final static double LeftButtomX=35.20238;
	final static double LeftButtomY=32.10190;
	
	//build the map to get the img.
	
	Map map = new Map();
	static Convertors  convertor;



	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		
		convertor = new Convertors(642, 1433, LeftButtomX, TopRightX, LeftButtomY, TopRightY);
				
				
			
		
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testPixel2Gps() {

		int [] pixels = {726, 489};
		Point3D result = convertor.pixel2Gps(pixels[0], pixels[1]);
		System.out.println(result.get_y()+", " +result.get_x() );
		
		//the gps courdinates of the movement cycle
		assertEquals(35.207470983949754 , result.get_x(),0.001);
		assertEquals(32.10247853582555 , result.get_y(),0.001);
		
		
	}

	@Test
	void testGps2Pixels() {
	Point3D p = new Point3D(35.207436161898116, 32.102803224299066)	;
	int [] PixelTest = convertor.gps2Pixels(p);
	
	
	//the pixels of the movement cycle
	assertEquals(726 , PixelTest[0]);
	assertEquals(489 ,PixelTest[1]);
	
				
		
	}

}