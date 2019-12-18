package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.Vertex;
import utils.Point3D;

class VertexTest {
	Vertex v1 = new Vertex(3, 4);
	Vertex v2 = new Vertex(1, -5, 20);
	Vertex v3 = new Vertex(new Point3D(7,8,9));


	@Test
	void testGetKey() {
		int key = v1.getKey();
		int ans2 = v2.getKey();
		int ans3 = v3.getKey();
		assertEquals(ans2, key+1);
		assertEquals(ans3, key+2);
	}

	@Test
	void testGetLocation() {
		Point3D p1 = v1.getLocation();
		Point3D  p2 = new Point3D(p1);
		assertEquals(p1, p2);
		Point3D p3 = v2.getLocation();
		Point3D  p4 = new Point3D(p3);
		assertEquals(p3, p4);
	}

	@Test
	void testSetLocation() {
		Point3D p1 = new Point3D(1, 2, 3);
		v1.setLocation(p1);
		Point3D p2 = v1.getLocation();
		assertEquals(p2, p1);
	}

	@Test
	void testGetWeight() {
		v2.setWeight(200);
		double w = v2.getWeight();
		assertEquals(200, w);
	}


	@Test
	void testGetInfo() {
		String s = "new edge created";
		v3.setInfo(s);
		String ans = v3.getInfo();
		assertEquals(ans, s);
	}

	

	@Test
	void testGetTag() {
		v1.setTag(1);
		v2.setTag(2);
		int ans1 = v1.getTag();
		int ans2 = v2.getTag();
		assertEquals(ans1, 1);
		assertEquals(ans2, 2);
		
	}


	@Test
	void testToString() {
		int ans1 = v1.getKey();
		int ans2 = ans1+1;
		int ans3 = ans1+2;
		assertEquals(ans1+"", v1.toString());
		assertEquals(ans2+"", v2.toString());
		assertEquals(ans3+"", v3.toString());
	}

}
