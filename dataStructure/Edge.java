package dataStructure;

public class Edge implements edge_data {
	private int src, des, tag; 
	private double weight = 0;
	private String info = "";
	
	public Edge(int src_id, int des_id) {
		src = src_id;
		des = des_id;
	}
	
	public boolean equals(Object ob) {
		if(ob instanceof Edge) {
			Edge e = (Edge)ob;
			return (src==e.src)&&(des==e.des);
		}
		else
			return false;
	}

	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return src;
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return des;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}
	
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		weight = w;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		info = s;
		
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		tag = t;
		
	}
	
	public String toString() {
		return "("+src+","+des+")";
	}

}
