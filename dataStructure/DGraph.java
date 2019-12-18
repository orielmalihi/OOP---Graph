package dataStructure;

import java.util.Collection;
import java.util.Hashtable;

public class DGraph implements graph{
	private Hashtable<Integer, Vertex> vBank = new Hashtable<Integer, Vertex>(1000000);
	private Hashtable<String, Edge> eBank = new Hashtable<String, Edge>(1000000);
	private int size;
	private static int MC = 0;
	
	public DGraph() {
		// TODO Auto-generated constructor stub
		size = 0;
	}
	
	

	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub
		node_data ans = vBank.get(key);
		return ans;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub
		Edge e = new Edge(src,dest);
		edge_data ans = eBank.get(e.toString());
		return ans;
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		if(n instanceof Vertex) {
			Vertex v = (Vertex)n;
			v.clearNeighbors();
			vBank.put(v.getKey(), v);
			MC++;
		}
		else {
			throw new RuntimeException("ERR: can not add "+n+" to the graph. it is not Vertex.");
		}
	} 
	
	/**
	 * connects src vertex with dest vertex.
	 * if they are already connected it will 'run over' the existing 
	 * edge and will actully update the weight filed
	 */

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		Edge e = new Edge(src,dest, w);
		eBank.put(e.toString(), e);
		vBank.get(src).addNeighbor(e);
		MC++;
		
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
