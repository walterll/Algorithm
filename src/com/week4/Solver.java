package com.week4;

import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	private MinPQ<Node> nodes;
	private int moves;
	private Node minNode;
	private boolean solvable;
	private class Node implements Comparable<Node>, Iterable {
		private Board board;
		private int move;
		private Node parent;
		Node(Board b, int moves, Node parent) {
			board = b;
			move = moves;
			this.parent = parent;
		}
		@Override
		public int compareTo(Node that) {
			// TODO Auto-generated method stub
			if ((this.board.manhattan() + this.move) <
					(that.board.manhattan() + that.move)) 
				return -1;
			else if ((this.board.manhattan() + this.move) >
					(that.board.manhattan() + that.move))
				return 1;
			else return 0;
		}
		private class NodeIterator implements Iterator {
			private Node node = minNode;		//此处必须附上初值，否则迭代器无效，之前就碰到了这个bug
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return node != null;
			}

			@Override
			public Object next() {
				// TODO Auto-generated method stub
				Board temp = node.board;
				node = node.parent;
				return temp;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		}
		@Override
		public Iterator<Board> iterator() {
			// TODO Auto-generated method stub
			return new NodeIterator();
		}
		
	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		Node node = new Node(initial, 0, null);
		nodes = new MinPQ<>();
		nodes.insert(node);
		
		Node twinNode = new Node(initial.twin(),0,null);
		MinPQ<Node> twinNodes = new MinPQ<>();
		twinNodes.insert(twinNode);
		Node minTwinNode;
//		List<Board> minBoards = new ArrayList<>();
		while (true) {
			minNode = nodes.delMin();
			minTwinNode = twinNodes.delMin();
//			minBoards.add(minNode.board);
//			System.out.println("minNode:\n" + minNode.board);
			if (minNode.board.isGoal()) {
				moves = minNode.move;
				solvable = true;
				break;
			}
			if (minTwinNode.board.isGoal()) {
				solvable = false;
				moves = -1;
				break;
			}
			for (Board b : minNode.board.neighbors()) {
//				if (minBoards.contains(b))	//这里不应该这样判断
				if (minNode.parent != null) 
					if (minNode.parent.board.equals(b))
						continue;
//						break;//这里写错了，这样的话第一个neighbor是重复的话就直接跳出循环了
//				for (Object o : minNode) {
//					if (b.equals(o)) 
//						continue outer;
//				}
//				System.out.println("neighborBoard:\n"+ b);
				Node neighborNode = new Node(b, minNode.move + 1, minNode);
				nodes.insert(neighborNode);
			}
			for (Board b : minTwinNode.board.neighbors()) {
//				if (minBoards.contains(b))	//这里不应该这样判断
				if (minTwinNode.parent != null) 
					if (minTwinNode.parent.board.equals(b))
						continue;
//						break;//这里写错了，这样的话第一个neighbor是重复的话就直接跳出循环了
//				for (Object o : minNode) {
//					if (b.equals(o)) 
//						continue outer;
//				}
//				System.out.println("neighborBoard:\n"+ b);
				Node neighborTwinNode = new Node(b, minTwinNode.move + 1, minTwinNode);
				twinNodes.insert(neighborTwinNode);
			}
		}
	}
	// is the initial board solvable?
    public boolean isSolvable() {
    	return solvable;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	return moves;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
//    	List<Board> list = new ArrayList<>();
//    	for (Object o : minNode) {
//    		list.add((Board)o);
//    	}
//    	return list;
    	Stack<Board> stack = new Stack<>();
    	for (Object o : minNode) {
    		stack.push((Board)o);
    	}
    	return stack;
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
//    	int[][] blocks ={{4,1,11,9},{7,0,13,2},{15,3,8,10},{14,6,12,5}};
    	int[][] blocks ={{1,2,3},{4,6,5},{8,7,0}};
//    	int[][] blocks ={{9,1,5,7},{0,13,2,4},{14,6,12,3},{10,15,8,11}};
//    	int[][] blocks = {{3,2},{1,0}};
		Board board = new Board(blocks);
		
    	Solver solver = new Solver(board);
    	if (solver.isSolvable()) {
	    	for (Board b : solver.solution()) {
	    		System.out.println(b);
	    	}
    	} else
    		System.out.println("unsolvable");
    }
}
