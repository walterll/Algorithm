package com.s2.week1;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	private Digraph digraph;	//有向图变量
	   // constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		digraph = G;
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		//新建两个广度搜索变量，分别计算v点和w点到有向图所有顶点的最短距离，用于找出最短公共距离
		BreadthFirstDirectedPaths bfpV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfpW = new BreadthFirstDirectedPaths(digraph, w);
		int distance = -1;
		//遍历有向图的所有顶点
		for (int i = 0; i < digraph.V(); i++) {
			//i点是v和w的公共顶点
			if (bfpV.hasPathTo(i) && bfpW.hasPathTo(i)) {
				//计算i点到v和w的距离和tempDistance
				int tempDistance = bfpV.distTo(i) + bfpW.distTo(i);
				//若i点是首个遍历到的公共顶点，或i点是至今为止到v和w距离和最短的点
				if (distance == -1 || (tempDistance < distance)) 
					distance = tempDistance;
//				System.out.println("ancestor:" + i);
			}
		}
		return distance;
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	//此方法同上面length方法几乎完全一样，只是返回的是找到的公共顶点，如果需要优化可以考虑修改此处
	public int ancestor(int v, int w) {
		BreadthFirstDirectedPaths bfpV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfpW = new BreadthFirstDirectedPaths(digraph, w);
		int distance = -1;
		int ancestor = 0;
		for (int i = 0; i < digraph.V(); i++) {
			if (bfpV.hasPathTo(i) && bfpW.hasPathTo(i)) {
				int tempDistance = bfpV.distTo(i) + bfpW.distTo(i);
				if (distance == -1 || (tempDistance < distance)) {
					distance = tempDistance;
					ancestor = i;
//					System.out.println("ancestor:" + i);
				}
			}
		}
		if (distance == -1)
			return -1;
		return ancestor;
	}

	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	//此方法参数为可迭代的集合，因为一个单词可能有不同id，所以需要找出这些id中距离最短的id
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		int length = -1;
		//用两个循环，找出集合v和w中最小的距离
		for (Integer v1 : v) {
			for (Integer w1 : w) {
				int tempLength = length(v1, w1);
				if (length == -1 || tempLength < length)
					length = tempLength;
			}
		}
		   return length;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	//同上
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		int length = -1;
		int ancestor = -1;
		Integer V = 0,W = 0;
		for (Integer v1 : v) {
			for (Integer w1 : w) {
				int tempLength = length(v1, w1);
				if (length == -1 || tempLength < length) {
					length = tempLength;
					V = v1;
					W = w1;
				}
			}
		}
		if (length != -1)
			ancestor = ancestor(V, W);
		   return ancestor;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("digraph1.txt");
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	}

}
