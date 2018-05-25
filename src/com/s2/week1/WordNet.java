package com.s2.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {
	private Digraph wordNet;
	private String[] words;					//String数组存放id-noun的映射对，不过要求中似乎没有这个需求，若需要优化，可以省去这个
//	private Map<String, Integer> id;
	private Map<String, Bag<Integer>> id;	//Map存放noun-id的映射对，同一个noun可能拥有多个不同id，因此使用Bag类型存放所有id
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		id = new HashMap<>();
		words = new String[4];
		int vertex = 0;
		In in = new In(synsets);
		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			if (Integer.valueOf(tokens[0]) > (words.length - 1))
				addWords();
			words[Integer.valueOf(tokens[0])] = tokens[1];
			String[] nouns = tokens[1].split(" ");
			for (int i = 0; i < nouns.length; i++) {
//				id.put(nouns[i], Integer.valueOf(tokens[0]));	//这样存储会有个bug，因为一个noun可能会有不同id，使用hashmap的话
				                                                //当一个noun已经存储在hashmap中的话后一个会将前一个覆盖
				                                                //这样计算得出的distance可能不是最短距离
				//若此noun已经存在，则将新的id添加到bag中，否则创建一个bag存放id
				if (id.containsKey(nouns[i]))
					id.get(nouns[i]).add(Integer.valueOf(tokens[0]));
				else {
					Bag<Integer> bag = new Bag<>();
					bag.add(Integer.valueOf(tokens[0]));
					id.put(nouns[i], bag);
				}
			}
			//计算图的总顶点数
			vertex++;
		}
		in = new In(hypernyms);
		//根据hypernyms创建有向图
		wordNet = new Digraph(vertex);
		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			for (int i = 1; i < tokens.length; i++) {
				wordNet.addEdge(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[i]));
			}
		}
//		System.out.println(wordNet.V());
//		System.out.println(wordNet.E());
	}
	//自动扩大数组
	private void addWords() {
		String[] tempWords = new String[words.length * 2];
		for (int i = 0; i < words.length; i++) {
			tempWords[i] = words[i];
		}
		words = tempWords;
		
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
	   List<String> nouns = new ArrayList<>();
	   for (String noun : id.keySet()) {
		   nouns.add(noun);
	   }
	   return nouns;
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
	   return id.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		   SAP sap = new SAP(wordNet);
//		   System.out.println(nounA + " id: " + id.get(nounA) + " " + nounB + " id: " + id.get(nounB));
		   return sap.length(id.get(nounA), id.get(nounB));
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		SAP sap = new SAP(wordNet);
		   return words[sap.ancestor(id.get(nounA), id.get(nounB))];
	}

	// do unit testing of this class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordNet wordNet = new WordNet("synsets.txt","hypernyms.txt");
		System.out.println(wordNet.distance("white_marlin", "mileage"));
//		System.out.println(wordNet.distance("horse", "cat"));
//		System.out.println(wordNet.distance("horse", "bear"));
//		System.out.println(wordNet.distance("horse", "table"));

	}

}
