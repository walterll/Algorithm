package com.s2.week5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;


public class BurrowsWheeler {
	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
	//用于在eclipse自己测试的版本
	public static void transform(String str) {
//		String str = BinaryStdIn.readString();
		CircularSuffixArray csa = new CircularSuffixArray(str);
//		String[] array = csa.getSortedArray();  //此为偷懒方法，已注释
//		for (int i = 0; i < csa.length(); i++)
//			System.out.println(array[i]);
		for (int i = 0; i < csa.length(); i++) 
			if (csa.index(i) == 0)
				System.out.println(i);
		int index;
		for (int i = 0; i < csa.length(); i++) {
			index = csa.index(i);
			if (index == 0)
				index = csa.length();
			System.out.println(str.charAt(index - 1));
		}
	}
	
	public static void transform() {
		String str = BinaryStdIn.readString();
		CircularSuffixArray csa = new CircularSuffixArray(str);
//		String[] array = csa.getSortedArray();  //此为偷懒方法，已注释
//		for (int i = 0; i < csa.length(); i++)
//			System.out.println(array[i]);
		for (int i = 0; i < csa.length(); i++) 
			if (csa.index(i) == 0)
				BinaryStdOut.write(i, 32);
		int index;
		for (int i = 0; i < csa.length(); i++) {
			index = csa.index(i);
			if (index == 0)
				index = csa.length();
			BinaryStdOut.write(str.charAt(index - 1), 8);
		}
		BinaryStdOut.flush();
	}
	
	// apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	//用于在eclipse自己测试的版本
	public static void inverseTransform(String str) {
		int R = 256;
		int[] count = new int[R];
//		boolean[] singleLetter = new boolean[R];
		char[] t = new char[str.length()];		//存放ordered字符串尾字符
		char[] first = new char[str.length()];	//存放ordered字符串首字符
		int[] next = new int[str.length()]; 	//存放next数组
		for (int i = 0; i < str.length(); i++) {
			t[i] = str.charAt(i);
//			count[t[i]]++;		//这里再次写错
			count[t[i] + 1]++;
		}
//		for (int i = 0; i < str.length(); i++) {
//			if (count[t[i] + 1] == 1)
//				singleLetter[i] = true;
//		}
		for (int i = 0; i < R - 1; i++) {
			count[i + 1] += count[i];
//			if ((count[i] == 1) && (count[i + 1] != count[i]))
//				singleLetter[i] = true;
		}
		for (int i = 0; i < str.length(); i++) {
			first[count[t[i]]] = t[i];
//			if (singleLetter[i])
			next[count[t[i]]++] = i;
//			else ()
		}
//		for (int i = 0; i < str.length(); i++) {
////			first[count[t[i]]++] = t[i];
//			if (!single)
//		}
//			System.out.println(count[t[i]]++);
		System.out.println(first[3]);
		int nextLetter = 3;
		for (int i = 1; i < str.length(); i++) {
			nextLetter = next[nextLetter];
			System.out.println(first[nextLetter]);
//			System.out.println(next[i]);
		}
	}
	
	public static void inverseTransform() {
		int R = 256;
		int[] count = new int[R];
		int nextLetter = BinaryStdIn.readInt(32);
		String str = BinaryStdIn.readString();
		char[] t = new char[str.length()];		//存放ordered字符串尾字符
		char[] first = new char[str.length()];	//存放ordered字符串首字符
		int[] next = new int[str.length()]; 	//存放next数组
		for (int i = 0; i < str.length(); i++) {
			t[i] = str.charAt(i);
			count[t[i] + 1]++;
		}
		for (int i = 0; i < R - 1; i++) {
			count[i + 1] += count[i];
		}
		for (int i = 0; i < str.length(); i++) {
			first[count[t[i]]] = t[i];
			next[count[t[i]]++] = i;
		}
		BinaryStdOut.write(first[3], 8);
		
		for (int i = 1; i < str.length(); i++) {
			nextLetter = next[nextLetter];
			BinaryStdOut.write(first[nextLetter]);
//			System.out.println(next[i]);
		}
	}
	
	// if args[0] is '-', apply Burrows-Wheeler transform
	// if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) {
//		if (args[2] != null) {
//			In in = new In(args[2]);
//		}
//		if ("+".equals(args[0])) {
//			inverseTransform("ARD!RCAAAABB");
//		}
		if ("-".equals(args[0]))
			transform();
    	else if ("+".equals(args[0]))
    		inverseTransform();
    	else throw new IllegalArgumentException("Illegal command line argument");
	}

}
