package com.leetcode;

/**********************
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 24 = 16
 * 示例 3:
 * 输入: 218
 * 输出: false
 * @author Walterll
 *
 */
//注意1也是2的幂
public class Question231 {
	public boolean isPowerOfTwo(int n) {
		if (n < 1) return false;
		if (n == 1 || n == 2) return true;
		double m = n;
		while (m >= 2) {
			m /= 2;
			if (m == 2)
				return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question231 q = new Question231();
		System.out.println(q.isPowerOfTwo(32));
	}

}
