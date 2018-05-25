package com.week4;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private int[][] blocks;
	// construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		this.blocks = new int[blocks.length][blocks.length];
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				this.blocks[i][j] = blocks[i][j];
			}
		}
//		this.blocks = blocks;
	}
	// board dimension n
	public int dimension() {
		return blocks.length;
	}
	// number of blocks out of place
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] != 0) {
					if (blocks[i][j] != (i * dimension() + j + 1)) 
						hamming ++;
				}
			}
		}
		return hamming;
	}
	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int manhattan = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
//				if (blocks[i][j] != 0) {							//此处判断有问题
//					int originX = blocks[i][j] / dimension();
//					int originY = blocks[i][j] % dimension() - 1;
//					if (originY < 0)
//						originY = dimension();
				if (blocks[i][j] != 0) {							
					int originX = (blocks[i][j] - 1) / dimension(); //这里不要漏-1
					int originY = blocks[i][j] % dimension() - 1;
					if (originY < 0)
						originY = dimension() - 1;					//这里也不要漏-1
					manhattan += Math.abs(originX - i) + Math.abs(originY - j);
				}
			}
		}
		return manhattan;
	}
	// is this board the goal board?
	public boolean isGoal() {
		return (hamming() == 0);
	}
	// a board that is obtained by exchanging any pair of blocks
	public Board twin() {
		int[][] twinBlocks = new int[dimension()][dimension()];
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				twinBlocks[i][j] = blocks[i][j];
			}
		}
		if (dimension() >= 1) {
//			int temp = twinBlocks[0][0];			//此处有bug,不能简单地随便交换两个单元，因为如果其中有一个是0,则不是交换而成了移动
//			twinBlocks[0][0] = twinBlocks[0][1];		
//			twinBlocks[0][1] = temp;
			int temp;
			if (twinBlocks[0][0] == 0) {
				temp = twinBlocks[0][1];
				twinBlocks[0][1] = twinBlocks[1][0];
				twinBlocks[1][0] = temp;
			} else	if (twinBlocks[0][1] == 0) {
				temp = twinBlocks[0][0];
				twinBlocks[0][0] = twinBlocks[1][0];
				twinBlocks[1][0] = temp;
			} else {
				temp = twinBlocks[0][0];
				twinBlocks[0][0] = twinBlocks[0][1];
				twinBlocks[0][1] = temp;
			}
		}
		return new Board(twinBlocks);
	}
	// does this board equal y?
	public boolean equals(Object y) {
		if (!(y instanceof Board))
			return false;
		Board boardY = (Board)y;
		if (boardY.dimension() != dimension())
			return false;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] != boardY.blocks[i][j])
					return false;
			}
		}
		return true;
	}
	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbors = new ArrayList<>();
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] == 0) {
//					int[][] newBlocks = new int[dimension()][dimension()];
//					for (int x = 0; x < dimension(); x++) {
//						for (int y = 0; y < dimension(); y++) {
//							newBlocks[x][y] = blocks[x][y];
//						}
//					}
					if (i > 0) {
						blocks[i][j] = blocks[i - 1][j];
						blocks[i - 1][j] = 0;
						neighbors.add(new Board(blocks));
						blocks[i - 1][j] = blocks[i][j];
						blocks[i][j] = 0;
					}
					if (j > 0) {
						blocks[i][j] = blocks[i][j - 1];
						blocks[i][j - 1] = 0;
						neighbors.add(new Board(blocks));
						blocks[i][j - 1] = blocks[i][j];
						blocks[i][j] = 0;
					}
					if (i < dimension() - 1) {
						blocks[i][j] = blocks[i + 1][j];
						blocks[i + 1][j] = 0;
						neighbors.add(new Board(blocks));
						blocks[i + 1][j] = blocks[i][j];
						blocks[i][j] = 0;
					}
					if (j < dimension() - 1) {
						blocks[i][j] = blocks[i][j + 1];
						blocks[i][j + 1] = 0;
						neighbors.add(new Board(blocks));
						blocks[i][j + 1] = blocks[i][j];
						blocks[i][j] = 0;
					}
				}
			}
		}
		return neighbors;
	}
	// string representation of this board (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();	//2018.2.5改为StringBuilder
		s.append(dimension() + "\n");
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				s.append(" " + blocks[i][j] + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
	// unit tests (not graded)
	public static void main(String[] args) {
//		int[][] blocks ={{1,2,3},{4,5,6},{7,0,8}};
		int[][] blocks = {{0,1,3},{4,2,5},{7,8,6}};
		Board board = new Board(blocks);
//		System.out.println(board);
//		for(Board b : board.neighbors()) {
//			System.out.println(b);
//		}
		System.out.println(board);
	}

}
