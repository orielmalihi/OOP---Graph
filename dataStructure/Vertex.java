package dataStructure;


import java.io.Serializable;

import utils.Point3D;

public class Vertex implements node_data, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6385196210904772317L;
	private static int key = 0;
	private int id = key++, tag = 0;
	private double weight = 0;
	private String info = "";
	private Point3D location;
	
	
	
	public Vertex(double x, double y) {
		// TODO Auto-generated constructor stub
		location = new Point3D(x,y);
	}
	
	public Vertex(double x, double y, double z) {
		// TODO Auto-generated constructor stub
		location = new Point3D(x,y,z);
	}
	
	public Vertex(Point3D p) {
		// TODO Auto-generated constructor stub
		location = new Point3D(p);
	}
	
	public boolean equals(Object ob) {
		if(ob instanceof Vertex) {
			Vertex v = (Vertex)ob;
			return v.getKey()==id;
		}
		else
			return false;
	}
	
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return new Point3D(location);
	}

	@Override
	public void setLocation(Point3D p) {
		// TODO Auto-generated method stub
		location = new Point3D(p);
		
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
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
		return ""+id;
	}

}
