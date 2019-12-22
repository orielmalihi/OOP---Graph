package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.graph;
import dataStructure.node_data;


class Graph_AlgoTest {
	Vertex[] v = {
			new Vertex(1,2),// a = v[0]
			new Vertex(2,2),// b = v[1]
			new Vertex(1,3),// c = v[2]
			new Vertex(2,4),// d = v[3]
			new Vertex(3,5),// e = v[4]
			new Vertex(2,5),// f = v[5]
			new Vertex(4,2),// g = v[6]
			new Vertex(6,1),// h = v[7]
			new Vertex(3,4),// i = v[8]
			};
	Edge[] e = {
			new Edge(v[0].getKey(), v[1].getKey(), 1),
			new Edge(v[0].getKey(), v[5].getKey(), 2),
			new Edge(v[0].getKey(), v[8].getKey(), 3),
			new Edge(v[1].getKey(), v[2].getKey(), 9),
			new Edge(v[1].getKey(), v[5].getKey(), 2),
			new Edge(v[5].getKey(), v[2].getKey(), 3),
			new Edge(v[5].getKey(), v[6].getKey(), 1),
			new Edge(v[5].getKey(), v[8].getKey(), 5),
			new Edge(v[8].getKey(), v[7].getKey(), 7),
			new Edge(v[7].getKey(), v[6].getKey(), 4),
			new Edge(v[6].getKey(), v[4].getKey(), 20),
			new Edge(v[2].getKey(), v[3].getKey(), 4),
			new Edge(v[3].getKey(), v[4].getKey(), 5)};
	Graph_Algo ga = new Graph_Algo();
	DGraph g = new DGraph();

	@BeforeEach
	void setUp() throws Exception {
		for(int i=0; i<v.length; i++)
			g.addNode(v[i]);
		for(int i = 0; i<e.length; i++) {
			g.connect(e[i].getSrc(), e[i].getDest(), e[i].getWeight());
		}
		ga.init(g);
	}

	@Test
	void testInitString() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testIsConnected() {
		boolean ans = ga.isConnected();
		boolean actual = false;
		assertEquals(ans, actual);
	}

	@Test
	void testShortestPathDist() {
		double expected = ga.shortestPathDist(v[0].getKey(), v[4].getKey());
		double actual = 14;
		assertEquals(expected, actual);
	}

	@Test
	void testShortestPath() {
		ArrayList<node_data> expected = (ArrayList<node_data>) ga.shortestPath(v[0].getKey(), v[4].getKey());
		ArrayList<node_data> actual = new ArrayList<node_data>();
		actual.add(v[0]);
		actual.add(v[5]);
		actual.add(v[2]);
		actual.add(v[3]);
		actual.add(v[4]);
		assertEquals(expected, actual);
	}

	@Test
	void testTSP() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		graph expected = ga.copy();
		assertEquals(expected, ga);
		
	}

}
