package com.week2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>  {
	private Item[] queue;
	private int size;
	// construct an empty randomized queue
	public RandomizedQueue() {
		size = 0;
		queue = (Item[]) new Object[5];
	}
	// is the randomized queue empty?
	public boolean isEmpty() {
		return size == 0;
	}
	// return the number of items on the randomized queue
	public int size() {
		return size;
	}
	// add the item
	public void enqueue(Item item) {
		if (size == queue.length) {
			expandSize();
		}
		queue[size] = item;
		size++;
	}
	// remove and return a random item
	public Item dequeue() {
		if (size == queue.length / 4) {
			shrinkSize();
		}
		int i = StdRandom.uniform(size);
		Item item = queue[i];
		queue[i] = queue[size - 1];
		queue[size - 1] = null;
		size--;
		return item;
	}
	// return a random item (but do not remove it)
	public Item sample() {
		return queue[StdRandom.uniform(size)];
	}
	private void expandSize() {
		Item[] oldQueue = queue;
		queue = (Item[]) new Object[queue.length * 2];
		for (int i = 0; i < size; i++) {
			queue[i] = oldQueue[i];
		}
	}
	private void shrinkSize() {
		Item[] oldQueue = queue;
		queue = (Item[]) new Object[queue.length / 2];
		for (int i = 0; i < size; i++) {
			queue[i] = oldQueue[i];
		}
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	private class RandomizedQueueIterator<Item> implements Iterator {
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return dequeue();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	// unit testing (optional)
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		queue.enqueue("d");
		queue.enqueue("e");
		queue.enqueue("f");
		queue.enqueue("g");
		for (String s : queue) {
			System.out.println(s);
		}
	}

}
