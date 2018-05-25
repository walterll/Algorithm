package com.s2.week5;

public class CircularSuffixArray {
	private String[] suffixArray;
	// circular suffix array of s
	public CircularSuffixArray(String s) {
		suffixArray = new String[s.length()];
		suffixArray[0] = s + '0';
		String tempString = s;
		for (int i = 1; i < s.length(); i++) {
			suffixArray[i] = tempString.substring(1, s.length()) + tempString.charAt(0) + i;
			tempString = suffixArray[i];
		}
//		for (int i = 0; i < s.length(); i++)
//			System.out.println(suffixArray[i]);
		sort(suffixArray);
//		for (int i = 0; i < s.length(); i++)
//			System.out.println(suffixArray[i]);
	}
	//LSD排序
	private void sort(String[] a) {
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
//		for (int d = a[0].length() - 2; d > 0; d--) {//原来自己写的,第一位没有排
		for (int d = a[0].length() - 2; d >= 0; d--) {
			int[] count = new int[R];
			for (int i = 0; i < N; i++)
//				count[a[i].charAt(d)]++;  //这是原来自己写的，造成bug
				count[a[i].charAt(d) + 1]++;//因为count比数组中的key要大1，所以要延后一位
			for (int i = 0; i < R - 1; i++)
				count[i + 1] += count[i];
			for (int i = 0; i < N; i++)
				aux[count[a[i].charAt(d)]++] = a[i];
			for (int i = 0; i < N; i++)
				a[i] = aux[i];
		}
	}
	//原版LSD排序由于需要生成整个字符串数组，未能达到空间和时间要求，尝试只用原始字符串的排序
	private void sort(String s) {
		int N = s.length();
		int R = 256;
		int[] index = new int[N];
		for (int i = 0; i < N; i++)
			index[i] = i;
		for (int d = N - 1; d >= 0; d--) {
			int[] count = new int[R];
			for (int i = 0; i < N; i++)
				count[s.charAt(getIndex(d, index[i])) + 1]++;
			for (int i = 0; i < R - 1; i++)
				count[i + 1] += count[i];
			for (int i = 0; i < N; i++)
				index[count[s.charAt(getIndex(d, index[i]))]++] = i;
		}
		for (int i = 0; i < N; i++)
			System.out.println(index[i]);
	}
	//因为字符串循环取字符时会从最左跳跃到最右，使用此方法便于得到下标
	private int getIndex(int d, int i) {
		return d >= i ? d - i : length() - i + d;
	}
	/*
	 * 原本想偷懒在这里添加这个函数，便于BurrowsWheeler的transform()中获得编码，
	 * 后参考了github中ArpitParuthi的作业，发现没有这个必要，完全可以用原字符串和
	 * index获得全部编码的字符
	 */
//	public String[] getSortedArray(){			
//		return suffixArray;
//	}
	// length of s
	public int length() {
		return suffixArray.length;
	}
	// returns index of ith sorted suffix
	public int index(int i) {
		return Integer.valueOf(suffixArray[i].substring(length()));
	}
	// unit testing (required)
	public static void main(String[] args) {
		CircularSuffixArray array = new CircularSuffixArray("ABRACADABRA!");
//		System.out.println(array.index(2));
		array.sort("ABRACADABRA!");
	}
}
