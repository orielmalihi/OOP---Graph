package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class Graph_GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	private graph g;
	private int kRADIUS = 5;
//	LinkedList<Point3D> mPoints = new LinkedList<Point3D>();
//	Point3D mPivot_point = null;
//	boolean mDraw_pivot = false;
//	boolean mMoving_point = false;
	

	public Graph_GUI() {
		initGUI();
	}
	
//	public Graph_GUI(graph gr) {
//		initGUI();
//		g = gr;
//	}

	private void initGUI() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		Menu menu2 = new Menu("Algo");
		menuBar.add(menu);
		menuBar.add(menu2);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("simpleTriangle");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("clean-up");
		item2.addActionListener(this);

		menu.add(item1);
		menu.add(item2);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		g = graphFactory();
		
	}


	public void paint(Graphics k) {
		super.paint(k);
		
		Collection<node_data> c1 = g.getV();
		Iterator<node_data> itrV = c1.iterator();
		while(itrV.hasNext()) {
			node_data n = itrV.next();
			Point3D p = n.getLocation();
			k.setColor(Color.blue);
			k.fillOval((int)p.x() - kRADIUS, (int)p.y() - kRADIUS, 2 * kRADIUS, 2 * kRADIUS);
			Collection<edge_data> c2 = g.getE(n.getKey());
			Iterator<edge_data> itrE = c2.iterator();
			k.setColor(Color.RED);
			while(itrE.hasNext()) {
				edge_data e = itrE.next();
				Point3D ps = g.getNode(e.getSrc()).getLocation();
				Point3D pf = g.getNode(e.getDest()).getLocation();
				k.drawLine(ps.ix(), ps.iy(), pf.ix(), pf.iy());
			}
		}
	
//		for (Point3D p : mPoints) {
//			g.setColor(Color.BLUE);
//			g.fillOval((int) p.x() - kRADIUS, (int) p.y() - kRADIUS,
//					2 * kRADIUS, 2 * kRADIUS);
//
//			if (prev != null) {
//				g.setColor(Color.RED);
//				g.drawLine((int) p.x(), (int) p.y(),
//						(int) prev.x(), (int) prev.y());
//
//				double dist = prev.distance3D(p);
//				g.drawString(String.format("%.2f", dist),
//						(int) ((p.x() + prev.x()) / 2),
//						(int) ((p.y() + prev.y()) / 2));
//			}
//
//			prev = p;
//		}
//
//		if (mDraw_pivot
//				&& !mMoving_point) {
//			g.setColor(Color.BLUE);
//			g.fillOval((int) mPivot_point.x() - kRADIUS, (int) mPivot_point.y() - kRADIUS,
//					2 * kRADIUS, 2 * kRADIUS);
//			if (prev != null) {
//				g.setColor(Color.RED);
//				g.drawLine((int) mPivot_point.x(), (int) mPivot_point.y(),
//						(int) prev.x(), (int) prev.y());
//
//				double dist = prev.distance3D(mPivot_point);
//				g.drawString(String.format("%.2f", dist), (int) ((mPivot_point.x() + prev.x()) / 2), (int) ((mPivot_point.y() + prev.y()) / 2));
//			}
//
//		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if (str.equals("simpleTriangle")) {
			
			repaint();
//			Point3D p1 = new Point3D(100, 100);
//			Point3D p2 = new Point3D(50, 300);
//			Point3D p3 = new Point3D(400, 150);

//			mPoints.add(p1);
//			mPoints.add(p2);
//			mPoints.add(p3);
//			mPoints.add(p1);

//			repaint();
		} else if (str.equals("clean-up")) {
//			mPoints.removeAll(mPoints);
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
//		int x = e.getX();
//		int y = e.getY();
//		Point3D tmp = new Point3D(x, y);
//		int min_dist = (int) (kRADIUS * 1.5);
//		double best_dist = 1000000;
//		for (Point3D p : mPoints) {
//			double dist = tmp.distance3D(p);
//			if (dist < min_dist
//					&& dist < best_dist) {
//				mPivot_point = p;
//				best_dist = dist;
//				mMoving_point = true;
//			}
//		}
//
//		if (mPivot_point == null) {
//			mPivot_point = new Point3D(x, y);
//		}
//		mDraw_pivot = true;
//		repaint();
		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
//		if (!mMoving_point) {
//			mPoints.add(new Point3D(mPivot_point));
//		}
//		mMoving_point = false;
//		mPivot_point = null;
//		mDraw_pivot = false;
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
		
		
//		int x = mouseEvent.getX();
//		int y = mouseEvent.getY();
//		if (mDraw_pivot) {
//			mPivot_point.setX(x);
//			mPivot_point.setY(y);
//
//			repaint();
//		}
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {

	}
	
	public static graph graphFactory() {
		DGraph gr = new DGraph();
		Vertex[] v = new Vertex[10];
		for(int i =0; i<v.length; i++) {
			int rx = (int)(Math.random()*350+40);
			int ry = (int)(Math.random()*350+80);
			v[i] = new Vertex(rx, ry);
			gr.addNode(v[i]);
		}
		for(int i = 0; i<Math.pow(v.length, 2); i++) {
			int rs = (int)(Math.random()*v.length);
			int rf = (int)(Math.random()*v.length);
			int w = (int)(Math.random()*50);
			gr.connect(v[rs].getKey(), v[rf].getKey(), w);
		}
		
		return gr;
	}

	public static void main(String[] args) {
		Graph_GUI graph_gui = new Graph_GUI();
		graph_gui.setVisible(true);
	}
}