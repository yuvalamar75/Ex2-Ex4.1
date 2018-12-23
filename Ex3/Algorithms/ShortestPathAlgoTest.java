package Algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithmes.GISLayer;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;

class ShortestPathAlgoTest {

	private static Set<GIS_element> fruit ;
	private static Set<GIS_element> pacman ;
	private static ShortestPathAlgo a;
	private static Game game;


	private static Packman p1;
	private static Fruit f1 ;
	private static Fruit f2; 
	private static Fruit f3 ;

	public static void addF(GIS_element f) {
		fruit.add(f);
	}
	public static void addP(GIS_element p) {
		pacman.add(p);
	}





	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		p1 = new Packman(32.1031269784172, 35.2038228915662, 2, 3, 1);
		f1 = new Fruit(32.1030724460431, 35.2045018993621, 1, 1);
		f2 = new Fruit(32.1029565647482, 35.2051596881644, 1, 2);
		f3 = new Fruit(32.1028883992805, 35.2057113819985, 1, 3);

		fruit =  new HashSet<>();
		pacman = new HashSet<>();

		addF(f1);
		addF(f2);
		addF(f3);
		addP(p1);

		Stack<GIS_layer > project = new Stack<>();

		GISLayer fruits = new GISLayer(fruit);
		GISLayer pacmans = new GISLayer(pacman);

		project.push(fruits);
		project.push(pacmans);

		game = new Game(project);

		a = new ShortestPathAlgo(game);
		a.start();
	}

	@Test
	void testStart() {
		ArrayList<Packman> pac = a.getPackmans();

		Packman tempP = pac.get(0);
		Queue<NextStep> next = tempP.getPath();
		int[] a = new int[3];

		a[0] = next.poll().getFruit().getId();
		a[1] = next.poll().getFruit().getId();
		a[2]= next.poll().getFruit().getId();

		assertEquals(1 ,a[0]);
		assertEquals(2 ,a[1]);
		assertEquals(3 ,a[2]);




	}

}
