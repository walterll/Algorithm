package com.s2.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
	private WordNet wordNet;
	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordNet = wordnet;
	}
	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
//		int[] interDistance = new int[nouns.length * (nouns.length - 1) / 2];
		//用来存放每个点相互之间的距离，因为vw和wv距离实际是一样的，数组有一半是空的
		int[][] interDistance = new int[nouns.length][nouns.length];
		int[] distance = new int[nouns.length];
		int maxNoun = 0, maxDistance = 0;
		//外循环，遍历所有顶点
		for (int i = 0; i < nouns.length; i++) {
			//内循环，遍历除了i以外的点，又因为j<i时的点i和点j之间的距离等同于点j和点i之间的距离，即在之前已经计算过了，所以只计算j>i的点之间的距离
			for (int j = i + 1; j < nouns.length; j++) {
				interDistance[i][j] = wordNet.distance(nouns[i], nouns[j]);
			}
			//使用外循环计算每个点的distance
			for (int j = 0; j < nouns.length; j++) {
				if (i > j) 								//此处不能漏，因为当i>j时，数组[i][j]位置的值是零，需要使用[j][i]的值
					distance[i] += interDistance[j][i];
				else
					distance[i] += interDistance[i][j];
			}
			if (distance[i] > maxDistance) {
				maxDistance = distance[i];
				maxNoun = i;
			}
		}
		return nouns[maxNoun];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }

	}

}
