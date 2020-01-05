package gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
/**
 * this class represents a GUI for the Graph_algo class.
 * it enables the user to build a new radom graph or to build a new custum graph
 * and to run on the graph the algorithms from the Graph_algo class.
 * @author oriel
 *
 */

public class Graph_GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	private graph g;
	private Graph_Algo algo;
	private int kRADIUS = 5;
	private boolean connected = false;
	private boolean is_connected_on = false;
	private boolean shortest_path_on = false;
	private boolean tsp = false;
	private boolean tsp_rec = false;
	private boolean custum_graph = false;
	private ArrayList<node_data> targets = new ArrayList<node_data>();

	public Graph_GUI() {
		initGUI();
	}

	public Graph_GUI(graph g) {
		initGUI();
		this.g = g;
		algo.init(this.g);
	}

	private void initGUI() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		Menu menu2 = new Menu("Algo");
		menuBar.add(menu);
		menuBar.add(menu2);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("Save Graph");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("Load Graph");
		item2.addActionListener(this);

		MenuItem item3 = new MenuItem("New Random DGraph");
		item3.addActionListener(this);

		MenuItem item4 = new MenuItem("Is Connected");
		item4.addActionListener(this);

		MenuItem item5 = new MenuItem("Shortest Path");
		item5.addActionListener(this);

		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);

		MenuItem item7 = new MenuItem("Nem Custum DGraph");
		item7.addActionListener(this);



		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item7);
		menu2.add(item4);
		menu2.add(item5);
		menu2.add(item6);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		g = graphFactory();
		algo = new Graph_Algo(g);

	}


	public void paint(Graphics k) {
		super.paint(k);
		Font font = k.getFont().deriveFont((float) 16.5);
		k.setFont(font);
		Collection<node_data> c1 = g.getV();
		Iterator<node_data> itrV = c1.iterator();
		while(itrV.hasNext()) {
			node_data n = itrV.next();
			Point3D p = n.getLocation();
			k.setColor(Color.BLUE);
			k.fillOval((int)p.x() - kRADIUS, (int)p.y() - kRADIUS, 2 * kRADIUS, 2 * kRADIUS);
			k.drawString(n.getKey()+"", (int)p.x() - kRADIUS, (int)p.y() - kRADIUS-2);
			Collection<edge_data> c2 = g.getE(n.getKey());
			Iterator<edge_data> itrE = c2.iterator();
			k.setColor(Color.RED);
			while(itrE.hasNext()) {
				edge_data e = itrE.next();
				Point3D ps = g.getNode(e.getSrc()).getLocation();
				Point3D pf = g.getNode(e.getDest()).getLocation();
				k.drawLine(ps.ix(), ps.iy(), pf.ix(), pf.iy());
				k.setColor(Color.YELLOW);
				int xdir = (int)(0.8*pf.x() + 0.2*ps.x());
				int ydir = (int)(0.8*pf.y() + 0.2*ps.y());
				k.fillOval(xdir - kRADIUS , ydir - kRADIUS , 2 * kRADIUS, 2 * kRADIUS);
				xdir = (int)(0.7*pf.x() + 0.3*ps.x());
				ydir = (int)(0.7*pf.y() + 0.3*ps.y()-4);
				k.setColor(Color.RED);
				k.drawString(String.format("%.2f", e.getWeight()), xdir, ydir);
			}
		}

		if(is_connected_on && connected) {
			k.setColor(Color.BLACK);
			k.drawString("This graph is connected!", 300, 80);
			is_connected_on = false;
		} else if(is_connected_on && !connected) {
			k.setColor(Color.BLACK);
			k.drawString("This graph is Not connected", 300, 80);
			is_connected_on = false;
		}

		if(shortest_path_on) {
			k.setColor(Color.BLACK);
			k.drawString("Please click on the Source node and then click on the Destination node:", 100, 80);
			k.drawString("The Shortest path between them will be marked with green,", 100, 100);
			if(targets.size()==2) {
				algo.init(g);
				ArrayList<node_data> ans = (ArrayList<node_data>) algo.shortestPath(targets.get(0).getKey(), targets.get(1).getKey());
				k.setColor(Color.GREEN);
				for(int i = 0; ans != null && i<ans.size()-1 ; i++) {
					node_data s = ans.get(i);
					node_data f = ans.get(i+1);
					Point3D ps = s.getLocation();
					Point3D pf = f.getLocation();
					k.drawLine(ps.ix()-kRADIUS, ps.iy()-kRADIUS, pf.ix(), pf.iy());
				}
				double sum = algo.shortestPathDist(targets.get(0).getKey(), targets.get(1).getKey());
				k.setColor(Color.BLACK);
				k.drawString("The length of the shortest path between "+targets.get(0).getKey()+" to "+targets.get(1).getKey()+" is: "+String.format("%.2f", sum), 100, 120);
				shortest_path_on = false;
			}
		}

		if(tsp) {
			k.setColor(Color.BLACK);
			k.drawString("Click on as many nodes as you want ,when you finish click on the blue button on the left.", 100, 80);
			k.drawString("A green path between every node you clicked will apper. (if it is possible).", 100, 100);
			if(!targets.isEmpty()) {
				k.drawString("So far you chose these nodes: "+targets, 100, 120);
			}
			k.setColor(Color.BLUE);
			k.drawRect(50, 130, 30, 30);
			if(tsp_rec) {
				k.setColor(Color.GREEN);
				algo.init(g);
				ArrayList<Integer> copy = new ArrayList<Integer>();
				for(int i =0 ; i<targets.size(); i++)
					copy.add(targets.get(i).getKey());
				ArrayList<node_data> ans = (ArrayList<node_data>) algo.TSP(copy);
				String path = "";
				for(int i = 0; ans != null && i<ans.size()-1 ; i++) {
					node_data s = ans.get(i);
					node_data f = ans.get(i+1);
					path += s.getKey()+">";
					Point3D ps = s.getLocation();
					Point3D pf = f.getLocation();
					k.drawLine(ps.ix()-kRADIUS, ps.iy()-kRADIUS, pf.ix(), pf.iy());
				}
				k.setColor(Color.BLACK);
				if(ans!=null) {
					path += ans.get(ans.size()-1).getKey();
					k.drawString("The path is: "+path, 100, 140);
				} else {
					k.drawString("There is no Path that goes through all the selected nodes. ", 100, 140);
				}

				tsp_rec = false;
				tsp = false;
			}	
		}

		if(custum_graph) {
			k.setColor(Color.BLACK);
			k.drawString("Make your owm Directed Graph! ", 100, 80);
			k.drawString("Click anywhere to deploy nodes, and click on 2 nodes to make an edge between them. ", 100, 100);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if (str.equals("Save Graph")) {
			FileDialog chooser = new FileDialog(this, "Save your Graph", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			String path = chooser.getDirectory();
			System.out.println(filename);
			if(filename!=null) {
				algo.init(g);
				algo.save(path + filename +".txt");
			}
		} else if (str.equals("Load Graph")) {
			FileDialog chooser = new FileDialog(this, "Load your Graph", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			String path = chooser.getDirectory();
			if(filename!=null) {
				algo.init(path + filename);
				g = algo.copy();
				repaint();
			}

		} else if(str.equals("New Random DGraph")) {
			clear();
			g = graphFactory();
			repaint();
		} else if(str.equals("Is Connected")) {
			clear();
			is_connected_on = true;
			algo.init(g);
			connected = algo.isConnected();
			repaint();
		} else if(str.equals("Shortest Path")) {
			clear();
			shortest_path_on = true;
			repaint();
		} else if(str.equals("TSP")) {
			clear();
			tsp = true;
			repaint();
		} else if(str.equals("Nem Custum DGraph")) {
			clear();
			g = new DGraph();
			custum_graph = true;
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(tsp && x>50 && x<80 && y>130 && y<160) {
			tsp_rec = true;
			repaint();
		}	
		node_data toChoose = null;
		Point3D temp = new Point3D(x,y);
		double min_dist = (kRADIUS * 3);
		double best_dist = 100000;
		Collection<node_data> c = g.getV();
		Iterator<node_data> itr = c.iterator();
		while(itr.hasNext()) {
			node_data n = itr.next();
			Point3D p = n.getLocation();
			double dist = temp.distance2D(p);
			if(dist<min_dist && dist<best_dist) {
				best_dist = dist;
				toChoose = n;
			}
		}
		if(custum_graph && toChoose==null) {
			g.addNode(new Vertex(x,y));
			targets.clear();
		}
		if(toChoose!=null && !targets.contains(toChoose))
			targets.add(toChoose);
		if(custum_graph && targets.size()==2) {
			node_data s = targets.get(0);
			node_data f = targets.get(1);
			double w;
			try {
				w = Double.parseDouble(JOptionPane.showInputDialog("Enter weight for the edge "+s.getKey()+">"+f.getKey()+":"));
			} catch (Exception e1) {
				w = Math.random()*50;
			}
			g.connect(s.getKey(), f.getKey(), w);
			targets.clear();
		}
		repaint();

		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {

	}

	public void clear() {
		connected = false;
		is_connected_on = false;
		shortest_path_on = false;
		tsp = false;
		tsp_rec = false;
		custum_graph = false;
		targets.clear();
	}

	public static graph graphFactory() {
		DGraph gr = new DGraph();
		Vertex[] v = new Vertex[12];
		for(int i =0; i<v.length; i++) {
			int rx = (int)(Math.random()*650+40);
			int ry = (int)(Math.random()*420+160);
			v[i] = new Vertex(rx, ry);
			gr.addNode(v[i]);
		}
		for(int i = 0; i<Math.pow(v.length, 1.5); i++) {
			int rs = (int)(Math.random()*v.length);
			int rf = (int)(Math.random()*v.length);
			double w = Math.random()*50;
			gr.connect(v[rs].getKey(), v[rf].getKey(), w);
		}

		return gr;
	}

	public graph getGraph() {
		return g;
	}

	public static void main(String[] args) {
		Graph_GUI graph_gui = new Graph_GUI();
		graph_gui.setVisible(true);
		graph g = graph_gui.getGraph();
		GraphChangeGUI_Thread changeListener = new GraphChangeGUI_Thread(g, graph_gui);
		changeListener.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=50; i<800; i=i+60) {
			node_data n1 = new Vertex(i,120);
			node_data n2 = new Vertex(i+30, 120);
			g.addNode(n1);
			g.addNode(n2);
			g.connect(n1.getKey(), n2.getKey(), 1);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			g.removeEdge(n1.getKey(), n2.getKey());
			g.removeNode(n1.getKey());
			g.removeNode(n2.getKey());
		}
	}
}