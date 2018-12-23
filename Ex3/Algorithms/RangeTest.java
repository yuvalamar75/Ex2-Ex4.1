package Algorithms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RangeTest {

	
	static Range range;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		range = new Range(200, 300);
	}

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	void testDistance() {
		
		double distance = range.distance();
		assertEquals(range.distance(), distance);
	}

	@Test
	void testRelation() {
		
		double relation  = range.relation(250);

		assertEquals(0.5, relation);
		
		
	}

	@Test
	void testGetval() {
		double relation = range.relation(250);
		double val = range.getval(relation);
		assertEquals(250, val);
		
	}

	

	@Test
	void testIsIn() {
		double Falsenumber = 400;
		
		assertFalse(range.isIn(Falsenumber));
		
		double TrueNumber = 250;
		assertTrue(range.isIn(TrueNumber));
	}

}