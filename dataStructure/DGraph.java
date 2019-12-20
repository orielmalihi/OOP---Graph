package dataStructure;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

public class DGraph implements graph{
	private Hashtable<Integer, node_data> vBank = new Hashtable<Integer, node_data>(1000000);
	private Hashtable<Integer,Hashtable<Integer,edge_data>> eBank = new Hashtable<Integer,Hashtable<Integer,edge_data>>(1000000);
	private int edge_size;
	private static int MC = 0;

	public DGraph() {
		// TODO Auto-generated constructor stub
		edge_size = 0;
	}



	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub
		return vBank.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return eBank.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		vBank.put(n.getKey(), n);
		eBank.put(n.getKey(), new Hashtable<Integer, edge_data>());
		MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		if(vBank.get(src)!=null && vBank.get(dest)!=null) {
			Edge e = new Edge(src,dest, w);
			eBank.get(src).put(dest, e);
			edge_size++;
			MC++;
		}

	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return vBank.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return eBank.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		Collection<node_data> c =vBank.values();
		Iterator<node_data> itr = c.iterator();
		while(itr.hasNext()) {
			node_data n = itr.next();
			if(eBank.get(n.getKey()).get(key)!=null) {
				eBank.get(n.getKey()).remove(key);
				edge_size--;	
			}
		}
		MC++;
		int minus = 0;
		if(eBank.get(key)!=null)
			 minus = eBank.get(key).size();
		eBank.remove(key);
		edge_size -= minus;
		return vBank.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		edge_size--;
		MC++;
		return eBank.get(src).remove(dest);
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return vBank.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return edge_size;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return MC;
	}

}
