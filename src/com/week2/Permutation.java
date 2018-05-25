package com.week2;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		while (!StdIn.isEmpty()) {
			queue.enqueue(StdIn.readString());
			Iterator<String> iterator = queue.iterator(); 
			for (int i = 0; i < Integer.parseInt(args[0]); i++) {
				if (iterator.hasNext()) 
					System.out.println(iterator.next());
				else break;
			}
		}
	}
}
