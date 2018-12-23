package GIS;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Algorithmes.GISLayer;
import Geom.Point3D;

class GameTest {
	
	private static Set<GIS_element> fruit ;
	private static Set<GIS_element> pacman ;
	static Game game;

	String path  = "C:\\Users\\YuvalAmar\\Documents\\shortestPathTest.csv";
	String pathSaveKML = "C:\\Users\\YuvalAmar\\Documents\\KMLTEST.csv";
	static Stack<GIS_layer> project ; 
	
	public static void addF(GIS_element f) {
		fruit.add(f);
	}
	public static void addP(GIS_element p) {
		pacman.add(p);
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Packman p1 = new Packman(32.1031269784172, 35.2038228915662, 2, 3, 1);
		Fruit f1 = new Fruit(32.1030724460431, 35.2045018993621, 1, 1);
		Fruit f2 = new Fruit(32.1029565647482, 35.2051596881644, 1, 2);
		Fruit f3 = new Fruit(32.1028883992805, 35.2057113819985, 1, 3);

		fruit =  new HashSet<>();
		pacman = new HashSet<>();

		addF(f1);
		addF(f2);
		addF(f3);
		addP(p1);

		project = new Stack<>();

		GISLayer fruits = new GISLayer(fruit);
		GISLayer pacmans = new GISLayer(pacman);

		project.push(fruits);
		project.push(pacmans);

		
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGameStackOfGIS_layer() {
		game = new Game(project);
		
		assertEquals(false, game.getFruitLayer().isEmpty());
		assertEquals(false, game.getPackmanLayer().isEmpty());
	}

	@Test
	void testGameString() {
		game = new Game(path);
		assertEquals(false, game.getFruitLayer().isEmpty());
		assertEquals(false, game.getPackmanLayer().isEmpty());
	}

	@Test
	void testProject2csv() {
		game = new Game(path);
		game.project2csv(game, pathSaveKML);
		File fileKML = new File (pathSaveKML);	
		assertEquals(true, fileKML.exists());
	}

}
