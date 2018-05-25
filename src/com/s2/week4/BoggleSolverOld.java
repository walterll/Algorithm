package com.s2.week4;


import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolverOld {
	private TrieST tree;
	private boolean[] marked;
	private int[] edgeTo;
	private Set<String> words;
	public class TrieST {
		private  int R = 26;
		private Node root;
		public class Node {
			int val;
			private Node[] next = new Node[R];
		}
		//重写的get方法，便于dfs搜索
		private int get(char[] key) {
			Node x = get(root, key[0]);
			if (x == null)
				return 0;
			for (int i = 1; i < key.length; i++) {
				x = get(x, key[i]);
				if (x == null)
					return 0;
			}
			if (x.val == 0)
				return 0;
			return 1;
		}
		private Node get(Node x, char c) {
			if (x == null)
				return null;
			return x.next[c - 65];
		}
		private int get(String key) {
			Node node = get(root, key, 0);
			if (node == null)
				return 0;
			return node.val == 0 ? 0 : 1;
//			return 1;
		}
		private Node get(Node x, String key, int d) {
			if (x == null)
				return null;
			if (key.length() <= d)
				return x;
			return get(x.next[key.charAt(d) - 65], key, d + 1);
		}
		private void put(String key) {
			root = put(root, key, 0);
		}
		//自己写的put，和原版对比一下
		private Node put(Node x, String key, int d) {
			if (x == null)
				x = new Node();
			if (key.length() <= d) {
				x.val = 1;
				return x;
			}
			x.next[key.charAt(d) - 65] = put(x.next[key.charAt(d) - 65], key, d + 1);
			return x;
		}
	}
	private void dfs(BoggleBoard board, int col, int row, TrieST.Node x) {
//		if (marked[row * board.cols() + col])
//			return;
		char letter = board.getLetter(row, col);
		if (letter == 'Q') {
			x = tree.get(x, 'Q');
			if (x == null)
				return;
			x = tree.get(x,  'U');
		} else 
			x = tree.get(x, letter);
		if (x == null)
			return;
		if (x.val == 1) {
			StringBuilder sb = new StringBuilder();
			sb.append(board.getLetter(row, col));
			int index = edgeTo[row * board.cols() + col];
			while (index != -1) {
				char c = board.getLetter(index / board.cols(), index % board.cols());
				if (c == 'Q')
					sb.append("UQ");
				else
					sb.append(c);
				index = edgeTo[index];
			}
			String word = sb.reverse().toString();
			words.add(word);
		}
//			words.add(e)
		marked[row * board.cols() + col] = true;
		if (row > 0) {
			if (col > 0) {
				if ((!marked[(row - 1) * board.cols() + col - 1]) && (edgeTo[(row - 1) * board.cols() + col - 1] != row * board.cols() + col)) {
					edgeTo[(row - 1) * board.cols() + col - 1] = row * board.cols() + col;
					dfs(board, col - 1, row - 1, x);
				}
			}
			if ((!marked[(row - 1) * board.cols() + col]) && (edgeTo[(row - 1) * board.cols() + col] != row * board.cols() + col)) {
				edgeTo[(row - 1) * board.cols() + col] = row * board.cols() + col;
				dfs(board, col, row - 1, x);
			}
			if (col < board.cols() - 1) {
				if ((!marked[(row - 1) * board.cols() + col + 1]) && (edgeTo[(row - 1) * board.cols() + col + 1] != row * board.cols() + col)) {
					edgeTo[(row - 1) * board.cols() + col + 1] = row * board.cols() + col;
					dfs(board, col + 1, row - 1, x);
				}
			}
		}
		if (col > 0) {
			if ((!marked[row * board.cols() + col - 1]) && (edgeTo[row * board.cols() + col - 1] != row * board.cols() + col)) {
				edgeTo[row * board.cols() + col - 1] = row * board.cols() + col;
				dfs(board, col - 1, row, x);
			}
		}
		if (col < board.cols() - 1) {
			if ((!marked[row * board.cols() + col + 1]) && (edgeTo[row * board.cols() + col + 1] != row * board.cols() + col)) {
				edgeTo[row * board.cols() + col + 1] = row * board.cols() + col;
				dfs(board, col + 1, row, x);
			}
		}
		if (row < board.rows() - 1) {
			if (col > 0) {
				if ((!marked[(row + 1) * board.cols() + col - 1]) && ((edgeTo[(row + 1) * board.cols() + col - 1]) != (row * board.cols() + col))){
					edgeTo[(row + 1) * board.cols() + col - 1] = row * board.cols() + col;
					dfs(board, col - 1, row + 1, x);
				}
			}
			if ((!marked[(row + 1) * board.cols() + col]) && (edgeTo[(row + 1) * board.cols() + col] != row * board.cols() + col)){
				edgeTo[(row + 1) * board.cols() + col] = row * board.cols() + col;
				dfs(board, col, row + 1, x);
			}
			if (col < board.cols() - 1) {
				if ((!marked[(row + 1) * board.cols() + col + 1]) && (edgeTo[(row + 1) * board.cols() + col + 1] != row * board.cols() + col)) {
					edgeTo[(row + 1) * board.cols() + col + 1] = row * board.cols() + col;
					dfs(board, col + 1, row + 1, x);
				}
			}
		}
//		StringBuilder sb = new StringBuilder();
//		sb.append(board.getLetter(row, col));
//		int index = edgeTo[row * board.cols() + col];
//		while (index != -1) {
//			char c = board.getLetter(index / board.cols(), index % board.cols());
//			sb.append(c);
//			index = edgeTo[index];
//		}
//		System.out.println(sb);
		marked[row * board.cols() + col] = false;
	}
	// Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolverOld(String[] dictionary) {
    	tree = new TrieST();
    	for (int i = 0; i < dictionary.length; i++)
    		tree.put(dictionary[i]);
//    	char[] c1 = "AVERAGE".toCharArray();
//    	char[] c2 = "AV".toCharArray();
//    	char[] c3 = "METAPHOR".toCharArray();
//    	System.out.println(tree.get("AVERAGE"));
//    	System.out.println(tree.get("AV"));
//    	System.out.println(tree.get("METAPHOR"));
//    	System.out.println(tree.get(c1));
//    	System.out.println(tree.get(c2));
//    	System.out.println(tree.get(c3));
    	words = new HashSet<>();
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
//    	board = new BoggleBoard();
    	int vertex = board.cols() * board.rows();
    	for (int v = 0; v < vertex; v++) {
	    	marked = new boolean[vertex];
	    	edgeTo = new int[vertex];
	    	for (int i = 0; i < vertex; i++)
	    		edgeTo[i] = -1;
	    	dfs(board, v % board.cols(), v / board.rows(), tree.root);
    	}
    	return words;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
//    	if (tree.get(word) == 0)
//    		return 0;
    	if (!words.contains(word))
    		return 0;
    	int length = word.length();
    	if (length <= 2)
    		return 0;
    	if (length <= 4)
    		return 1;
    	if (length <= 5)
    		return 2;
    	if (length <= 6)
    		return 3;
    	if (length <= 7)
    		return 5;
    	return 11;
    }
    public static void main(String[] args) {
//    	In in = new In("dictionary-algs4.txt");
//        String[] dictionary = in.readAllStrings();
//		BoggleSolver solver = new BoggleSolver(dictionary);
//		System.out.println(solver.words);
    	In in = new In("dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolverOld solver = new BoggleSolverOld(dictionary);
        BoggleBoard board = new BoggleBoard("board-q.txt");
        System.out.println(board);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
	}
}
