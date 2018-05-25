package com.test;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

public class DijkstraTest {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public DijkstraTest(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		pq.insert(s, 0.0);
		while (!pq.isEmpty())
			relax(G, pq.delMin());
	}
	
	private void relax(EdgeWeightedDigraph G, int v) {
		for (DirectedEdge e : G.adj(v))
		{
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.change(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EdgeWeightedDigraph e = new EdgeWeightedDigraph(4);
		e.addEdge(new DirectedEdge(0, 1, 1.0));
		e.addEdge(new DirectedEdge(0, 2, 0.0));
		e.addEdge(new DirectedEdge(0, 3, 99.0));
		e.addEdge(new DirectedEdge(1, 2, 1.0));
		e.addEdge(new DirectedEdge(3, 1, -300.0));
		DijkstraTest test = new DijkstraTest(e, 0);
		System.out.println(test.distTo(2));
	}

}
