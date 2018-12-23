package Algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithmes.GISLayer;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Packman;
import Geom.Point3D;

class PathTest {
	ArrayList<Fruit> fruit ;
	Set<GIS_element> pacman ;
	int fruitId;
	double time;
	Packman p1;
	Fruit f1 ;
	Fruit f2; 
	Fruit f3 ;
	Path p ;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		p1 = new Packman(32.1031269784172, 35.2038228915662, 2, 3, 1);
		f1 = new Fruit(32.1030724460431, 35.2045018993621, 1, 1);
		f2 = new Fruit(32.1029565647482, 35.2051596881644, 1, 2);
		f3 = new Fruit(32.1028883992805, 35.2057113819985, 1, 3);
		fruit = new ArrayList<>();


		fruit.add(f1);
		fruit.add(f2);
		fruit.add(f3);
		
		p= new Path(p1, fruit);
		p.BuildPath();
	}

	@Test
	void testPath() {


		

		NextStep f = p.getNext();
		fruitId = f.getfId();
		System.out.println(fruitId);
		assertEquals(1, fruitId);
	}

	@Test
	void testTime() {
		NextStep f = p.getNext();
		time = f.getTime();
		System.out.println(time);
		
		assertEquals(30.622100851797526, time);;
		
	}

}


