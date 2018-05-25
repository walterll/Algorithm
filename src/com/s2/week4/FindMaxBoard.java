package com.s2.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class FindMaxBoard {

	public static void main(String[] args) {
		In in = new In("dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
//        BoggleBoard board = new BoggleBoard("board4x4.txt");
        BoggleBoard board;
        BoggleBoard maxBoard = null;
        int maxScore = 0;
        int[] scoreAccount = new int[5000];
        for (int i = 0; i < 1000000; i++) {
        	board = new BoggleBoard(4,4);
        	int score = 0;
	        for (String word : solver.getAllValidWords(board)) {
	            score += solver.scoreOf(word);
	        }
	        scoreAccount[score]++;
//	        StdOut.println("Score = " + score);
	        if (score > maxScore) {
	        	maxScore = score;
	        	maxBoard = board;
	        }
	        if ((i % 100000) == 0) {
	        	System.out.println("i:" + i);
	        	System.out.println(maxBoard);
	        	System.out.println(maxScore);
	        }
        }
        System.out.println(maxBoard);
        System.out.println("maxScore:" + maxScore);
        int maxAccount = 0;
        int maxNumber = 0;
        for (int i = 0; i < 200; i++) {
        	if (maxAccount < scoreAccount[i]) {
        		maxAccount = scoreAccount[i];
        		maxNumber = i;
        	}
        }
        System.out.println("maxNumber:" + maxNumber);
        drawScoreData(scoreAccount, maxScore, maxAccount);
//        char[][] c = new char[][]{{'G','N','E','S'},
//        						  {'S','R','I','P'},
//        						  {'E','T','A','L'},
//        						  {'T','S','E','B'}};
//        board = new BoggleBoard(c);
//        int score = 0;
//        for (String word : solver.getAllValidWords(board)) {
//            score += solver.scoreOf(word);
//        }
//        System.out.println(score);
	}
	private static void drawScoreData(int[] scores, int maxScore, int maxAccount) {
		StdDraw.setCanvasSize(1000, 600);
		StdDraw.setXscale(0, maxScore);
		StdDraw.setYscale(0, maxAccount * 1.1);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		for (int i = 1; i <= 8; i++) {
			StdDraw.text(50, maxAccount / 8 * i, Integer.toString(maxAccount / 8 * i));
		}
		for (int i = 0; i < maxScore; i++) {
			StdDraw.point(i, scores[i]);
			if ((i % 100) == 0)
				StdDraw.text(i, scores[i] + 30, Integer.toString(i));
			
		}
	}
}
