package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.Edge;

class EdgeTest {
	private Edge e1 = new Edge(3,7);
	private Edge e2 = new Edge(-10,10);




	@Test
	void testGetSrc() {
		int ans1 = e1.getSrc();
		int ans2 = e2.getSrc();
		assertEquals(3, ans1);
		assertEquals(-10, ans2);
	}

	@Test
	void testGetDest() {
		int ans1 = e1.getDest();
		int ans2 = e2.getDest();
		assertEquals(7, ans1);
		assertEquals(10, ans2);
	}

	@Test
	void testGetWeight() {
		e1.setWeight(50);
		double ans = e1.getWeight();
		assertEquals(50, ans);
	}

	@Test
	void testGetInfo() {
		String s = "new edge created";
		e1.setInfo(s);
		String ans = e1.getInfo();
		assertEquals(ans, s);
	}

	@Test
	void testGetTag() {
		e1.setTag(1);
		e2.setTag(2);
		int ans1 = e1.getTag();
		int ans2 = e2.getTag();
		assertEquals(1, ans1);
		assertEquals(2, ans2);
	}


	@Test
	void testToString() {
		String ans1 = e1.toString();
		String s1 = "(3,7)";
		String ans2 = e2.toString();
		String s2 = "(-10,10)";
		assertEquals(ans1, s1);
		assertEquals(ans2, s2);
		
	}

}
