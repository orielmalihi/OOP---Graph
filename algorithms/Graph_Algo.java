package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private DGraph g;

	public Graph_Algo() {
		g = new DGraph();
	}

	@Override
	public void init(graph g) {
		if(g instanceof DGraph) {
			DGraph newG = (DGraph)g;
			this.g = (DGraph) newG.copy();
		}
		else
			throw new RuntimeException("ERR: could not init Graph_Algo from: "+g);

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		Collection c1 = g.getV();
		Iterator<node_data> itr1 = c1.iterator();
		while(itr1.hasNext()) {
			node_data n1 = itr1.next();
			Collection<node_data> c2 = g.getV();
			Iterator<node_data> itr2 = c2.iterator();
			while(itr2.hasNext()) {
				node_data n2 = itr2.next();
				double d = shortestPathDist(n1.getKey(), n2.getKey());
				if(d<0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		ArrayList<node_data> a = (ArrayList<node_data>) shortestPath(src, dest);
		if(a==null) {
			return -1;
		}
		double sum = 0;
		for(int i = 0; i<a.size()-1; i++) {
			edge_data e = g.getEdge(a.get(i).getKey(), a.get(i+1).getKey());
			sum += e.getWeight();
		}
		return sum;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		try {
			ArrayList<node_data> ans = new ArrayList<node_data>();
			if(g.getNode(src).equals(g.getNode(dest))) {
				ans.add(g.getNode(src));
				return ans;
			}
			PriorityQueue <node_data> notVisited = new PriorityQueue <node_data> (g.nodeSize(),new Node_Comparator());
			Collection<node_data> c = g.getV();
			Iterator<node_data> itr = c.iterator();
			while(itr.hasNext()) {
				node_data n = itr.next();
				if(n.getKey()==src) {
					n.setWeight(0);

				} else {
					n.setWeight(Integer.MAX_VALUE);

				}
				n.setInfo("");
				n.setTag(0);
				notVisited.add(n);
			}
			while(!notVisited.isEmpty()) {
				//			System.out.println("src:"+src+", dest: "+dest+" pq: "+notVisited);
				node_data n = notVisited.poll();
				if(n.getKey()==dest && !n.getInfo().equals("")) {
					ans.add(n);
					while(!n.getInfo().equals("")) {
						node_data newNode = g.getNode(Integer.parseInt(n.getInfo()));
						ans.add(newNode);
						n = newNode;
					}
					ans.sort(new Node_Comparator());
					return ans;	
				}
				Collection<edge_data> outOfn = g.getE(n.getKey());
				Iterator<edge_data> itr2 = outOfn.iterator();
				while(itr2.hasNext()) {
					edge_data edge = itr2.next();
					node_data d = g.getNode(edge.getDest());
					if(d.getTag()==0) {
						if(d.getWeight()>(n.getWeight() + edge.getWeight())) {
							d.setWeight(n.getWeight() + edge.getWeight());
							d.setInfo(""+n.getKey());
							notVisited.remove(d);
							notVisited.add(d);
						} 
					}	
				}
				n.setTag(1);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		ArrayList<node_data> left = new ArrayList<node_data>();
		ArrayList<node_data> ans = new ArrayList<node_data>();
		Iterator<Integer> itr = targets.iterator();
		while(itr.hasNext()) {
			int id = itr.next();
			if(g.getNode(id)==null) {
				return null;
			}
			left.add(g.getNode(id));
		}

		for(int i = 0; i<left.size()-1; i++) {
			ArrayList<node_data> temp = (ArrayList<node_data>) shortestPath(left.get(i).getKey(), left.get(i+1).getKey());
			if(temp==null) {
				return null;
			}
			for(int j =0; j<temp.size(); j++) {
				if(!ans.contains(temp.get(j)))
					ans.add(temp.get(j));
			}
		}
		return ans;	
	}


	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return g.copy();
	}

	public boolean equals(graph g) {
		return this.g.equals(g);
	}

	public String toString() {
		return g.toString();
	}
}




