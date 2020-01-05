package gui;

import dataStructure.graph;

public class GraphChangeGUI_Thread extends Thread {
	private int myMC;
	private graph g;
	private Graph_GUI gui;

	public GraphChangeGUI_Thread(graph g, Graph_GUI gui) {
		this.g = g;
		this.gui = gui;
		myMC = g.getMC();
	}

	public synchronized void run() {
		while(true) {
			if(!gui.isVisible())
				break;
			if(!gui.getGraph().equals(g)) {
				g = gui.getGraph();
				myMC = g.getMC();
			}
			if(g.getMC()!=myMC) {
				myMC = g.getMC();
				gui.repaint();
			} else {
				try {
					synchronized (g) {
						g.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
