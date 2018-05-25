package com.week2;

import java.util.Iterator;

public class Deque <Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node next;
		Node previous;
	}
	private Node first;
	private Node last;
	private int size;
	// construct an empty deque
	public Deque() {
		size = 0;
	}
	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}
	// return the number of items on the deque
	public int size() {
		return size;
	}
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.IllegalArgumentException();
		Node oldFirst = first;
		first = new Node();
		first.next = oldFirst;
		first.item = item;
		if (isEmpty())
			last = first; 
		else 
			oldFirst.previous = first;
		size++;
	}
	// add the item to the end
	public void addLast(Item item) {
		Node oldLast = last;
		last = new Node();
		last.previous = oldLast;
		last.item = item;
		if (isEmpty())
			first = last;
		else
			oldLast.next = last;
		size++;
	}
	// remove and return the item from the front
	public Item removeFirst() {
		if (!isEmpty()) {
			Item temp = first.item;
			if (first.next != null) {
				first = first.next;
				first.previous = null;
			} else {
				first = null;
				last = null;
			}
			size--;
			return temp;
		} else throw new java.util.NoSuchElementException();
	}
	// remove and return the item from the end
	public Item removeLast() {
		if (!isEmpty()) {
			Item temp = last.item;
			if (last.previous != null) {
				last = last.previous;
				last.next = null;
			} else {
				first = null;
				last = null;
			}
			size--;
			return temp;
		} else throw new java.util.NoSuchElementException();
	}
	
	@Override
	public Iterator<Item> iterator() {		// return an iterator over items in order from front to end
		// TODO Auto-generated method stub
		return new DequeIterator();
	}
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new java.lang.UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args) {  // unit testing (optional)
		Deque<String> deque = new Deque<String>();
		deque.addLast("a");
		deque.addLast("c");
		deque.addFirst("d");
		
		for(String s : deque) {
			System.out.println(s);
		}
		deque.removeFirst();
		deque.removeLast();
		deque.removeFirst();
		for(String s : deque) {
			System.out.println(s);
		}
	}

}
