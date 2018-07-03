package com.leetcode.med;

/******************
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * @author Walterll
 *
 */
//网上看了些提示，自己写了个算法（用的方法4：中心扩展算法）
//遍历字符串，分两种情况寻找回文，速度在中等
//解答里提供的中心扩展算法的速度明显更快，复习时尝试自己写一下
public class Question5 {
	public String longestPalindrome(String s) {
		int longest = 1;
		int longestIndex = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			//计算奇数长的回文
			int palindrome = 1;
			int shift = 1;
			while ((i >= shift && i + shift < s.length())
				&& (s.charAt(i - shift) == s.charAt(i + shift))) {
				palindrome += 2;
				shift++;
			}
			if (palindrome > longest) {
				longest = palindrome;
				longestIndex = i;
			}
			//计算偶数长的回文
			palindrome = 0;
			shift = 0;
			while ((i >= shift && i + shift + 1 < s.length()) 
				&& (s.charAt(i - shift) == s.charAt(i + shift + 1))) {
				palindrome += 2;
				shift++;
			}
			if (palindrome > longest) {
				longest = palindrome;
				longestIndex = i;
			}
		}
		System.out.println("longestIndex: " + longestIndex);
		System.out.println("longest: " + longest);
		if (longest % 2 == 0)
			System.out.println((longestIndex - longest / 2 + 1)  + "  "  + (longestIndex + longest / 2 + 1));
		else 
			System.out.println((longestIndex - longest / 2) + "  " + (longestIndex + longest / 2 + 1));
		return longest % 2 == 0 ?
				s.substring(longestIndex - longest / 2 + 1, longestIndex + longest / 2 + 1)
				: s.substring(longestIndex - longest / 2, longestIndex + longest / 2 + 1);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question5 q = new Question5();
		System.out.println(q.longestPalindrome("bbabb"));
	}

}
